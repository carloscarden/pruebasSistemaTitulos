package com.dgcye.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dgcye.IntegrationTest;
import com.dgcye.domain.AlumnoAnalitico;
import com.dgcye.repository.AlumnoAnaliticoRepository;
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
 * Integration tests for the {@link AlumnoAnaliticoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AlumnoAnaliticoResourceIT {

    private static final Long DEFAULT_NOTA = 1L;
    private static final Long UPDATED_NOTA = 2L;

    private static final Long DEFAULT_ID_ESTADO_ALUMNO_ANALITICO = 1L;
    private static final Long UPDATED_ID_ESTADO_ALUMNO_ANALITICO = 2L;

    private static final Long DEFAULT_ID_ALUMNO_ESTAB_OFERTA = 1L;
    private static final Long UPDATED_ID_ALUMNO_ESTAB_OFERTA = 2L;

    private static final Long DEFAULT_ID_MATERIA = 1L;
    private static final Long UPDATED_ID_MATERIA = 2L;

    private static final Long DEFAULT_ID_MES_IMP = 1L;
    private static final Long UPDATED_ID_MES_IMP = 2L;

    private static final Long DEFAULT_ID_ANO_IMP = 1L;
    private static final Long UPDATED_ID_ANO_IMP = 2L;

    private static final String DEFAULT_ESTABLECIMIENTO_IMP = "AAAAAAAAAA";
    private static final String UPDATED_ESTABLECIMIENTO_IMP = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/alumno-analiticos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AlumnoAnaliticoRepository alumnoAnaliticoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAlumnoAnaliticoMockMvc;

    private AlumnoAnalitico alumnoAnalitico;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AlumnoAnalitico createEntity(EntityManager em) {
        AlumnoAnalitico alumnoAnalitico = new AlumnoAnalitico()
            .nota(DEFAULT_NOTA)
            .idEstadoAlumnoAnalitico(DEFAULT_ID_ESTADO_ALUMNO_ANALITICO)
            .idAlumnoEstabOferta(DEFAULT_ID_ALUMNO_ESTAB_OFERTA)
            .idMateria(DEFAULT_ID_MATERIA)
            .idMesImp(DEFAULT_ID_MES_IMP)
            .idAnoImp(DEFAULT_ID_ANO_IMP)
            .establecimientoImp(DEFAULT_ESTABLECIMIENTO_IMP);
        return alumnoAnalitico;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AlumnoAnalitico createUpdatedEntity(EntityManager em) {
        AlumnoAnalitico alumnoAnalitico = new AlumnoAnalitico()
            .nota(UPDATED_NOTA)
            .idEstadoAlumnoAnalitico(UPDATED_ID_ESTADO_ALUMNO_ANALITICO)
            .idAlumnoEstabOferta(UPDATED_ID_ALUMNO_ESTAB_OFERTA)
            .idMateria(UPDATED_ID_MATERIA)
            .idMesImp(UPDATED_ID_MES_IMP)
            .idAnoImp(UPDATED_ID_ANO_IMP)
            .establecimientoImp(UPDATED_ESTABLECIMIENTO_IMP);
        return alumnoAnalitico;
    }

    @BeforeEach
    public void initTest() {
        alumnoAnalitico = createEntity(em);
    }

    @Test
    @Transactional
    void createAlumnoAnalitico() throws Exception {
        int databaseSizeBeforeCreate = alumnoAnaliticoRepository.findAll().size();
        // Create the AlumnoAnalitico
        restAlumnoAnaliticoMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(alumnoAnalitico))
            )
            .andExpect(status().isCreated());

        // Validate the AlumnoAnalitico in the database
        List<AlumnoAnalitico> alumnoAnaliticoList = alumnoAnaliticoRepository.findAll();
        assertThat(alumnoAnaliticoList).hasSize(databaseSizeBeforeCreate + 1);
        AlumnoAnalitico testAlumnoAnalitico = alumnoAnaliticoList.get(alumnoAnaliticoList.size() - 1);
        assertThat(testAlumnoAnalitico.getNota()).isEqualTo(DEFAULT_NOTA);
        assertThat(testAlumnoAnalitico.getIdEstadoAlumnoAnalitico()).isEqualTo(DEFAULT_ID_ESTADO_ALUMNO_ANALITICO);
        assertThat(testAlumnoAnalitico.getIdAlumnoEstabOferta()).isEqualTo(DEFAULT_ID_ALUMNO_ESTAB_OFERTA);
        assertThat(testAlumnoAnalitico.getIdMateria()).isEqualTo(DEFAULT_ID_MATERIA);
        assertThat(testAlumnoAnalitico.getIdMesImp()).isEqualTo(DEFAULT_ID_MES_IMP);
        assertThat(testAlumnoAnalitico.getIdAnoImp()).isEqualTo(DEFAULT_ID_ANO_IMP);
        assertThat(testAlumnoAnalitico.getEstablecimientoImp()).isEqualTo(DEFAULT_ESTABLECIMIENTO_IMP);
    }

    @Test
    @Transactional
    void createAlumnoAnaliticoWithExistingId() throws Exception {
        // Create the AlumnoAnalitico with an existing ID
        alumnoAnalitico.setId(1L);

        int databaseSizeBeforeCreate = alumnoAnaliticoRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAlumnoAnaliticoMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(alumnoAnalitico))
            )
            .andExpect(status().isBadRequest());

        // Validate the AlumnoAnalitico in the database
        List<AlumnoAnalitico> alumnoAnaliticoList = alumnoAnaliticoRepository.findAll();
        assertThat(alumnoAnaliticoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAlumnoAnaliticos() throws Exception {
        // Initialize the database
        alumnoAnaliticoRepository.saveAndFlush(alumnoAnalitico);

        // Get all the alumnoAnaliticoList
        restAlumnoAnaliticoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(alumnoAnalitico.getId().intValue())))
            .andExpect(jsonPath("$.[*].nota").value(hasItem(DEFAULT_NOTA.intValue())))
            .andExpect(jsonPath("$.[*].idEstadoAlumnoAnalitico").value(hasItem(DEFAULT_ID_ESTADO_ALUMNO_ANALITICO.intValue())))
            .andExpect(jsonPath("$.[*].idAlumnoEstabOferta").value(hasItem(DEFAULT_ID_ALUMNO_ESTAB_OFERTA.intValue())))
            .andExpect(jsonPath("$.[*].idMateria").value(hasItem(DEFAULT_ID_MATERIA.intValue())))
            .andExpect(jsonPath("$.[*].idMesImp").value(hasItem(DEFAULT_ID_MES_IMP.intValue())))
            .andExpect(jsonPath("$.[*].idAnoImp").value(hasItem(DEFAULT_ID_ANO_IMP.intValue())))
            .andExpect(jsonPath("$.[*].establecimientoImp").value(hasItem(DEFAULT_ESTABLECIMIENTO_IMP)));
    }

    @Test
    @Transactional
    void getAlumnoAnalitico() throws Exception {
        // Initialize the database
        alumnoAnaliticoRepository.saveAndFlush(alumnoAnalitico);

        // Get the alumnoAnalitico
        restAlumnoAnaliticoMockMvc
            .perform(get(ENTITY_API_URL_ID, alumnoAnalitico.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(alumnoAnalitico.getId().intValue()))
            .andExpect(jsonPath("$.nota").value(DEFAULT_NOTA.intValue()))
            .andExpect(jsonPath("$.idEstadoAlumnoAnalitico").value(DEFAULT_ID_ESTADO_ALUMNO_ANALITICO.intValue()))
            .andExpect(jsonPath("$.idAlumnoEstabOferta").value(DEFAULT_ID_ALUMNO_ESTAB_OFERTA.intValue()))
            .andExpect(jsonPath("$.idMateria").value(DEFAULT_ID_MATERIA.intValue()))
            .andExpect(jsonPath("$.idMesImp").value(DEFAULT_ID_MES_IMP.intValue()))
            .andExpect(jsonPath("$.idAnoImp").value(DEFAULT_ID_ANO_IMP.intValue()))
            .andExpect(jsonPath("$.establecimientoImp").value(DEFAULT_ESTABLECIMIENTO_IMP));
    }

    @Test
    @Transactional
    void getNonExistingAlumnoAnalitico() throws Exception {
        // Get the alumnoAnalitico
        restAlumnoAnaliticoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAlumnoAnalitico() throws Exception {
        // Initialize the database
        alumnoAnaliticoRepository.saveAndFlush(alumnoAnalitico);

        int databaseSizeBeforeUpdate = alumnoAnaliticoRepository.findAll().size();

        // Update the alumnoAnalitico
        AlumnoAnalitico updatedAlumnoAnalitico = alumnoAnaliticoRepository.findById(alumnoAnalitico.getId()).get();
        // Disconnect from session so that the updates on updatedAlumnoAnalitico are not directly saved in db
        em.detach(updatedAlumnoAnalitico);
        updatedAlumnoAnalitico
            .nota(UPDATED_NOTA)
            .idEstadoAlumnoAnalitico(UPDATED_ID_ESTADO_ALUMNO_ANALITICO)
            .idAlumnoEstabOferta(UPDATED_ID_ALUMNO_ESTAB_OFERTA)
            .idMateria(UPDATED_ID_MATERIA)
            .idMesImp(UPDATED_ID_MES_IMP)
            .idAnoImp(UPDATED_ID_ANO_IMP)
            .establecimientoImp(UPDATED_ESTABLECIMIENTO_IMP);

        restAlumnoAnaliticoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAlumnoAnalitico.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAlumnoAnalitico))
            )
            .andExpect(status().isOk());

        // Validate the AlumnoAnalitico in the database
        List<AlumnoAnalitico> alumnoAnaliticoList = alumnoAnaliticoRepository.findAll();
        assertThat(alumnoAnaliticoList).hasSize(databaseSizeBeforeUpdate);
        AlumnoAnalitico testAlumnoAnalitico = alumnoAnaliticoList.get(alumnoAnaliticoList.size() - 1);
        assertThat(testAlumnoAnalitico.getNota()).isEqualTo(UPDATED_NOTA);
        assertThat(testAlumnoAnalitico.getIdEstadoAlumnoAnalitico()).isEqualTo(UPDATED_ID_ESTADO_ALUMNO_ANALITICO);
        assertThat(testAlumnoAnalitico.getIdAlumnoEstabOferta()).isEqualTo(UPDATED_ID_ALUMNO_ESTAB_OFERTA);
        assertThat(testAlumnoAnalitico.getIdMateria()).isEqualTo(UPDATED_ID_MATERIA);
        assertThat(testAlumnoAnalitico.getIdMesImp()).isEqualTo(UPDATED_ID_MES_IMP);
        assertThat(testAlumnoAnalitico.getIdAnoImp()).isEqualTo(UPDATED_ID_ANO_IMP);
        assertThat(testAlumnoAnalitico.getEstablecimientoImp()).isEqualTo(UPDATED_ESTABLECIMIENTO_IMP);
    }

    @Test
    @Transactional
    void putNonExistingAlumnoAnalitico() throws Exception {
        int databaseSizeBeforeUpdate = alumnoAnaliticoRepository.findAll().size();
        alumnoAnalitico.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAlumnoAnaliticoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, alumnoAnalitico.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(alumnoAnalitico))
            )
            .andExpect(status().isBadRequest());

        // Validate the AlumnoAnalitico in the database
        List<AlumnoAnalitico> alumnoAnaliticoList = alumnoAnaliticoRepository.findAll();
        assertThat(alumnoAnaliticoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAlumnoAnalitico() throws Exception {
        int databaseSizeBeforeUpdate = alumnoAnaliticoRepository.findAll().size();
        alumnoAnalitico.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAlumnoAnaliticoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(alumnoAnalitico))
            )
            .andExpect(status().isBadRequest());

        // Validate the AlumnoAnalitico in the database
        List<AlumnoAnalitico> alumnoAnaliticoList = alumnoAnaliticoRepository.findAll();
        assertThat(alumnoAnaliticoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAlumnoAnalitico() throws Exception {
        int databaseSizeBeforeUpdate = alumnoAnaliticoRepository.findAll().size();
        alumnoAnalitico.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAlumnoAnaliticoMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(alumnoAnalitico))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AlumnoAnalitico in the database
        List<AlumnoAnalitico> alumnoAnaliticoList = alumnoAnaliticoRepository.findAll();
        assertThat(alumnoAnaliticoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAlumnoAnaliticoWithPatch() throws Exception {
        // Initialize the database
        alumnoAnaliticoRepository.saveAndFlush(alumnoAnalitico);

        int databaseSizeBeforeUpdate = alumnoAnaliticoRepository.findAll().size();

        // Update the alumnoAnalitico using partial update
        AlumnoAnalitico partialUpdatedAlumnoAnalitico = new AlumnoAnalitico();
        partialUpdatedAlumnoAnalitico.setId(alumnoAnalitico.getId());

        partialUpdatedAlumnoAnalitico
            .nota(UPDATED_NOTA)
            .idEstadoAlumnoAnalitico(UPDATED_ID_ESTADO_ALUMNO_ANALITICO)
            .idAlumnoEstabOferta(UPDATED_ID_ALUMNO_ESTAB_OFERTA)
            .idMateria(UPDATED_ID_MATERIA)
            .idMesImp(UPDATED_ID_MES_IMP)
            .idAnoImp(UPDATED_ID_ANO_IMP)
            .establecimientoImp(UPDATED_ESTABLECIMIENTO_IMP);

        restAlumnoAnaliticoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAlumnoAnalitico.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAlumnoAnalitico))
            )
            .andExpect(status().isOk());

        // Validate the AlumnoAnalitico in the database
        List<AlumnoAnalitico> alumnoAnaliticoList = alumnoAnaliticoRepository.findAll();
        assertThat(alumnoAnaliticoList).hasSize(databaseSizeBeforeUpdate);
        AlumnoAnalitico testAlumnoAnalitico = alumnoAnaliticoList.get(alumnoAnaliticoList.size() - 1);
        assertThat(testAlumnoAnalitico.getNota()).isEqualTo(UPDATED_NOTA);
        assertThat(testAlumnoAnalitico.getIdEstadoAlumnoAnalitico()).isEqualTo(UPDATED_ID_ESTADO_ALUMNO_ANALITICO);
        assertThat(testAlumnoAnalitico.getIdAlumnoEstabOferta()).isEqualTo(UPDATED_ID_ALUMNO_ESTAB_OFERTA);
        assertThat(testAlumnoAnalitico.getIdMateria()).isEqualTo(UPDATED_ID_MATERIA);
        assertThat(testAlumnoAnalitico.getIdMesImp()).isEqualTo(UPDATED_ID_MES_IMP);
        assertThat(testAlumnoAnalitico.getIdAnoImp()).isEqualTo(UPDATED_ID_ANO_IMP);
        assertThat(testAlumnoAnalitico.getEstablecimientoImp()).isEqualTo(UPDATED_ESTABLECIMIENTO_IMP);
    }

    @Test
    @Transactional
    void fullUpdateAlumnoAnaliticoWithPatch() throws Exception {
        // Initialize the database
        alumnoAnaliticoRepository.saveAndFlush(alumnoAnalitico);

        int databaseSizeBeforeUpdate = alumnoAnaliticoRepository.findAll().size();

        // Update the alumnoAnalitico using partial update
        AlumnoAnalitico partialUpdatedAlumnoAnalitico = new AlumnoAnalitico();
        partialUpdatedAlumnoAnalitico.setId(alumnoAnalitico.getId());

        partialUpdatedAlumnoAnalitico
            .nota(UPDATED_NOTA)
            .idEstadoAlumnoAnalitico(UPDATED_ID_ESTADO_ALUMNO_ANALITICO)
            .idAlumnoEstabOferta(UPDATED_ID_ALUMNO_ESTAB_OFERTA)
            .idMateria(UPDATED_ID_MATERIA)
            .idMesImp(UPDATED_ID_MES_IMP)
            .idAnoImp(UPDATED_ID_ANO_IMP)
            .establecimientoImp(UPDATED_ESTABLECIMIENTO_IMP);

        restAlumnoAnaliticoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAlumnoAnalitico.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAlumnoAnalitico))
            )
            .andExpect(status().isOk());

        // Validate the AlumnoAnalitico in the database
        List<AlumnoAnalitico> alumnoAnaliticoList = alumnoAnaliticoRepository.findAll();
        assertThat(alumnoAnaliticoList).hasSize(databaseSizeBeforeUpdate);
        AlumnoAnalitico testAlumnoAnalitico = alumnoAnaliticoList.get(alumnoAnaliticoList.size() - 1);
        assertThat(testAlumnoAnalitico.getNota()).isEqualTo(UPDATED_NOTA);
        assertThat(testAlumnoAnalitico.getIdEstadoAlumnoAnalitico()).isEqualTo(UPDATED_ID_ESTADO_ALUMNO_ANALITICO);
        assertThat(testAlumnoAnalitico.getIdAlumnoEstabOferta()).isEqualTo(UPDATED_ID_ALUMNO_ESTAB_OFERTA);
        assertThat(testAlumnoAnalitico.getIdMateria()).isEqualTo(UPDATED_ID_MATERIA);
        assertThat(testAlumnoAnalitico.getIdMesImp()).isEqualTo(UPDATED_ID_MES_IMP);
        assertThat(testAlumnoAnalitico.getIdAnoImp()).isEqualTo(UPDATED_ID_ANO_IMP);
        assertThat(testAlumnoAnalitico.getEstablecimientoImp()).isEqualTo(UPDATED_ESTABLECIMIENTO_IMP);
    }

    @Test
    @Transactional
    void patchNonExistingAlumnoAnalitico() throws Exception {
        int databaseSizeBeforeUpdate = alumnoAnaliticoRepository.findAll().size();
        alumnoAnalitico.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAlumnoAnaliticoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, alumnoAnalitico.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(alumnoAnalitico))
            )
            .andExpect(status().isBadRequest());

        // Validate the AlumnoAnalitico in the database
        List<AlumnoAnalitico> alumnoAnaliticoList = alumnoAnaliticoRepository.findAll();
        assertThat(alumnoAnaliticoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAlumnoAnalitico() throws Exception {
        int databaseSizeBeforeUpdate = alumnoAnaliticoRepository.findAll().size();
        alumnoAnalitico.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAlumnoAnaliticoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(alumnoAnalitico))
            )
            .andExpect(status().isBadRequest());

        // Validate the AlumnoAnalitico in the database
        List<AlumnoAnalitico> alumnoAnaliticoList = alumnoAnaliticoRepository.findAll();
        assertThat(alumnoAnaliticoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAlumnoAnalitico() throws Exception {
        int databaseSizeBeforeUpdate = alumnoAnaliticoRepository.findAll().size();
        alumnoAnalitico.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAlumnoAnaliticoMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(alumnoAnalitico))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AlumnoAnalitico in the database
        List<AlumnoAnalitico> alumnoAnaliticoList = alumnoAnaliticoRepository.findAll();
        assertThat(alumnoAnaliticoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAlumnoAnalitico() throws Exception {
        // Initialize the database
        alumnoAnaliticoRepository.saveAndFlush(alumnoAnalitico);

        int databaseSizeBeforeDelete = alumnoAnaliticoRepository.findAll().size();

        // Delete the alumnoAnalitico
        restAlumnoAnaliticoMockMvc
            .perform(delete(ENTITY_API_URL_ID, alumnoAnalitico.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AlumnoAnalitico> alumnoAnaliticoList = alumnoAnaliticoRepository.findAll();
        assertThat(alumnoAnaliticoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
