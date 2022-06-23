import { entityItemSelector } from '../../support/commands';
import { entityTableSelector, entityDetailsButtonSelector, entityDetailsBackButtonSelector } from '../../support/entity';

describe('Jornada e2e test', () => {
  const jornadaPageUrl = '/jornada';
  const jornadaPageUrlPattern = new RegExp('/jornada(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const jornadaSample = {};

  let jornada: any;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/jornadas+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/jornadas').as('postEntityRequest');
    cy.intercept('DELETE', '/api/jornadas/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (jornada) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/jornadas/${jornada.id}`,
      }).then(() => {
        jornada = undefined;
      });
    }
  });

  it('Jornadas menu should load Jornadas page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('jornada');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response!.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('Jornada').should('exist');
    cy.url().should('match', jornadaPageUrlPattern);
  });

  describe('Jornada page', () => {
    describe('with existing value', () => {
      beforeEach(function () {
        cy.visit(jornadaPageUrl);

        cy.wait('@entitiesRequest').then(({ response }) => {
          if (response!.body.length === 0) {
            this.skip();
          }
        });
      });

      it('detail button click should load details Jornada page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('jornada');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', jornadaPageUrlPattern);
      });
    });
  });
});
