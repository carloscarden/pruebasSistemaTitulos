import { entityItemSelector } from '../../support/commands';
import { entityTableSelector, entityDetailsButtonSelector, entityDetailsBackButtonSelector } from '../../support/entity';

describe('Turno e2e test', () => {
  const turnoPageUrl = '/turno';
  const turnoPageUrlPattern = new RegExp('/turno(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const turnoSample = {};

  let turno: any;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/turnos+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/turnos').as('postEntityRequest');
    cy.intercept('DELETE', '/api/turnos/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (turno) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/turnos/${turno.id}`,
      }).then(() => {
        turno = undefined;
      });
    }
  });

  it('Turnos menu should load Turnos page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('turno');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response!.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('Turno').should('exist');
    cy.url().should('match', turnoPageUrlPattern);
  });

  describe('Turno page', () => {
    describe('with existing value', () => {
      beforeEach(function () {
        cy.visit(turnoPageUrl);

        cy.wait('@entitiesRequest').then(({ response }) => {
          if (response!.body.length === 0) {
            this.skip();
          }
        });
      });

      it('detail button click should load details Turno page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('turno');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', turnoPageUrlPattern);
      });
    });
  });
});
