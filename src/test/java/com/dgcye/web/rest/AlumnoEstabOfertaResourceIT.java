package com.dgcye.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dgcye.IntegrationTest;
import com.dgcye.domain.AlumnoEstabOferta;
import com.dgcye.repository.AlumnoEstabOfertaRepository;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
 * Integration tests for the {@link AlumnoEstabOfertaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AlumnoEstabOfertaResourceIT {

    private static final Long DEFAULT_ID_SER = 1L;
    private static final Long UPDATED_ID_SER = 2L;

    private static final Long DEFAULT_ID_OFERTA_EDUCATIVA = 1L;
    private static final Long UPDATED_ID_OFERTA_EDUCATIVA = 2L;

    private static final Long DEFAULT_ID_ALUMNO = 1L;
    private static final Long UPDATED_ID_ALUMNO = 2L;

    private static final Long DEFAULT_ID_ESTADO_ALUMNO_ESTAB_OFERTA = 1L;
    private static final Long UPDATED_ID_ESTADO_ALUMNO_ESTAB_OFERTA = 2L;

    private static final Instant DEFAULT_FECHA_INICIO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FECHA_INICIO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_FECHA_FIN = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FECHA_FIN = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/alumno-estab-ofertas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AlumnoEstabOfertaRepository alumnoEstabOfertaRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAlumnoEstabOfertaMockMvc;

    private AlumnoEstabOferta alumnoEstabOferta;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AlumnoEstabOferta createEntity(EntityManager em) {
        AlumnoEstabOferta alumnoEstabOferta = new AlumnoEstabOferta()
            .idSer(DEFAULT_ID_SER)
            .idOfertaEducativa(DEFAULT_ID_OFERTA_EDUCATIVA)
            .idAlumno(DEFAULT_ID_ALUMNO)
            .idEstadoAlumnoEstabOferta(DEFAULT_ID_ESTADO_ALUMNO_ESTAB_OFERTA)
            .fechaInicio(DEFAULT_FECHA_INICIO)
            .fechaFin(DEFAULT_FECHA_FIN);
        return alumnoEstabOferta;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AlumnoEstabOferta createUpdatedEntity(EntityManager em) {
        AlumnoEstabOferta alumnoEstabOferta = new AlumnoEstabOferta()
            .idSer(UPDATED_ID_SER)
            .idOfertaEducativa(UPDATED_ID_OFERTA_EDUCATIVA)
            .idAlumno(UPDATED_ID_ALUMNO)
            .idEstadoAlumnoEstabOferta(UPDATED_ID_ESTADO_ALUMNO_ESTAB_OFERTA)
            .fechaInicio(UPDATED_FECHA_INICIO)
            .fechaFin(UPDATED_FECHA_FIN);
        return alumnoEstabOferta;
    }

    @BeforeEach
    public void initTest() {
        alumnoEstabOferta = createEntity(em);
    }

    @Test
    @Transactional
    void createAlumnoEstabOferta() throws Exception {
        int databaseSizeBeforeCreate = alumnoEstabOfertaRepository.findAll().size();
        // Create the AlumnoEstabOferta
        restAlumnoEstabOfertaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(alumnoEstabOferta))
            )
            .andExpect(status().isCreated());

        // Validate the AlumnoEstabOferta in the database
        List<AlumnoEstabOferta> alumnoEstabOfertaList = alumnoEstabOfertaRepository.findAll();
        assertThat(alumnoEstabOfertaList).hasSize(databaseSizeBeforeCreate + 1);
        AlumnoEstabOferta testAlumnoEstabOferta = alumnoEstabOfertaList.get(alumnoEstabOfertaList.size() - 1);
        assertThat(testAlumnoEstabOferta.getIdSer()).isEqualTo(DEFAULT_ID_SER);
        assertThat(testAlumnoEstabOferta.getIdOfertaEducativa()).isEqualTo(DEFAULT_ID_OFERTA_EDUCATIVA);
        assertThat(testAlumnoEstabOferta.getIdAlumno()).isEqualTo(DEFAULT_ID_ALUMNO);
        assertThat(testAlumnoEstabOferta.getIdEstadoAlumnoEstabOferta()).isEqualTo(DEFAULT_ID_ESTADO_ALUMNO_ESTAB_OFERTA);
        assertThat(testAlumnoEstabOferta.getFechaInicio()).isEqualTo(DEFAULT_FECHA_INICIO);
        assertThat(testAlumnoEstabOferta.getFechaFin()).isEqualTo(DEFAULT_FECHA_FIN);
    }

    @Test
    @Transactional
    void createAlumnoEstabOfertaWithExistingId() throws Exception {
        // Create the AlumnoEstabOferta with an existing ID
        alumnoEstabOferta.setId(1L);

        int databaseSizeBeforeCreate = alumnoEstabOfertaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAlumnoEstabOfertaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(alumnoEstabOferta))
            )
            .andExpect(status().isBadRequest());

        // Validate the AlumnoEstabOferta in the database
        List<AlumnoEstabOferta> alumnoEstabOfertaList = alumnoEstabOfertaRepository.findAll();
        assertThat(alumnoEstabOfertaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAlumnoEstabOfertas() throws Exception {
        // Initialize the database
        alumnoEstabOfertaRepository.saveAndFlush(alumnoEstabOferta);

        // Get all the alumnoEstabOfertaList
        restAlumnoEstabOfertaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(alumnoEstabOferta.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSer").value(hasItem(DEFAULT_ID_SER.intValue())))
            .andExpect(jsonPath("$.[*].idOfertaEducativa").value(hasItem(DEFAULT_ID_OFERTA_EDUCATIVA.intValue())))
            .andExpect(jsonPath("$.[*].idAlumno").value(hasItem(DEFAULT_ID_ALUMNO.intValue())))
            .andExpect(jsonPath("$.[*].idEstadoAlumnoEstabOferta").value(hasItem(DEFAULT_ID_ESTADO_ALUMNO_ESTAB_OFERTA.intValue())))
            .andExpect(jsonPath("$.[*].fechaInicio").value(hasItem(DEFAULT_FECHA_INICIO.toString())))
            .andExpect(jsonPath("$.[*].fechaFin").value(hasItem(DEFAULT_FECHA_FIN.toString())));
    }

    @Test
    @Transactional
    void getAlumnoEstabOferta() throws Exception {
        // Initialize the database
        alumnoEstabOfertaRepository.saveAndFlush(alumnoEstabOferta);

        // Get the alumnoEstabOferta
        restAlumnoEstabOfertaMockMvc
            .perform(get(ENTITY_API_URL_ID, alumnoEstabOferta.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(alumnoEstabOferta.getId().intValue()))
            .andExpect(jsonPath("$.idSer").value(DEFAULT_ID_SER.intValue()))
            .andExpect(jsonPath("$.idOfertaEducativa").value(DEFAULT_ID_OFERTA_EDUCATIVA.intValue()))
            .andExpect(jsonPath("$.idAlumno").value(DEFAULT_ID_ALUMNO.intValue()))
            .andExpect(jsonPath("$.idEstadoAlumnoEstabOferta").value(DEFAULT_ID_ESTADO_ALUMNO_ESTAB_OFERTA.intValue()))
            .andExpect(jsonPath("$.fechaInicio").value(DEFAULT_FECHA_INICIO.toString()))
            .andExpect(jsonPath("$.fechaFin").value(DEFAULT_FECHA_FIN.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAlumnoEstabOferta() throws Exception {
        // Get the alumnoEstabOferta
        restAlumnoEstabOfertaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAlumnoEstabOferta() throws Exception {
        // Initialize the database
        alumnoEstabOfertaRepository.saveAndFlush(alumnoEstabOferta);

        int databaseSizeBeforeUpdate = alumnoEstabOfertaRepository.findAll().size();

        // Update the alumnoEstabOferta
        AlumnoEstabOferta updatedAlumnoEstabOferta = alumnoEstabOfertaRepository.findById(alumnoEstabOferta.getId()).get();
        // Disconnect from session so that the updates on updatedAlumnoEstabOferta are not directly saved in db
        em.detach(updatedAlumnoEstabOferta);
        updatedAlumnoEstabOferta
            .idSer(UPDATED_ID_SER)
            .idOfertaEducativa(UPDATED_ID_OFERTA_EDUCATIVA)
            .idAlumno(UPDATED_ID_ALUMNO)
            .idEstadoAlumnoEstabOferta(UPDATED_ID_ESTADO_ALUMNO_ESTAB_OFERTA)
            .fechaInicio(UPDATED_FECHA_INICIO)
            .fechaFin(UPDATED_FECHA_FIN);

        restAlumnoEstabOfertaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAlumnoEstabOferta.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAlumnoEstabOferta))
            )
            .andExpect(status().isOk());

        // Validate the AlumnoEstabOferta in the database
        List<AlumnoEstabOferta> alumnoEstabOfertaList = alumnoEstabOfertaRepository.findAll();
        assertThat(alumnoEstabOfertaList).hasSize(databaseSizeBeforeUpdate);
        AlumnoEstabOferta testAlumnoEstabOferta = alumnoEstabOfertaList.get(alumnoEstabOfertaList.size() - 1);
        assertThat(testAlumnoEstabOferta.getIdSer()).isEqualTo(UPDATED_ID_SER);
        assertThat(testAlumnoEstabOferta.getIdOfertaEducativa()).isEqualTo(UPDATED_ID_OFERTA_EDUCATIVA);
        assertThat(testAlumnoEstabOferta.getIdAlumno()).isEqualTo(UPDATED_ID_ALUMNO);
        assertThat(testAlumnoEstabOferta.getIdEstadoAlumnoEstabOferta()).isEqualTo(UPDATED_ID_ESTADO_ALUMNO_ESTAB_OFERTA);
        assertThat(testAlumnoEstabOferta.getFechaInicio()).isEqualTo(UPDATED_FECHA_INICIO);
        assertThat(testAlumnoEstabOferta.getFechaFin()).isEqualTo(UPDATED_FECHA_FIN);
    }

    @Test
    @Transactional
    void putNonExistingAlumnoEstabOferta() throws Exception {
        int databaseSizeBeforeUpdate = alumnoEstabOfertaRepository.findAll().size();
        alumnoEstabOferta.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAlumnoEstabOfertaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, alumnoEstabOferta.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(alumnoEstabOferta))
            )
            .andExpect(status().isBadRequest());

        // Validate the AlumnoEstabOferta in the database
        List<AlumnoEstabOferta> alumnoEstabOfertaList = alumnoEstabOfertaRepository.findAll();
        assertThat(alumnoEstabOfertaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAlumnoEstabOferta() throws Exception {
        int databaseSizeBeforeUpdate = alumnoEstabOfertaRepository.findAll().size();
        alumnoEstabOferta.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAlumnoEstabOfertaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(alumnoEstabOferta))
            )
            .andExpect(status().isBadRequest());

        // Validate the AlumnoEstabOferta in the database
        List<AlumnoEstabOferta> alumnoEstabOfertaList = alumnoEstabOfertaRepository.findAll();
        assertThat(alumnoEstabOfertaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAlumnoEstabOferta() throws Exception {
        int databaseSizeBeforeUpdate = alumnoEstabOfertaRepository.findAll().size();
        alumnoEstabOferta.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAlumnoEstabOfertaMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(alumnoEstabOferta))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AlumnoEstabOferta in the database
        List<AlumnoEstabOferta> alumnoEstabOfertaList = alumnoEstabOfertaRepository.findAll();
        assertThat(alumnoEstabOfertaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAlumnoEstabOfertaWithPatch() throws Exception {
        // Initialize the database
        alumnoEstabOfertaRepository.saveAndFlush(alumnoEstabOferta);

        int databaseSizeBeforeUpdate = alumnoEstabOfertaRepository.findAll().size();

        // Update the alumnoEstabOferta using partial update
        AlumnoEstabOferta partialUpdatedAlumnoEstabOferta = new AlumnoEstabOferta();
        partialUpdatedAlumnoEstabOferta.setId(alumnoEstabOferta.getId());

        partialUpdatedAlumnoEstabOferta.idOfertaEducativa(UPDATED_ID_OFERTA_EDUCATIVA);

        restAlumnoEstabOfertaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAlumnoEstabOferta.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAlumnoEstabOferta))
            )
            .andExpect(status().isOk());

        // Validate the AlumnoEstabOferta in the database
        List<AlumnoEstabOferta> alumnoEstabOfertaList = alumnoEstabOfertaRepository.findAll();
        assertThat(alumnoEstabOfertaList).hasSize(databaseSizeBeforeUpdate);
        AlumnoEstabOferta testAlumnoEstabOferta = alumnoEstabOfertaList.get(alumnoEstabOfertaList.size() - 1);
        assertThat(testAlumnoEstabOferta.getIdSer()).isEqualTo(DEFAULT_ID_SER);
        assertThat(testAlumnoEstabOferta.getIdOfertaEducativa()).isEqualTo(UPDATED_ID_OFERTA_EDUCATIVA);
        assertThat(testAlumnoEstabOferta.getIdAlumno()).isEqualTo(DEFAULT_ID_ALUMNO);
        assertThat(testAlumnoEstabOferta.getIdEstadoAlumnoEstabOferta()).isEqualTo(DEFAULT_ID_ESTADO_ALUMNO_ESTAB_OFERTA);
        assertThat(testAlumnoEstabOferta.getFechaInicio()).isEqualTo(DEFAULT_FECHA_INICIO);
        assertThat(testAlumnoEstabOferta.getFechaFin()).isEqualTo(DEFAULT_FECHA_FIN);
    }

    @Test
    @Transactional
    void fullUpdateAlumnoEstabOfertaWithPatch() throws Exception {
        // Initialize the database
        alumnoEstabOfertaRepository.saveAndFlush(alumnoEstabOferta);

        int databaseSizeBeforeUpdate = alumnoEstabOfertaRepository.findAll().size();

        // Update the alumnoEstabOferta using partial update
        AlumnoEstabOferta partialUpdatedAlumnoEstabOferta = new AlumnoEstabOferta();
        partialUpdatedAlumnoEstabOferta.setId(alumnoEstabOferta.getId());

        partialUpdatedAlumnoEstabOferta
            .idSer(UPDATED_ID_SER)
            .idOfertaEducativa(UPDATED_ID_OFERTA_EDUCATIVA)
            .idAlumno(UPDATED_ID_ALUMNO)
            .idEstadoAlumnoEstabOferta(UPDATED_ID_ESTADO_ALUMNO_ESTAB_OFERTA)
            .fechaInicio(UPDATED_FECHA_INICIO)
            .fechaFin(UPDATED_FECHA_FIN);

        restAlumnoEstabOfertaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAlumnoEstabOferta.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAlumnoEstabOferta))
            )
            .andExpect(status().isOk());

        // Validate the AlumnoEstabOferta in the database
        List<AlumnoEstabOferta> alumnoEstabOfertaList = alumnoEstabOfertaRepository.findAll();
        assertThat(alumnoEstabOfertaList).hasSize(databaseSizeBeforeUpdate);
        AlumnoEstabOferta testAlumnoEstabOferta = alumnoEstabOfertaList.get(alumnoEstabOfertaList.size() - 1);
        assertThat(testAlumnoEstabOferta.getIdSer()).isEqualTo(UPDATED_ID_SER);
        assertThat(testAlumnoEstabOferta.getIdOfertaEducativa()).isEqualTo(UPDATED_ID_OFERTA_EDUCATIVA);
        assertThat(testAlumnoEstabOferta.getIdAlumno()).isEqualTo(UPDATED_ID_ALUMNO);
        assertThat(testAlumnoEstabOferta.getIdEstadoAlumnoEstabOferta()).isEqualTo(UPDATED_ID_ESTADO_ALUMNO_ESTAB_OFERTA);
        assertThat(testAlumnoEstabOferta.getFechaInicio()).isEqualTo(UPDATED_FECHA_INICIO);
        assertThat(testAlumnoEstabOferta.getFechaFin()).isEqualTo(UPDATED_FECHA_FIN);
    }

    @Test
    @Transactional
    void patchNonExistingAlumnoEstabOferta() throws Exception {
        int databaseSizeBeforeUpdate = alumnoEstabOfertaRepository.findAll().size();
        alumnoEstabOferta.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAlumnoEstabOfertaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, alumnoEstabOferta.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(alumnoEstabOferta))
            )
            .andExpect(status().isBadRequest());

        // Validate the AlumnoEstabOferta in the database
        List<AlumnoEstabOferta> alumnoEstabOfertaList = alumnoEstabOfertaRepository.findAll();
        assertThat(alumnoEstabOfertaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAlumnoEstabOferta() throws Exception {
        int databaseSizeBeforeUpdate = alumnoEstabOfertaRepository.findAll().size();
        alumnoEstabOferta.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAlumnoEstabOfertaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(alumnoEstabOferta))
            )
            .andExpect(status().isBadRequest());

        // Validate the AlumnoEstabOferta in the database
        List<AlumnoEstabOferta> alumnoEstabOfertaList = alumnoEstabOfertaRepository.findAll();
        assertThat(alumnoEstabOfertaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAlumnoEstabOferta() throws Exception {
        int databaseSizeBeforeUpdate = alumnoEstabOfertaRepository.findAll().size();
        alumnoEstabOferta.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAlumnoEstabOfertaMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(alumnoEstabOferta))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AlumnoEstabOferta in the database
        List<AlumnoEstabOferta> alumnoEstabOfertaList = alumnoEstabOfertaRepository.findAll();
        assertThat(alumnoEstabOfertaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAlumnoEstabOferta() throws Exception {
        // Initialize the database
        alumnoEstabOfertaRepository.saveAndFlush(alumnoEstabOferta);

        int databaseSizeBeforeDelete = alumnoEstabOfertaRepository.findAll().size();

        // Delete the alumnoEstabOferta
        restAlumnoEstabOfertaMockMvc
            .perform(delete(ENTITY_API_URL_ID, alumnoEstabOferta.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AlumnoEstabOferta> alumnoEstabOfertaList = alumnoEstabOfertaRepository.findAll();
        assertThat(alumnoEstabOfertaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
