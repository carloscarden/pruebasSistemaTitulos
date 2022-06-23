package com.dgcye.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dgcye.IntegrationTest;
import com.dgcye.domain.NroSerieTitulo;
import com.dgcye.repository.NroSerieTituloRepository;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link NroSerieTituloResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NroSerieTituloResourceIT {

    private static final Integer DEFAULT_NRO_SERIE_INICIO = 1;
    private static final Integer UPDATED_NRO_SERIE_INICIO = 2;

    private static final Integer DEFAULT_NRO_SERIE_FIN = 1;
    private static final Integer UPDATED_NRO_SERIE_FIN = 2;

    private static final LocalDate DEFAULT_FECHA_INICIO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_INICIO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FECHA_FIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_FIN = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_USUARIO_ALTA = "AAAAAAAAAA";
    private static final String UPDATED_USUARIO_ALTA = "BBBBBBBBBB";

    private static final String DEFAULT_USUARIO_MODIFICACION = "AAAAAAAAAA";
    private static final String UPDATED_USUARIO_MODIFICACION = "BBBBBBBBBB";

    private static final Integer DEFAULT_SERIE = 1;
    private static final Integer UPDATED_SERIE = 2;

    private static final String ENTITY_API_URL = "/api/nro-serie-titulos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private NroSerieTituloRepository nroSerieTituloRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNroSerieTituloMockMvc;

    private NroSerieTitulo nroSerieTitulo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NroSerieTitulo createEntity(EntityManager em) {
        NroSerieTitulo nroSerieTitulo = new NroSerieTitulo()
            .nroSerieInicio(DEFAULT_NRO_SERIE_INICIO)
            .nroSerieFin(DEFAULT_NRO_SERIE_FIN)
            .fechaInicio(DEFAULT_FECHA_INICIO)
            .fechaFin(DEFAULT_FECHA_FIN)
            .usuarioAlta(DEFAULT_USUARIO_ALTA)
            .usuarioModificacion(DEFAULT_USUARIO_MODIFICACION)
            .serie(DEFAULT_SERIE);
        return nroSerieTitulo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NroSerieTitulo createUpdatedEntity(EntityManager em) {
        NroSerieTitulo nroSerieTitulo = new NroSerieTitulo()
            .nroSerieInicio(UPDATED_NRO_SERIE_INICIO)
            .nroSerieFin(UPDATED_NRO_SERIE_FIN)
            .fechaInicio(UPDATED_FECHA_INICIO)
            .fechaFin(UPDATED_FECHA_FIN)
            .usuarioAlta(UPDATED_USUARIO_ALTA)
            .usuarioModificacion(UPDATED_USUARIO_MODIFICACION)
            .serie(UPDATED_SERIE);
        return nroSerieTitulo;
    }

    @BeforeEach
    public void initTest() {
        nroSerieTitulo = createEntity(em);
    }

    @Test
    @Transactional
    void createNroSerieTitulo() throws Exception {
        int databaseSizeBeforeCreate = nroSerieTituloRepository.findAll().size();
        // Create the NroSerieTitulo
        restNroSerieTituloMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(nroSerieTitulo))
            )
            .andExpect(status().isCreated());

        // Validate the NroSerieTitulo in the database
        List<NroSerieTitulo> nroSerieTituloList = nroSerieTituloRepository.findAll();
        assertThat(nroSerieTituloList).hasSize(databaseSizeBeforeCreate + 1);
        NroSerieTitulo testNroSerieTitulo = nroSerieTituloList.get(nroSerieTituloList.size() - 1);
        assertThat(testNroSerieTitulo.getNroSerieInicio()).isEqualTo(DEFAULT_NRO_SERIE_INICIO);
        assertThat(testNroSerieTitulo.getNroSerieFin()).isEqualTo(DEFAULT_NRO_SERIE_FIN);
        assertThat(testNroSerieTitulo.getFechaInicio()).isEqualTo(DEFAULT_FECHA_INICIO);
        assertThat(testNroSerieTitulo.getFechaFin()).isEqualTo(DEFAULT_FECHA_FIN);
        assertThat(testNroSerieTitulo.getUsuarioAlta()).isEqualTo(DEFAULT_USUARIO_ALTA);
        assertThat(testNroSerieTitulo.getUsuarioModificacion()).isEqualTo(DEFAULT_USUARIO_MODIFICACION);
        assertThat(testNroSerieTitulo.getSerie()).isEqualTo(DEFAULT_SERIE);
    }

    @Test
    @Transactional
    void createNroSerieTituloWithExistingId() throws Exception {
        // Create the NroSerieTitulo with an existing ID
        nroSerieTitulo.setId(1L);

        int databaseSizeBeforeCreate = nroSerieTituloRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restNroSerieTituloMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(nroSerieTitulo))
            )
            .andExpect(status().isBadRequest());

        // Validate the NroSerieTitulo in the database
        List<NroSerieTitulo> nroSerieTituloList = nroSerieTituloRepository.findAll();
        assertThat(nroSerieTituloList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllNroSerieTitulos() throws Exception {
        // Initialize the database
        nroSerieTituloRepository.saveAndFlush(nroSerieTitulo);

        // Get all the nroSerieTituloList
        restNroSerieTituloMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nroSerieTitulo.getId().intValue())))
            .andExpect(jsonPath("$.[*].nroSerieInicio").value(hasItem(DEFAULT_NRO_SERIE_INICIO)))
            .andExpect(jsonPath("$.[*].nroSerieFin").value(hasItem(DEFAULT_NRO_SERIE_FIN)))
            .andExpect(jsonPath("$.[*].fechaInicio").value(hasItem(DEFAULT_FECHA_INICIO.toString())))
            .andExpect(jsonPath("$.[*].fechaFin").value(hasItem(DEFAULT_FECHA_FIN.toString())))
            .andExpect(jsonPath("$.[*].usuarioAlta").value(hasItem(DEFAULT_USUARIO_ALTA)))
            .andExpect(jsonPath("$.[*].usuarioModificacion").value(hasItem(DEFAULT_USUARIO_MODIFICACION)))
            .andExpect(jsonPath("$.[*].serie").value(hasItem(DEFAULT_SERIE)));
    }

    @Test
    @Transactional
    void getNroSerieTitulo() throws Exception {
        // Initialize the database
        nroSerieTituloRepository.saveAndFlush(nroSerieTitulo);

        // Get the nroSerieTitulo
        restNroSerieTituloMockMvc
            .perform(get(ENTITY_API_URL_ID, nroSerieTitulo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(nroSerieTitulo.getId().intValue()))
            .andExpect(jsonPath("$.nroSerieInicio").value(DEFAULT_NRO_SERIE_INICIO))
            .andExpect(jsonPath("$.nroSerieFin").value(DEFAULT_NRO_SERIE_FIN))
            .andExpect(jsonPath("$.fechaInicio").value(DEFAULT_FECHA_INICIO.toString()))
            .andExpect(jsonPath("$.fechaFin").value(DEFAULT_FECHA_FIN.toString()))
            .andExpect(jsonPath("$.usuarioAlta").value(DEFAULT_USUARIO_ALTA))
            .andExpect(jsonPath("$.usuarioModificacion").value(DEFAULT_USUARIO_MODIFICACION))
            .andExpect(jsonPath("$.serie").value(DEFAULT_SERIE));
    }

    @Test
    @Transactional
    void getNonExistingNroSerieTitulo() throws Exception {
        // Get the nroSerieTitulo
        restNroSerieTituloMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewNroSerieTitulo() throws Exception {
        // Initialize the database
        nroSerieTituloRepository.saveAndFlush(nroSerieTitulo);

        int databaseSizeBeforeUpdate = nroSerieTituloRepository.findAll().size();

        // Update the nroSerieTitulo
        NroSerieTitulo updatedNroSerieTitulo = nroSerieTituloRepository.findById(nroSerieTitulo.getId()).get();
        // Disconnect from session so that the updates on updatedNroSerieTitulo are not directly saved in db
        em.detach(updatedNroSerieTitulo);
        updatedNroSerieTitulo
            .nroSerieInicio(UPDATED_NRO_SERIE_INICIO)
            .nroSerieFin(UPDATED_NRO_SERIE_FIN)
            .fechaInicio(UPDATED_FECHA_INICIO)
            .fechaFin(UPDATED_FECHA_FIN)
            .usuarioAlta(UPDATED_USUARIO_ALTA)
            .usuarioModificacion(UPDATED_USUARIO_MODIFICACION)
            .serie(UPDATED_SERIE);

        restNroSerieTituloMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedNroSerieTitulo.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedNroSerieTitulo))
            )
            .andExpect(status().isOk());

        // Validate the NroSerieTitulo in the database
        List<NroSerieTitulo> nroSerieTituloList = nroSerieTituloRepository.findAll();
        assertThat(nroSerieTituloList).hasSize(databaseSizeBeforeUpdate);
        NroSerieTitulo testNroSerieTitulo = nroSerieTituloList.get(nroSerieTituloList.size() - 1);
        assertThat(testNroSerieTitulo.getNroSerieInicio()).isEqualTo(UPDATED_NRO_SERIE_INICIO);
        assertThat(testNroSerieTitulo.getNroSerieFin()).isEqualTo(UPDATED_NRO_SERIE_FIN);
        assertThat(testNroSerieTitulo.getFechaInicio()).isEqualTo(UPDATED_FECHA_INICIO);
        assertThat(testNroSerieTitulo.getFechaFin()).isEqualTo(UPDATED_FECHA_FIN);
        assertThat(testNroSerieTitulo.getUsuarioAlta()).isEqualTo(UPDATED_USUARIO_ALTA);
        assertThat(testNroSerieTitulo.getUsuarioModificacion()).isEqualTo(UPDATED_USUARIO_MODIFICACION);
        assertThat(testNroSerieTitulo.getSerie()).isEqualTo(UPDATED_SERIE);
    }

    @Test
    @Transactional
    void putNonExistingNroSerieTitulo() throws Exception {
        int databaseSizeBeforeUpdate = nroSerieTituloRepository.findAll().size();
        nroSerieTitulo.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNroSerieTituloMockMvc
            .perform(
                put(ENTITY_API_URL_ID, nroSerieTitulo.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(nroSerieTitulo))
            )
            .andExpect(status().isBadRequest());

        // Validate the NroSerieTitulo in the database
        List<NroSerieTitulo> nroSerieTituloList = nroSerieTituloRepository.findAll();
        assertThat(nroSerieTituloList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchNroSerieTitulo() throws Exception {
        int databaseSizeBeforeUpdate = nroSerieTituloRepository.findAll().size();
        nroSerieTitulo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNroSerieTituloMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(nroSerieTitulo))
            )
            .andExpect(status().isBadRequest());

        // Validate the NroSerieTitulo in the database
        List<NroSerieTitulo> nroSerieTituloList = nroSerieTituloRepository.findAll();
        assertThat(nroSerieTituloList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamNroSerieTitulo() throws Exception {
        int databaseSizeBeforeUpdate = nroSerieTituloRepository.findAll().size();
        nroSerieTitulo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNroSerieTituloMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(nroSerieTitulo)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the NroSerieTitulo in the database
        List<NroSerieTitulo> nroSerieTituloList = nroSerieTituloRepository.findAll();
        assertThat(nroSerieTituloList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateNroSerieTituloWithPatch() throws Exception {
        // Initialize the database
        nroSerieTituloRepository.saveAndFlush(nroSerieTitulo);

        int databaseSizeBeforeUpdate = nroSerieTituloRepository.findAll().size();

        // Update the nroSerieTitulo using partial update
        NroSerieTitulo partialUpdatedNroSerieTitulo = new NroSerieTitulo();
        partialUpdatedNroSerieTitulo.setId(nroSerieTitulo.getId());

        partialUpdatedNroSerieTitulo
            .nroSerieInicio(UPDATED_NRO_SERIE_INICIO)
            .fechaInicio(UPDATED_FECHA_INICIO)
            .usuarioAlta(UPDATED_USUARIO_ALTA)
            .usuarioModificacion(UPDATED_USUARIO_MODIFICACION)
            .serie(UPDATED_SERIE);

        restNroSerieTituloMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNroSerieTitulo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNroSerieTitulo))
            )
            .andExpect(status().isOk());

        // Validate the NroSerieTitulo in the database
        List<NroSerieTitulo> nroSerieTituloList = nroSerieTituloRepository.findAll();
        assertThat(nroSerieTituloList).hasSize(databaseSizeBeforeUpdate);
        NroSerieTitulo testNroSerieTitulo = nroSerieTituloList.get(nroSerieTituloList.size() - 1);
        assertThat(testNroSerieTitulo.getNroSerieInicio()).isEqualTo(UPDATED_NRO_SERIE_INICIO);
        assertThat(testNroSerieTitulo.getNroSerieFin()).isEqualTo(DEFAULT_NRO_SERIE_FIN);
        assertThat(testNroSerieTitulo.getFechaInicio()).isEqualTo(UPDATED_FECHA_INICIO);
        assertThat(testNroSerieTitulo.getFechaFin()).isEqualTo(DEFAULT_FECHA_FIN);
        assertThat(testNroSerieTitulo.getUsuarioAlta()).isEqualTo(UPDATED_USUARIO_ALTA);
        assertThat(testNroSerieTitulo.getUsuarioModificacion()).isEqualTo(UPDATED_USUARIO_MODIFICACION);
        assertThat(testNroSerieTitulo.getSerie()).isEqualTo(UPDATED_SERIE);
    }

    @Test
    @Transactional
    void fullUpdateNroSerieTituloWithPatch() throws Exception {
        // Initialize the database
        nroSerieTituloRepository.saveAndFlush(nroSerieTitulo);

        int databaseSizeBeforeUpdate = nroSerieTituloRepository.findAll().size();

        // Update the nroSerieTitulo using partial update
        NroSerieTitulo partialUpdatedNroSerieTitulo = new NroSerieTitulo();
        partialUpdatedNroSerieTitulo.setId(nroSerieTitulo.getId());

        partialUpdatedNroSerieTitulo
            .nroSerieInicio(UPDATED_NRO_SERIE_INICIO)
            .nroSerieFin(UPDATED_NRO_SERIE_FIN)
            .fechaInicio(UPDATED_FECHA_INICIO)
            .fechaFin(UPDATED_FECHA_FIN)
            .usuarioAlta(UPDATED_USUARIO_ALTA)
            .usuarioModificacion(UPDATED_USUARIO_MODIFICACION)
            .serie(UPDATED_SERIE);

        restNroSerieTituloMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNroSerieTitulo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNroSerieTitulo))
            )
            .andExpect(status().isOk());

        // Validate the NroSerieTitulo in the database
        List<NroSerieTitulo> nroSerieTituloList = nroSerieTituloRepository.findAll();
        assertThat(nroSerieTituloList).hasSize(databaseSizeBeforeUpdate);
        NroSerieTitulo testNroSerieTitulo = nroSerieTituloList.get(nroSerieTituloList.size() - 1);
        assertThat(testNroSerieTitulo.getNroSerieInicio()).isEqualTo(UPDATED_NRO_SERIE_INICIO);
        assertThat(testNroSerieTitulo.getNroSerieFin()).isEqualTo(UPDATED_NRO_SERIE_FIN);
        assertThat(testNroSerieTitulo.getFechaInicio()).isEqualTo(UPDATED_FECHA_INICIO);
        assertThat(testNroSerieTitulo.getFechaFin()).isEqualTo(UPDATED_FECHA_FIN);
        assertThat(testNroSerieTitulo.getUsuarioAlta()).isEqualTo(UPDATED_USUARIO_ALTA);
        assertThat(testNroSerieTitulo.getUsuarioModificacion()).isEqualTo(UPDATED_USUARIO_MODIFICACION);
        assertThat(testNroSerieTitulo.getSerie()).isEqualTo(UPDATED_SERIE);
    }

    @Test
    @Transactional
    void patchNonExistingNroSerieTitulo() throws Exception {
        int databaseSizeBeforeUpdate = nroSerieTituloRepository.findAll().size();
        nroSerieTitulo.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNroSerieTituloMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, nroSerieTitulo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(nroSerieTitulo))
            )
            .andExpect(status().isBadRequest());

        // Validate the NroSerieTitulo in the database
        List<NroSerieTitulo> nroSerieTituloList = nroSerieTituloRepository.findAll();
        assertThat(nroSerieTituloList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchNroSerieTitulo() throws Exception {
        int databaseSizeBeforeUpdate = nroSerieTituloRepository.findAll().size();
        nroSerieTitulo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNroSerieTituloMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(nroSerieTitulo))
            )
            .andExpect(status().isBadRequest());

        // Validate the NroSerieTitulo in the database
        List<NroSerieTitulo> nroSerieTituloList = nroSerieTituloRepository.findAll();
        assertThat(nroSerieTituloList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamNroSerieTitulo() throws Exception {
        int databaseSizeBeforeUpdate = nroSerieTituloRepository.findAll().size();
        nroSerieTitulo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNroSerieTituloMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(nroSerieTitulo))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NroSerieTitulo in the database
        List<NroSerieTitulo> nroSerieTituloList = nroSerieTituloRepository.findAll();
        assertThat(nroSerieTituloList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteNroSerieTitulo() throws Exception {
        // Initialize the database
        nroSerieTituloRepository.saveAndFlush(nroSerieTitulo);

        int databaseSizeBeforeDelete = nroSerieTituloRepository.findAll().size();

        // Delete the nroSerieTitulo
        restNroSerieTituloMockMvc
            .perform(delete(ENTITY_API_URL_ID, nroSerieTitulo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NroSerieTitulo> nroSerieTituloList = nroSerieTituloRepository.findAll();
        assertThat(nroSerieTituloList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
