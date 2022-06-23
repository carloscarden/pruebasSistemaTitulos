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

describe('Rendicion e2e test', () => {
  const rendicionPageUrl = '/rendicion';
  const rendicionPageUrlPattern = new RegExp('/rendicion(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const rendicionSample = {};

  let rendicion: any;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/rendicions+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/rendicions').as('postEntityRequest');
    cy.intercept('DELETE', '/api/rendicions/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (rendicion) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/rendicions/${rendicion.id}`,
      }).then(() => {
        rendicion = undefined;
      });
    }
  });

  it('Rendicions menu should load Rendicions page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('rendicion');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response!.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('Rendicion').should('exist');
    cy.url().should('match', rendicionPageUrlPattern);
  });

  describe('Rendicion page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(rendicionPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create Rendicion page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/rendicion/new$'));
        cy.getEntityCreateUpdateHeading('Rendicion');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', rendicionPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/rendicions',
          body: rendicionSample,
        }).then(({ body }) => {
          rendicion = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/rendicions+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              headers: {
                link: '<http://localhost/api/rendicions?page=0&size=20>; rel="last",<http://localhost/api/rendicions?page=0&size=20>; rel="first"',
              },
              body: [rendicion],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(rendicionPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details Rendicion page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('rendicion');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', rendicionPageUrlPattern);
      });

      it('edit button click should load edit Rendicion page', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Rendicion');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', rendicionPageUrlPattern);
      });

      it('last delete button click should delete instance of Rendicion', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('rendicion').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', rendicionPageUrlPattern);

        rendicion = undefined;
      });
    });
  });

  describe('new Rendicion page', () => {
    beforeEach(() => {
      cy.visit(`${rendicionPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('Rendicion');
    });

    it('should create an instance of Rendicion', () => {
      cy.get(`[data-cy="dniUsuario"]`).type('Puente Avon Guapo').should('have.value', 'Puente Avon Guapo');

      cy.get(`[data-cy="nombreUsuario"]`).type('backing').should('have.value', 'backing');

      cy.get(`[data-cy="dniUsuarioAnulador"]`).type('Namibia').should('have.value', 'Namibia');

      cy.get(`[data-cy="nombreUsuarioAnulador"]`).type('Senior Multi').should('have.value', 'Senior Multi');

      cy.get(`[data-cy="motivo"]`).type('Canada out-of-the-box Bebes').should('have.value', 'Canada out-of-the-box Bebes');

      cy.get(`[data-cy="causaRechazo"]`).type('harness').should('have.value', 'harness');

      cy.get(`[data-cy="fechaAnulacion"]`).type('2022-06-05').should('have.value', '2022-06-05');

      cy.get(`[data-cy="dniAlumno"]`).type('invoice Rincón hibrida').should('have.value', 'invoice Rincón hibrida');

      cy.get(`[data-cy="nombreAlumno"]`).type('array').should('have.value', 'array');

      cy.get(`[data-cy="alumnoTituloId"]`).type('58571').should('have.value', '58571');

      cy.get(`[data-cy="establecimientoId"]`).type('72020').should('have.value', '72020');

      cy.get(`[data-cy="claveEstab"]`).type('calculate Buckinghamshire').should('have.value', 'calculate Buckinghamshire');

      cy.get(`[data-cy="rama"]`).type('trabajo SSL Bebes').should('have.value', 'trabajo SSL Bebes');

      cy.get(`[data-cy="nroFormulario"]`).type('58916').should('have.value', '58916');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response!.statusCode).to.equal(201);
        rendicion = response!.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response!.statusCode).to.equal(200);
      });
      cy.url().should('match', rendicionPageUrlPattern);
    });
  });
});
