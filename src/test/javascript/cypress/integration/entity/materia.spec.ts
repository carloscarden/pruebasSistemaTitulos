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

describe('Materia e2e test', () => {
  const materiaPageUrl = '/materia';
  const materiaPageUrlPattern = new RegExp('/materia(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const materiaSample = {};

  let materia: any;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/materias+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/materias').as('postEntityRequest');
    cy.intercept('DELETE', '/api/materias/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (materia) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/materias/${materia.id}`,
      }).then(() => {
        materia = undefined;
      });
    }
  });

  it('Materias menu should load Materias page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('materia');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response!.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('Materia').should('exist');
    cy.url().should('match', materiaPageUrlPattern);
  });

  describe('Materia page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(materiaPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create Materia page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/materia/new$'));
        cy.getEntityCreateUpdateHeading('Materia');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', materiaPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/materias',
          body: materiaSample,
        }).then(({ body }) => {
          materia = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/materias+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [materia],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(materiaPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details Materia page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('materia');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', materiaPageUrlPattern);
      });

      it('edit button click should load edit Materia page', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Materia');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', materiaPageUrlPattern);
      });

      it('last delete button click should delete instance of Materia', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('materia').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', materiaPageUrlPattern);

        materia = undefined;
      });
    });
  });

  describe('new Materia page', () => {
    beforeEach(() => {
      cy.visit(`${materiaPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('Materia');
    });

    it('should create an instance of Materia', () => {
      cy.get(`[data-cy="idMateriaDenominacion"]`).type('61999').should('have.value', '61999');

      cy.get(`[data-cy="marcaModulo"]`).type('open-source virtual').should('have.value', 'open-source virtual');

      cy.get(`[data-cy="cargaHoraria"]`).type('45221').should('have.value', '45221');

      cy.get(`[data-cy="idOfertaEducativa"]`).type('39838').should('have.value', '39838');

      cy.get(`[data-cy="idSeccion"]`).type('6435').should('have.value', '6435');

      cy.get(`[data-cy="idTipoMateriaOpcional"]`).type('75133').should('have.value', '75133');

      cy.get(`[data-cy="orden"]`).type('623').should('have.value', '623');

      cy.get(`[data-cy="idArea"]`).type('31886').should('have.value', '31886');

      cy.get(`[data-cy="idAsignatira"]`).type('80941').should('have.value', '80941');

      cy.get(`[data-cy="codigosChequeados"]`).type('6266').should('have.value', '6266');

      cy.get(`[data-cy="obligatoriedad"]`).type('20116').should('have.value', '20116');

      cy.get(`[data-cy="unidadCargaPedagogica"]`).type('15383').should('have.value', '15383');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response!.statusCode).to.equal(201);
        materia = response!.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response!.statusCode).to.equal(200);
      });
      cy.url().should('match', materiaPageUrlPattern);
    });
  });
});
