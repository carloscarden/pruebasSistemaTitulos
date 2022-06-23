package com.dgcye.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dgcye.IntegrationTest;
import com.dgcye.domain.Materia;
import com.dgcye.repository.MateriaRepository;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link MateriaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MateriaResourceIT {

    private static final Long DEFAULT_ID_MATERIA_DENOMINACION = 1L;
    private static final Long UPDATED_ID_MATERIA_DENOMINACION = 2L;

    private static final String DEFAULT_MARCA_MODULO = "AAAAAAAAAA";
    private static final String UPDATED_MARCA_MODULO = "BBBBBBBBBB";

    private static final Long DEFAULT_CARGA_HORARIA = 1L;
    private static final Long UPDATED_CARGA_HORARIA = 2L;

    private static final Long DEFAULT_ID_OFERTA_EDUCATIVA = 1L;
    private static final Long UPDATED_ID_OFERTA_EDUCATIVA = 2L;

    private static final Long DEFAULT_ID_SECCION = 1L;
    private static final Long UPDATED_ID_SECCION = 2L;

    private static final Long DEFAULT_ID_TIPO_MATERIA_OPCIONAL = 1L;
    private static final Long UPDATED_ID_TIPO_MATERIA_OPCIONAL = 2L;

    private static final Long DEFAULT_ORDEN = 1L;
    private static final Long UPDATED_ORDEN = 2L;

    private static final Long DEFAULT_ID_AREA = 1L;
    private static final Long UPDATED_ID_AREA = 2L;

    private static final Long DEFAULT_ID_ASIGNATIRA = 1L;
    private static final Long UPDATED_ID_ASIGNATIRA = 2L;

    private static final Long DEFAULT_CODIGOS_CHEQUEADOS = 1L;
    private static final Long UPDATED_CODIGOS_CHEQUEADOS = 2L;

    private static final Long DEFAULT_OBLIGATORIEDAD = 1L;
    private static final Long UPDATED_OBLIGATORIEDAD = 2L;

    private static final Long DEFAULT_UNIDAD_CARGA_PEDAGOGICA = 1L;
    private static final Long UPDATED_UNIDAD_CARGA_PEDAGOGICA = 2L;

    private static final String ENTITY_API_URL = "/api/materias";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMateriaMockMvc;

    private Materia materia;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Materia createEntity(EntityManager em) {
        Materia materia = new Materia()
            .idMateriaDenominacion(DEFAULT_ID_MATERIA_DENOMINACION)
            .marcaModulo(DEFAULT_MARCA_MODULO)
            .cargaHoraria(DEFAULT_CARGA_HORARIA)
            .idOfertaEducativa(DEFAULT_ID_OFERTA_EDUCATIVA)
            .idSeccion(DEFAULT_ID_SECCION)
            .idTipoMateriaOpcional(DEFAULT_ID_TIPO_MATERIA_OPCIONAL)
            .orden(DEFAULT_ORDEN)
            .idArea(DEFAULT_ID_AREA)
            .idAsignatira(DEFAULT_ID_ASIGNATIRA)
            .codigosChequeados(DEFAULT_CODIGOS_CHEQUEADOS)
            .obligatoriedad(DEFAULT_OBLIGATORIEDAD)
            .unidadCargaPedagogica(DEFAULT_UNIDAD_CARGA_PEDAGOGICA);
        return materia;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Materia createUpdatedEntity(EntityManager em) {
        Materia materia = new Materia()
            .idMateriaDenominacion(UPDATED_ID_MATERIA_DENOMINACION)
            .marcaModulo(UPDATED_MARCA_MODULO)
            .cargaHoraria(UPDATED_CARGA_HORARIA)
            .idOfertaEducativa(UPDATED_ID_OFERTA_EDUCATIVA)
            .idSeccion(UPDATED_ID_SECCION)
            .idTipoMateriaOpcional(UPDATED_ID_TIPO_MATERIA_OPCIONAL)
            .orden(UPDATED_ORDEN)
            .idArea(UPDATED_ID_AREA)
            .idAsignatira(UPDATED_ID_ASIGNATIRA)
            .codigosChequeados(UPDATED_CODIGOS_CHEQUEADOS)
            .obligatoriedad(UPDATED_OBLIGATORIEDAD)
            .unidadCargaPedagogica(UPDATED_UNIDAD_CARGA_PEDAGOGICA);
        return materia;
    }

    @BeforeEach
    public void initTest() {
        materia = createEntity(em);
    }

    @Test
    @Transactional
    void createMateria() throws Exception {
        int databaseSizeBeforeCreate = materiaRepository.findAll().size();
        // Create the Materia
        restMateriaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(materia)))
            .andExpect(status().isCreated());

        // Validate the Materia in the database
        List<Materia> materiaList = materiaRepository.findAll();
        assertThat(materiaList).hasSize(databaseSizeBeforeCreate + 1);
        Materia testMateria = materiaList.get(materiaList.size() - 1);
        assertThat(testMateria.getIdMateriaDenominacion()).isEqualTo(DEFAULT_ID_MATERIA_DENOMINACION);
        assertThat(testMateria.getMarcaModulo()).isEqualTo(DEFAULT_MARCA_MODULO);
        assertThat(testMateria.getCargaHoraria()).isEqualTo(DEFAULT_CARGA_HORARIA);
        assertThat(testMateria.getIdOfertaEducativa()).isEqualTo(DEFAULT_ID_OFERTA_EDUCATIVA);
        assertThat(testMateria.getIdSeccion()).isEqualTo(DEFAULT_ID_SECCION);
        assertThat(testMateria.getIdTipoMateriaOpcional()).isEqualTo(DEFAULT_ID_TIPO_MATERIA_OPCIONAL);
        assertThat(testMateria.getOrden()).isEqualTo(DEFAULT_ORDEN);
        assertThat(testMateria.getIdArea()).isEqualTo(DEFAULT_ID_AREA);
        assertThat(testMateria.getIdAsignatira()).isEqualTo(DEFAULT_ID_ASIGNATIRA);
        assertThat(testMateria.getCodigosChequeados()).isEqualTo(DEFAULT_CODIGOS_CHEQUEADOS);
        assertThat(testMateria.getObligatoriedad()).isEqualTo(DEFAULT_OBLIGATORIEDAD);
        assertThat(testMateria.getUnidadCargaPedagogica()).isEqualTo(DEFAULT_UNIDAD_CARGA_PEDAGOGICA);
    }

    @Test
    @Transactional
    void createMateriaWithExistingId() throws Exception {
        // Create the Materia with an existing ID
        materia.setId(1L);

        int databaseSizeBeforeCreate = materiaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMateriaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(materia)))
            .andExpect(status().isBadRequest());

        // Validate the Materia in the database
        List<Materia> materiaList = materiaRepository.findAll();
        assertThat(materiaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllMaterias() throws Exception {
        // Initialize the database
        materiaRepository.saveAndFlush(materia);

        // Get all the materiaList
        restMateriaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(materia.getId().intValue())))
            .andExpect(jsonPath("$.[*].idMateriaDenominacion").value(hasItem(DEFAULT_ID_MATERIA_DENOMINACION.intValue())))
            .andExpect(jsonPath("$.[*].marcaModulo").value(hasItem(DEFAULT_MARCA_MODULO)))
            .andExpect(jsonPath("$.[*].cargaHoraria").value(hasItem(DEFAULT_CARGA_HORARIA.intValue())))
            .andExpect(jsonPath("$.[*].idOfertaEducativa").value(hasItem(DEFAULT_ID_OFERTA_EDUCATIVA.intValue())))
            .andExpect(jsonPath("$.[*].idSeccion").value(hasItem(DEFAULT_ID_SECCION.intValue())))
            .andExpect(jsonPath("$.[*].idTipoMateriaOpcional").value(hasItem(DEFAULT_ID_TIPO_MATERIA_OPCIONAL.intValue())))
            .andExpect(jsonPath("$.[*].orden").value(hasItem(DEFAULT_ORDEN.intValue())))
            .andExpect(jsonPath("$.[*].idArea").value(hasItem(DEFAULT_ID_AREA.intValue())))
            .andExpect(jsonPath("$.[*].idAsignatira").value(hasItem(DEFAULT_ID_ASIGNATIRA.intValue())))
            .andExpect(jsonPath("$.[*].codigosChequeados").value(hasItem(DEFAULT_CODIGOS_CHEQUEADOS.intValue())))
            .andExpect(jsonPath("$.[*].obligatoriedad").value(hasItem(DEFAULT_OBLIGATORIEDAD.intValue())))
            .andExpect(jsonPath("$.[*].unidadCargaPedagogica").value(hasItem(DEFAULT_UNIDAD_CARGA_PEDAGOGICA.intValue())));
    }

    @Test
    @Transactional
    void getMateria() throws Exception {
        // Initialize the database
        materiaRepository.saveAndFlush(materia);

        // Get the materia
        restMateriaMockMvc
            .perform(get(ENTITY_API_URL_ID, materia.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(materia.getId().intValue()))
            .andExpect(jsonPath("$.idMateriaDenominacion").value(DEFAULT_ID_MATERIA_DENOMINACION.intValue()))
            .andExpect(jsonPath("$.marcaModulo").value(DEFAULT_MARCA_MODULO))
            .andExpect(jsonPath("$.cargaHoraria").value(DEFAULT_CARGA_HORARIA.intValue()))
            .andExpect(jsonPath("$.idOfertaEducativa").value(DEFAULT_ID_OFERTA_EDUCATIVA.intValue()))
            .andExpect(jsonPath("$.idSeccion").value(DEFAULT_ID_SECCION.intValue()))
            .andExpect(jsonPath("$.idTipoMateriaOpcional").value(DEFAULT_ID_TIPO_MATERIA_OPCIONAL.intValue()))
            .andExpect(jsonPath("$.orden").value(DEFAULT_ORDEN.intValue()))
            .andExpect(jsonPath("$.idArea").value(DEFAULT_ID_AREA.intValue()))
            .andExpect(jsonPath("$.idAsignatira").value(DEFAULT_ID_ASIGNATIRA.intValue()))
            .andExpect(jsonPath("$.codigosChequeados").value(DEFAULT_CODIGOS_CHEQUEADOS.intValue()))
            .andExpect(jsonPath("$.obligatoriedad").value(DEFAULT_OBLIGATORIEDAD.intValue()))
            .andExpect(jsonPath("$.unidadCargaPedagogica").value(DEFAULT_UNIDAD_CARGA_PEDAGOGICA.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingMateria() throws Exception {
        // Get the materia
        restMateriaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewMateria() throws Exception {
        // Initialize the database
        materiaRepository.saveAndFlush(materia);

        int databaseSizeBeforeUpdate = materiaRepository.findAll().size();

        // Update the materia
        Materia updatedMateria = materiaRepository.findById(materia.getId()).get();
        // Disconnect from session so that the updates on updatedMateria are not directly saved in db
        em.detach(updatedMateria);
        updatedMateria
            .idMateriaDenominacion(UPDATED_ID_MATERIA_DENOMINACION)
            .marcaModulo(UPDATED_MARCA_MODULO)
            .cargaHoraria(UPDATED_CARGA_HORARIA)
            .idOfertaEducativa(UPDATED_ID_OFERTA_EDUCATIVA)
            .idSeccion(UPDATED_ID_SECCION)
            .idTipoMateriaOpcional(UPDATED_ID_TIPO_MATERIA_OPCIONAL)
            .orden(UPDATED_ORDEN)
            .idArea(UPDATED_ID_AREA)
            .idAsignatira(UPDATED_ID_ASIGNATIRA)
            .codigosChequeados(UPDATED_CODIGOS_CHEQUEADOS)
            .obligatoriedad(UPDATED_OBLIGATORIEDAD)
            .unidadCargaPedagogica(UPDATED_UNIDAD_CARGA_PEDAGOGICA);

        restMateriaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedMateria.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedMateria))
            )
            .andExpect(status().isOk());

        // Validate the Materia in the database
        List<Materia> materiaList = materiaRepository.findAll();
        assertThat(materiaList).hasSize(databaseSizeBeforeUpdate);
        Materia testMateria = materiaList.get(materiaList.size() - 1);
        assertThat(testMateria.getIdMateriaDenominacion()).isEqualTo(UPDATED_ID_MATERIA_DENOMINACION);
        assertThat(testMateria.getMarcaModulo()).isEqualTo(UPDATED_MARCA_MODULO);
        assertThat(testMateria.getCargaHoraria()).isEqualTo(UPDATED_CARGA_HORARIA);
        assertThat(testMateria.getIdOfertaEducativa()).isEqualTo(UPDATED_ID_OFERTA_EDUCATIVA);
        assertThat(testMateria.getIdSeccion()).isEqualTo(UPDATED_ID_SECCION);
        assertThat(testMateria.getIdTipoMateriaOpcional()).isEqualTo(UPDATED_ID_TIPO_MATERIA_OPCIONAL);
        assertThat(testMateria.getOrden()).isEqualTo(UPDATED_ORDEN);
        assertThat(testMateria.getIdArea()).isEqualTo(UPDATED_ID_AREA);
        assertThat(testMateria.getIdAsignatira()).isEqualTo(UPDATED_ID_ASIGNATIRA);
        assertThat(testMateria.getCodigosChequeados()).isEqualTo(UPDATED_CODIGOS_CHEQUEADOS);
        assertThat(testMateria.getObligatoriedad()).isEqualTo(UPDATED_OBLIGATORIEDAD);
        assertThat(testMateria.getUnidadCargaPedagogica()).isEqualTo(UPDATED_UNIDAD_CARGA_PEDAGOGICA);
    }

    @Test
    @Transactional
    void putNonExistingMateria() throws Exception {
        int databaseSizeBeforeUpdate = materiaRepository.findAll().size();
        materia.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMateriaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, materia.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(materia))
            )
            .andExpect(status().isBadRequest());

        // Validate the Materia in the database
        List<Materia> materiaList = materiaRepository.findAll();
        assertThat(materiaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMateria() throws Exception {
        int databaseSizeBeforeUpdate = materiaRepository.findAll().size();
        materia.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMateriaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(materia))
            )
            .andExpect(status().isBadRequest());

        // Validate the Materia in the database
        List<Materia> materiaList = materiaRepository.findAll();
        assertThat(materiaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMateria() throws Exception {
        int databaseSizeBeforeUpdate = materiaRepository.findAll().size();
        materia.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMateriaMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(materia)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Materia in the database
        List<Materia> materiaList = materiaRepository.findAll();
        assertThat(materiaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMateriaWithPatch() throws Exception {
        // Initialize the database
        materiaRepository.saveAndFlush(materia);

        int databaseSizeBeforeUpdate = materiaRepository.findAll().size();

        // Update the materia using partial update
        Materia partialUpdatedMateria = new Materia();
        partialUpdatedMateria.setId(materia.getId());

        partialUpdatedMateria
            .idMateriaDenominacion(UPDATED_ID_MATERIA_DENOMINACION)
            .marcaModulo(UPDATED_MARCA_MODULO)
            .idOfertaEducativa(UPDATED_ID_OFERTA_EDUCATIVA)
            .idSeccion(UPDATED_ID_SECCION)
            .orden(UPDATED_ORDEN)
            .idArea(UPDATED_ID_AREA)
            .codigosChequeados(UPDATED_CODIGOS_CHEQUEADOS)
            .obligatoriedad(UPDATED_OBLIGATORIEDAD)
            .unidadCargaPedagogica(UPDATED_UNIDAD_CARGA_PEDAGOGICA);

        restMateriaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMateria.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMateria))
            )
            .andExpect(status().isOk());

        // Validate the Materia in the database
        List<Materia> materiaList = materiaRepository.findAll();
        assertThat(materiaList).hasSize(databaseSizeBeforeUpdate);
        Materia testMateria = materiaList.get(materiaList.size() - 1);
        assertThat(testMateria.getIdMateriaDenominacion()).isEqualTo(UPDATED_ID_MATERIA_DENOMINACION);
        assertThat(testMateria.getMarcaModulo()).isEqualTo(UPDATED_MARCA_MODULO);
        assertThat(testMateria.getCargaHoraria()).isEqualTo(DEFAULT_CARGA_HORARIA);
        assertThat(testMateria.getIdOfertaEducativa()).isEqualTo(UPDATED_ID_OFERTA_EDUCATIVA);
        assertThat(testMateria.getIdSeccion()).isEqualTo(UPDATED_ID_SECCION);
        assertThat(testMateria.getIdTipoMateriaOpcional()).isEqualTo(DEFAULT_ID_TIPO_MATERIA_OPCIONAL);
        assertThat(testMateria.getOrden()).isEqualTo(UPDATED_ORDEN);
        assertThat(testMateria.getIdArea()).isEqualTo(UPDATED_ID_AREA);
        assertThat(testMateria.getIdAsignatira()).isEqualTo(DEFAULT_ID_ASIGNATIRA);
        assertThat(testMateria.getCodigosChequeados()).isEqualTo(UPDATED_CODIGOS_CHEQUEADOS);
        assertThat(testMateria.getObligatoriedad()).isEqualTo(UPDATED_OBLIGATORIEDAD);
        assertThat(testMateria.getUnidadCargaPedagogica()).isEqualTo(UPDATED_UNIDAD_CARGA_PEDAGOGICA);
    }

    @Test
    @Transactional
    void fullUpdateMateriaWithPatch() throws Exception {
        // Initialize the database
        materiaRepository.saveAndFlush(materia);

        int databaseSizeBeforeUpdate = materiaRepository.findAll().size();

        // Update the materia using partial update
        Materia partialUpdatedMateria = new Materia();
        partialUpdatedMateria.setId(materia.getId());

        partialUpdatedMateria
            .idMateriaDenominacion(UPDATED_ID_MATERIA_DENOMINACION)
            .marcaModulo(UPDATED_MARCA_MODULO)
            .cargaHoraria(UPDATED_CARGA_HORARIA)
            .idOfertaEducativa(UPDATED_ID_OFERTA_EDUCATIVA)
            .idSeccion(UPDATED_ID_SECCION)
            .idTipoMateriaOpcional(UPDATED_ID_TIPO_MATERIA_OPCIONAL)
            .orden(UPDATED_ORDEN)
            .idArea(UPDATED_ID_AREA)
            .idAsignatira(UPDATED_ID_ASIGNATIRA)
            .codigosChequeados(UPDATED_CODIGOS_CHEQUEADOS)
            .obligatoriedad(UPDATED_OBLIGATORIEDAD)
            .unidadCargaPedagogica(UPDATED_UNIDAD_CARGA_PEDAGOGICA);

        restMateriaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMateria.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMateria))
            )
            .andExpect(status().isOk());

        // Validate the Materia in the database
        List<Materia> materiaList = materiaRepository.findAll();
        assertThat(materiaList).hasSize(databaseSizeBeforeUpdate);
        Materia testMateria = materiaList.get(materiaList.size() - 1);
        assertThat(testMateria.getIdMateriaDenominacion()).isEqualTo(UPDATED_ID_MATERIA_DENOMINACION);
        assertThat(testMateria.getMarcaModulo()).isEqualTo(UPDATED_MARCA_MODULO);
        assertThat(testMateria.getCargaHoraria()).isEqualTo(UPDATED_CARGA_HORARIA);
        assertThat(testMateria.getIdOfertaEducativa()).isEqualTo(UPDATED_ID_OFERTA_EDUCATIVA);
        assertThat(testMateria.getIdSeccion()).isEqualTo(UPDATED_ID_SECCION);
        assertThat(testMateria.getIdTipoMateriaOpcional()).isEqualTo(UPDATED_ID_TIPO_MATERIA_OPCIONAL);
        assertThat(testMateria.getOrden()).isEqualTo(UPDATED_ORDEN);
        assertThat(testMateria.getIdArea()).isEqualTo(UPDATED_ID_AREA);
        assertThat(testMateria.getIdAsignatira()).isEqualTo(UPDATED_ID_ASIGNATIRA);
        assertThat(testMateria.getCodigosChequeados()).isEqualTo(UPDATED_CODIGOS_CHEQUEADOS);
        assertThat(testMateria.getObligatoriedad()).isEqualTo(UPDATED_OBLIGATORIEDAD);
        assertThat(testMateria.getUnidadCargaPedagogica()).isEqualTo(UPDATED_UNIDAD_CARGA_PEDAGOGICA);
    }

    @Test
    @Transactional
    void patchNonExistingMateria() throws Exception {
        int databaseSizeBeforeUpdate = materiaRepository.findAll().size();
        materia.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMateriaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, materia.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(materia))
            )
            .andExpect(status().isBadRequest());

        // Validate the Materia in the database
        List<Materia> materiaList = materiaRepository.findAll();
        assertThat(materiaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMateria() throws Exception {
        int databaseSizeBeforeUpdate = materiaRepository.findAll().size();
        materia.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMateriaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(materia))
            )
            .andExpect(status().isBadRequest());

        // Validate the Materia in the database
        List<Materia> materiaList = materiaRepository.findAll();
        assertThat(materiaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMateria() throws Exception {
        int databaseSizeBeforeUpdate = materiaRepository.findAll().size();
        materia.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMateriaMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(materia)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Materia in the database
        List<Materia> materiaList = materiaRepository.findAll();
        assertThat(materiaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMateria() throws Exception {
        // Initialize the database
        materiaRepository.saveAndFlush(materia);

        int databaseSizeBeforeDelete = materiaRepository.findAll().size();

        // Delete the materia
        restMateriaMockMvc
            .perform(delete(ENTITY_API_URL_ID, materia.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Materia> materiaList = materiaRepository.findAll();
        assertThat(materiaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
