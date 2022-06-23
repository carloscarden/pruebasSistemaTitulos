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

describe('NroSerieTitulo e2e test', () => {
  const nroSerieTituloPageUrl = '/nro-serie-titulo';
  const nroSerieTituloPageUrlPattern = new RegExp('/nro-serie-titulo(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const nroSerieTituloSample = {};

  let nroSerieTitulo: any;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/nro-serie-titulos+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/nro-serie-titulos').as('postEntityRequest');
    cy.intercept('DELETE', '/api/nro-serie-titulos/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (nroSerieTitulo) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/nro-serie-titulos/${nroSerieTitulo.id}`,
      }).then(() => {
        nroSerieTitulo = undefined;
      });
    }
  });

  it('NroSerieTitulos menu should load NroSerieTitulos page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('nro-serie-titulo');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response!.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('NroSerieTitulo').should('exist');
    cy.url().should('match', nroSerieTituloPageUrlPattern);
  });

  describe('NroSerieTitulo page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(nroSerieTituloPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create NroSerieTitulo page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/nro-serie-titulo/new$'));
        cy.getEntityCreateUpdateHeading('NroSerieTitulo');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', nroSerieTituloPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/nro-serie-titulos',
          body: nroSerieTituloSample,
        }).then(({ body }) => {
          nroSerieTitulo = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/nro-serie-titulos+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [nroSerieTitulo],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(nroSerieTituloPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details NroSerieTitulo page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('nroSerieTitulo');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', nroSerieTituloPageUrlPattern);
      });

      it('edit button click should load edit NroSerieTitulo page', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('NroSerieTitulo');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', nroSerieTituloPageUrlPattern);
      });

      it('last delete button click should delete instance of NroSerieTitulo', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('nroSerieTitulo').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', nroSerieTituloPageUrlPattern);

        nroSerieTitulo = undefined;
      });
    });
  });

  describe('new NroSerieTitulo page', () => {
    beforeEach(() => {
      cy.visit(`${nroSerieTituloPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('NroSerieTitulo');
    });

    it('should create an instance of NroSerieTitulo', () => {
      cy.get(`[data-cy="nroSerieInicio"]`).type('26217').should('have.value', '26217');

      cy.get(`[data-cy="nroSerieFin"]`).type('42338').should('have.value', '42338');

      cy.get(`[data-cy="fechaInicio"]`).type('2022-06-06').should('have.value', '2022-06-06');

      cy.get(`[data-cy="fechaFin"]`).type('2022-06-06').should('have.value', '2022-06-06');

      cy.get(`[data-cy="usuarioAlta"]`).type('Cliente Genérico Negro').should('have.value', 'Cliente Genérico Negro');

      cy.get(`[data-cy="usuarioModificacion"]`).type('Berkshire Configurable').should('have.value', 'Berkshire Configurable');

      cy.get(`[data-cy="serie"]`).type('67324').should('have.value', '67324');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response!.statusCode).to.equal(201);
        nroSerieTitulo = response!.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response!.statusCode).to.equal(200);
      });
      cy.url().should('match', nroSerieTituloPageUrlPattern);
    });
  });
});
