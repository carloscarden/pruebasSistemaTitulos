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

describe('AlumnoEstabOferta e2e test', () => {
  const alumnoEstabOfertaPageUrl = '/alumno-estab-oferta';
  const alumnoEstabOfertaPageUrlPattern = new RegExp('/alumno-estab-oferta(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const alumnoEstabOfertaSample = {};

  let alumnoEstabOferta: any;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/alumno-estab-ofertas+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/alumno-estab-ofertas').as('postEntityRequest');
    cy.intercept('DELETE', '/api/alumno-estab-ofertas/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (alumnoEstabOferta) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/alumno-estab-ofertas/${alumnoEstabOferta.id}`,
      }).then(() => {
        alumnoEstabOferta = undefined;
      });
    }
  });

  it('AlumnoEstabOfertas menu should load AlumnoEstabOfertas page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('alumno-estab-oferta');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response!.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('AlumnoEstabOferta').should('exist');
    cy.url().should('match', alumnoEstabOfertaPageUrlPattern);
  });

  describe('AlumnoEstabOferta page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(alumnoEstabOfertaPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create AlumnoEstabOferta page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/alumno-estab-oferta/new$'));
        cy.getEntityCreateUpdateHeading('AlumnoEstabOferta');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', alumnoEstabOfertaPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/alumno-estab-ofertas',
          body: alumnoEstabOfertaSample,
        }).then(({ body }) => {
          alumnoEstabOferta = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/alumno-estab-ofertas+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [alumnoEstabOferta],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(alumnoEstabOfertaPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details AlumnoEstabOferta page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('alumnoEstabOferta');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', alumnoEstabOfertaPageUrlPattern);
      });

      it('edit button click should load edit AlumnoEstabOferta page', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('AlumnoEstabOferta');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', alumnoEstabOfertaPageUrlPattern);
      });

      it('last delete button click should delete instance of AlumnoEstabOferta', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('alumnoEstabOferta').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', alumnoEstabOfertaPageUrlPattern);

        alumnoEstabOferta = undefined;
      });
    });
  });

  describe('new AlumnoEstabOferta page', () => {
    beforeEach(() => {
      cy.visit(`${alumnoEstabOfertaPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('AlumnoEstabOferta');
    });

    it('should create an instance of AlumnoEstabOferta', () => {
      cy.get(`[data-cy="idSer"]`).type('41276').should('have.value', '41276');

      cy.get(`[data-cy="idOfertaEducativa"]`).type('77542').should('have.value', '77542');

      cy.get(`[data-cy="idAlumno"]`).type('44942').should('have.value', '44942');

      cy.get(`[data-cy="idEstadoAlumnoEstabOferta"]`).type('63164').should('have.value', '63164');

      cy.get(`[data-cy="fechaInicio"]`).type('2022-06-06T10:51').should('have.value', '2022-06-06T10:51');

      cy.get(`[data-cy="fechaFin"]`).type('2022-06-06T02:49').should('have.value', '2022-06-06T02:49');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response!.statusCode).to.equal(201);
        alumnoEstabOferta = response!.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response!.statusCode).to.equal(200);
      });
      cy.url().should('match', alumnoEstabOfertaPageUrlPattern);
    });
  });
});
