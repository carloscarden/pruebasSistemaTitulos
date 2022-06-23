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

describe('OfertaEducativa e2e test', () => {
  const ofertaEducativaPageUrl = '/oferta-educativa';
  const ofertaEducativaPageUrlPattern = new RegExp('/oferta-educativa(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const ofertaEducativaSample = {};

  let ofertaEducativa: any;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/oferta-educativas+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/oferta-educativas').as('postEntityRequest');
    cy.intercept('DELETE', '/api/oferta-educativas/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (ofertaEducativa) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/oferta-educativas/${ofertaEducativa.id}`,
      }).then(() => {
        ofertaEducativa = undefined;
      });
    }
  });

  it('OfertaEducativas menu should load OfertaEducativas page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('oferta-educativa');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response!.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('OfertaEducativa').should('exist');
    cy.url().should('match', ofertaEducativaPageUrlPattern);
  });

  describe('OfertaEducativa page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(ofertaEducativaPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create OfertaEducativa page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/oferta-educativa/new$'));
        cy.getEntityCreateUpdateHeading('OfertaEducativa');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', ofertaEducativaPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/oferta-educativas',
          body: ofertaEducativaSample,
        }).then(({ body }) => {
          ofertaEducativa = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/oferta-educativas+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [ofertaEducativa],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(ofertaEducativaPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details OfertaEducativa page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('ofertaEducativa');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', ofertaEducativaPageUrlPattern);
      });

      it('edit button click should load edit OfertaEducativa page', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('OfertaEducativa');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', ofertaEducativaPageUrlPattern);
      });

      it('last delete button click should delete instance of OfertaEducativa', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('ofertaEducativa').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', ofertaEducativaPageUrlPattern);

        ofertaEducativa = undefined;
      });
    });
  });

  describe('new OfertaEducativa page', () => {
    beforeEach(() => {
      cy.visit(`${ofertaEducativaPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('OfertaEducativa');
    });

    it('should create an instance of OfertaEducativa', () => {
      cy.get(`[data-cy="idModalidad"]`).type('80818').should('have.value', '80818');

      cy.get(`[data-cy="idNivel"]`).type('34906').should('have.value', '34906');

      cy.get(`[data-cy="idOrientacion"]`).type('71476').should('have.value', '71476');

      cy.get(`[data-cy="idTituloDenominacion"]`).type('95498').should('have.value', '95498');

      cy.get(`[data-cy="observiaciones"]`).type('Cuban Tanzania').should('have.value', 'Cuban Tanzania');

      cy.get(`[data-cy="idTipoTitulo"]`).type('87797').should('have.value', '87797');

      cy.get(`[data-cy="idUser"]`).type('57223').should('have.value', '57223');

      cy.get(`[data-cy="fechaInicio"]`).type('2022-06-05T21:46').should('have.value', '2022-06-05T21:46');

      cy.get(`[data-cy="fechaFin"]`).type('2022-06-06T13:47').should('have.value', '2022-06-06T13:47');

      cy.get(`[data-cy="fechaEstado"]`).type('2022-06-05T23:59').should('have.value', '2022-06-05T23:59');

      cy.get(`[data-cy="idEstadoOferta"]`).type('68420').should('have.value', '68420');

      cy.get(`[data-cy="idLeyEducacion"]`).type('87318').should('have.value', '87318');

      cy.get(`[data-cy="idNormaAprobacionDen"]`).type('55979').should('have.value', '55979');

      cy.get(`[data-cy="idNormaDictamenDen"]`).type('81313').should('have.value', '81313');

      cy.get(`[data-cy="idSeCorrespondeCon"]`).type('59586').should('have.value', '59586');

      cy.get(`[data-cy="titulo"]`).type('content').should('have.value', 'content');

      cy.get(`[data-cy="tituloImpresion"]`).type('Cuba Implementado').should('have.value', 'Cuba Implementado');

      cy.get(`[data-cy="tituloIntermedio"]`).type('a pixel').should('have.value', 'a pixel');

      cy.get(`[data-cy="tituloIntermedioImpresion"]`).type('Account').should('have.value', 'Account');

      cy.get(`[data-cy="orientacion"]`).type('Pasaje user-centric').should('have.value', 'Pasaje user-centric');

      cy.get(`[data-cy="idRama"]`).type('26663').should('have.value', '26663');

      cy.get(`[data-cy="idSeccionTituloIntermedio"]`).type('81209').should('have.value', '81209');

      cy.get(`[data-cy="idCursoGrupoNombre"]`).type('59623').should('have.value', '59623');

      cy.get(`[data-cy="correlatividad"]`).type('76298').should('have.value', '76298');

      cy.get(`[data-cy="idModalidadDictado"]`).type('12566').should('have.value', '12566');

      cy.get(`[data-cy="titula"]`).type('45357').should('have.value', '45357');

      cy.get(`[data-cy="cicloBasico"]`).type('30021').should('have.value', '30021');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response!.statusCode).to.equal(201);
        ofertaEducativa = response!.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response!.statusCode).to.equal(200);
      });
      cy.url().should('match', ofertaEducativaPageUrlPattern);
    });
  });
});
