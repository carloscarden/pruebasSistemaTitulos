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

describe('AlumnoAnalitico e2e test', () => {
  const alumnoAnaliticoPageUrl = '/alumno-analitico';
  const alumnoAnaliticoPageUrlPattern = new RegExp('/alumno-analitico(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const alumnoAnaliticoSample = {};

  let alumnoAnalitico: any;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/alumno-analiticos+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/alumno-analiticos').as('postEntityRequest');
    cy.intercept('DELETE', '/api/alumno-analiticos/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (alumnoAnalitico) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/alumno-analiticos/${alumnoAnalitico.id}`,
      }).then(() => {
        alumnoAnalitico = undefined;
      });
    }
  });

  it('AlumnoAnaliticos menu should load AlumnoAnaliticos page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('alumno-analitico');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response!.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('AlumnoAnalitico').should('exist');
    cy.url().should('match', alumnoAnaliticoPageUrlPattern);
  });

  describe('AlumnoAnalitico page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(alumnoAnaliticoPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create AlumnoAnalitico page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/alumno-analitico/new$'));
        cy.getEntityCreateUpdateHeading('AlumnoAnalitico');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', alumnoAnaliticoPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/alumno-analiticos',
          body: alumnoAnaliticoSample,
        }).then(({ body }) => {
          alumnoAnalitico = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/alumno-analiticos+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [alumnoAnalitico],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(alumnoAnaliticoPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details AlumnoAnalitico page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('alumnoAnalitico');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', alumnoAnaliticoPageUrlPattern);
      });

      it('edit button click should load edit AlumnoAnalitico page', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('AlumnoAnalitico');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', alumnoAnaliticoPageUrlPattern);
      });

      it('last delete button click should delete instance of AlumnoAnalitico', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('alumnoAnalitico').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', alumnoAnaliticoPageUrlPattern);

        alumnoAnalitico = undefined;
      });
    });
  });

  describe('new AlumnoAnalitico page', () => {
    beforeEach(() => {
      cy.visit(`${alumnoAnaliticoPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('AlumnoAnalitico');
    });

    it('should create an instance of AlumnoAnalitico', () => {
      cy.get(`[data-cy="nota"]`).type('4257').should('have.value', '4257');

      cy.get(`[data-cy="idEstadoAlumnoAnalitico"]`).type('16313').should('have.value', '16313');

      cy.get(`[data-cy="idAlumnoEstabOferta"]`).type('5852').should('have.value', '5852');

      cy.get(`[data-cy="idMateria"]`).type('43354').should('have.value', '43354');

      cy.get(`[data-cy="idMesImp"]`).type('90743').should('have.value', '90743');

      cy.get(`[data-cy="idAnoImp"]`).type('10974').should('have.value', '10974');

      cy.get(`[data-cy="establecimientoImp"]`).type('SMTP definición').should('have.value', 'SMTP definición');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response!.statusCode).to.equal(201);
        alumnoAnalitico = response!.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response!.statusCode).to.equal(200);
      });
      cy.url().should('match', alumnoAnaliticoPageUrlPattern);
    });
  });
});
