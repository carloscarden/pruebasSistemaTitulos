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

describe('AlumnoTitulo e2e test', () => {
  const alumnoTituloPageUrl = '/alumno-titulo';
  const alumnoTituloPageUrlPattern = new RegExp('/alumno-titulo(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const alumnoTituloSample = {};

  let alumnoTitulo: any;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/alumno-titulos+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/alumno-titulos').as('postEntityRequest');
    cy.intercept('DELETE', '/api/alumno-titulos/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (alumnoTitulo) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/alumno-titulos/${alumnoTitulo.id}`,
      }).then(() => {
        alumnoTitulo = undefined;
      });
    }
  });

  it('AlumnoTitulos menu should load AlumnoTitulos page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('alumno-titulo');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response!.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('AlumnoTitulo').should('exist');
    cy.url().should('match', alumnoTituloPageUrlPattern);
  });

  describe('AlumnoTitulo page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(alumnoTituloPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create AlumnoTitulo page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/alumno-titulo/new$'));
        cy.getEntityCreateUpdateHeading('AlumnoTitulo');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', alumnoTituloPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/alumno-titulos',
          body: alumnoTituloSample,
        }).then(({ body }) => {
          alumnoTitulo = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/alumno-titulos+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              headers: {
                link: '<http://localhost/api/alumno-titulos?page=0&size=20>; rel="last",<http://localhost/api/alumno-titulos?page=0&size=20>; rel="first"',
              },
              body: [alumnoTitulo],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(alumnoTituloPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details AlumnoTitulo page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('alumnoTitulo');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', alumnoTituloPageUrlPattern);
      });

      it('edit button click should load edit AlumnoTitulo page', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('AlumnoTitulo');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', alumnoTituloPageUrlPattern);
      });

      it('last delete button click should delete instance of AlumnoTitulo', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('alumnoTitulo').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', alumnoTituloPageUrlPattern);

        alumnoTitulo = undefined;
      });
    });
  });

  describe('new AlumnoTitulo page', () => {
    beforeEach(() => {
      cy.visit(`${alumnoTituloPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('AlumnoTitulo');
    });

    it('should create an instance of AlumnoTitulo', () => {
      cy.get(`[data-cy="idAlumnoEstabOferta"]`).type('1688').should('have.value', '1688');

      cy.get(`[data-cy="fechaEmision"]`).type('2022-06-06T11:40').should('have.value', '2022-06-06T11:40');

      cy.get(`[data-cy="nroSerie"]`).type('compress').should('have.value', 'compress');

      cy.get(`[data-cy="numeroRfifd"]`).type('Seguridada Sabroso lateral').should('have.value', 'Seguridada Sabroso lateral');

      cy.get(`[data-cy="validezNacional"]`).type('invoice gestión').should('have.value', 'invoice gestión');

      cy.get(`[data-cy="fechaEnvio"]`).type('2022-06-05T22:57').should('have.value', '2022-06-05T22:57');

      cy.get(`[data-cy="fechaEgreso"]`).type('2022-06-06T04:57').should('have.value', '2022-06-06T04:57');

      cy.get(`[data-cy="nroLibroMatriz"]`).type('52351').should('have.value', '52351');

      cy.get(`[data-cy="nroActa"]`).type('22104').should('have.value', '22104');

      cy.get(`[data-cy="nroFolio"]`).type('83265').should('have.value', '83265');

      cy.get(`[data-cy="estabNombre"]`).type('Granito Avon Gorro').should('have.value', 'Granito Avon Gorro');

      cy.get(`[data-cy="estabCue"]`).type('Optimización portal').should('have.value', 'Optimización portal');

      cy.get(`[data-cy="estabUbicadoEn"]`).type('productividad firewall').should('have.value', 'productividad firewall');

      cy.get(`[data-cy="estabCiudad"]`).type('Prolongación optical Amarillo').should('have.value', 'Prolongación optical Amarillo');

      cy.get(`[data-cy="estabProvincia"]`).type('24/7 Moda').should('have.value', '24/7 Moda');

      cy.get(`[data-cy="idEstadoAlumnoTitulo"]`).type('5966').should('have.value', '5966');

      cy.get(`[data-cy="tituloIntermedio"]`).type('56528').should('have.value', '56528');

      cy.get(`[data-cy="promedio"]`).type('Senior Dollar XSS').should('have.value', 'Senior Dollar XSS');

      cy.get(`[data-cy="fechaRuta"]`).type('2022-06-05T22:08').should('have.value', '2022-06-05T22:08');

      cy.get(`[data-cy="idRamaRuta"]`).type('45173').should('have.value', '45173');

      cy.get(`[data-cy="apYnomListoParaEnviar"]`).type('Soporte óptima').should('have.value', 'Soporte óptima');

      cy.get(`[data-cy="documentoListoParaEnviar"]`).type('instalación').should('have.value', 'instalación');

      cy.get(`[data-cy="apYnomEnviado"]`).type('Baleares').should('have.value', 'Baleares');

      cy.get(`[data-cy="documentoEnviado"]`).type('Cambridgeshire').should('have.value', 'Cambridgeshire');

      cy.get(`[data-cy="fechaUltimoEstado"]`).type('2022-06-06T14:31').should('have.value', '2022-06-06T14:31');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response!.statusCode).to.equal(201);
        alumnoTitulo = response!.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response!.statusCode).to.equal(200);
      });
      cy.url().should('match', alumnoTituloPageUrlPattern);
    });
  });
});
