import { entityItemSelector } from '../../support/commands';
import {
  entityTableSelector,
  entityDetailsButtonSelector,
  entityDetailsBackButtonSelector,
  entityCreateButtonSelector,
  entityCreateSaveButtonSelector,
  entityCreateCancelButtonSelector,
  entityEditButtonSelector,
  entityDeleteButtonSelector,
  entityConfirmDeleteButtonSelector,
} from '../../support/entity';

describe('Alumno e2e test', () => {
  const alumnoPageUrl = '/alumno';
  const alumnoPageUrlPattern = new RegExp('/alumno(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const alumnoSample = {};

  let alumno: any;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/alumnos+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/alumnos').as('postEntityRequest');
    cy.intercept('DELETE', '/api/alumnos/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (alumno) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/alumnos/${alumno.id}`,
      }).then(() => {
        alumno = undefined;
      });
    }
  });

  it('Alumnos menu should load Alumnos page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('alumno');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response!.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('Alumno').should('exist');
    cy.url().should('match', alumnoPageUrlPattern);
  });

  describe('Alumno page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(alumnoPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create Alumno page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/alumno/new$'));
        cy.getEntityCreateUpdateHeading('Alumno');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', alumnoPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/alumnos',
          body: alumnoSample,
        }).then(({ body }) => {
          alumno = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/alumnos+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [alumno],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(alumnoPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details Alumno page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('alumno');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', alumnoPageUrlPattern);
      });

      it('edit button click should load edit Alumno page', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Alumno');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', alumnoPageUrlPattern);
      });

      it('last delete button click should delete instance of Alumno', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('alumno').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', alumnoPageUrlPattern);

        alumno = undefined;
      });
    });
  });

  describe('new Alumno page', () => {
    beforeEach(() => {
      cy.visit(`${alumnoPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('Alumno');
    });

    it('should create an instance of Alumno', () => {
      cy.get(`[data-cy="documento"]`).type('Vietnam Rincón grow').should('have.value', 'Vietnam Rincón grow');

      cy.get(`[data-cy="idTipoDocumento"]`).type('22661').should('have.value', '22661');

      cy.get(`[data-cy="nombre"]`).type('Borders').should('have.value', 'Borders');

      cy.get(`[data-cy="apellido"]`).type('Práctico').should('have.value', 'Práctico');

      cy.get(`[data-cy="fechaNacimento"]`).type('2022-06-06T08:56').should('have.value', '2022-06-06T08:56');

      cy.get(`[data-cy="sexo"]`).type('Algodón Moldavia').should('have.value', 'Algodón Moldavia');

      cy.get(`[data-cy="ciudadNacimiento"]`).type('Franc Investigación estable').should('have.value', 'Franc Investigación estable');

      cy.get(`[data-cy="provinciaNacimiento"]`).type('feed Berkshire Cambridgeshire').should('have.value', 'feed Berkshire Cambridgeshire');

      cy.get(`[data-cy="idNacionalidad"]`).type('97274').should('have.value', '97274');

      cy.get(`[data-cy="idSerCreador"]`).type('95042').should('have.value', '95042');

      cy.get(`[data-cy="idProvincia"]`).type('72402').should('have.value', '72402');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response!.statusCode).to.equal(201);
        alumno = response!.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response!.statusCode).to.equal(200);
      });
      cy.url().should('match', alumnoPageUrlPattern);
    });
  });
});
