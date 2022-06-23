package com.dgcye.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dgcye.IntegrationTest;
import com.dgcye.domain.Rendicion;
import com.dgcye.repository.RendicionRepository;
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
 * Integration tests for the {@link RendicionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RendicionResourceIT {

    private static final String DEFAULT_DNI_USUARIO = "AAAAAAAAAA";
    private static final String UPDATED_DNI_USUARIO = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRE_USUARIO = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_USUARIO = "BBBBBBBBBB";

    private static final String DEFAULT_DNI_USUARIO_ANULADOR = "AAAAAAAAAA";
    private static final String UPDATED_DNI_USUARIO_ANULADOR = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRE_USUARIO_ANULADOR = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_USUARIO_ANULADOR = "BBBBBBBBBB";

    private static final String DEFAULT_MOTIVO = "AAAAAAAAAA";
    private static final String UPDATED_MOTIVO = "BBBBBBBBBB";

    private static final String DEFAULT_CAUSA_RECHAZO = "AAAAAAAAAA";
    private static final String UPDATED_CAUSA_RECHAZO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_ANULACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_ANULACION = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DNI_ALUMNO = "AAAAAAAAAA";
    private static final String UPDATED_DNI_ALUMNO = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRE_ALUMNO = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_ALUMNO = "BBBBBBBBBB";

    private static final Long DEFAULT_ALUMNO_TITULO_ID = 1L;
    private static final Long UPDATED_ALUMNO_TITULO_ID = 2L;

    private static final Long DEFAULT_ESTABLECIMIENTO_ID = 1L;
    private static final Long UPDATED_ESTABLECIMIENTO_ID = 2L;

    private static final String DEFAULT_CLAVE_ESTAB = "AAAAAAAAAA";
    private static final String UPDATED_CLAVE_ESTAB = "BBBBBBBBBB";

    private static final String DEFAULT_RAMA = "AAAAAAAAAA";
    private static final String UPDATED_RAMA = "BBBBBBBBBB";

    private static final Integer DEFAULT_NRO_FORMULARIO = 1;
    private static final Integer UPDATED_NRO_FORMULARIO = 2;

    private static final String ENTITY_API_URL = "/api/rendicions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RendicionRepository rendicionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRendicionMockMvc;

    private Rendicion rendicion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Rendicion createEntity(EntityManager em) {
        Rendicion rendicion = new Rendicion()
            .dniUsuario(DEFAULT_DNI_USUARIO)
            .nombreUsuario(DEFAULT_NOMBRE_USUARIO)
            .dniUsuarioAnulador(DEFAULT_DNI_USUARIO_ANULADOR)
            .nombreUsuarioAnulador(DEFAULT_NOMBRE_USUARIO_ANULADOR)
            .motivo(DEFAULT_MOTIVO)
            .causaRechazo(DEFAULT_CAUSA_RECHAZO)
            .fechaAnulacion(DEFAULT_FECHA_ANULACION)
            .dniAlumno(DEFAULT_DNI_ALUMNO)
            .nombreAlumno(DEFAULT_NOMBRE_ALUMNO)
            .alumnoTituloId(DEFAULT_ALUMNO_TITULO_ID)
            .establecimientoId(DEFAULT_ESTABLECIMIENTO_ID)
            .claveEstab(DEFAULT_CLAVE_ESTAB)
            .rama(DEFAULT_RAMA)
            .nroFormulario(DEFAULT_NRO_FORMULARIO);
        return rendicion;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Rendicion createUpdatedEntity(EntityManager em) {
        Rendicion rendicion = new Rendicion()
            .dniUsuario(UPDATED_DNI_USUARIO)
            .nombreUsuario(UPDATED_NOMBRE_USUARIO)
            .dniUsuarioAnulador(UPDATED_DNI_USUARIO_ANULADOR)
            .nombreUsuarioAnulador(UPDATED_NOMBRE_USUARIO_ANULADOR)
            .motivo(UPDATED_MOTIVO)
            .causaRechazo(UPDATED_CAUSA_RECHAZO)
            .fechaAnulacion(UPDATED_FECHA_ANULACION)
            .dniAlumno(UPDATED_DNI_ALUMNO)
            .nombreAlumno(UPDATED_NOMBRE_ALUMNO)
            .alumnoTituloId(UPDATED_ALUMNO_TITULO_ID)
            .establecimientoId(UPDATED_ESTABLECIMIENTO_ID)
            .claveEstab(UPDATED_CLAVE_ESTAB)
            .rama(UPDATED_RAMA)
            .nroFormulario(UPDATED_NRO_FORMULARIO);
        return rendicion;
    }

    @BeforeEach
    public void initTest() {
        rendicion = createEntity(em);
    }

    @Test
    @Transactional
    void createRendicion() throws Exception {
        int databaseSizeBeforeCreate = rendicionRepository.findAll().size();
        // Create the Rendicion
        restRendicionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rendicion)))
            .andExpect(status().isCreated());

        // Validate the Rendicion in the database
        List<Rendicion> rendicionList = rendicionRepository.findAll();
        assertThat(rendicionList).hasSize(databaseSizeBeforeCreate + 1);
        Rendicion testRendicion = rendicionList.get(rendicionList.size() - 1);
        assertThat(testRendicion.getDniUsuario()).isEqualTo(DEFAULT_DNI_USUARIO);
        assertThat(testRendicion.getNombreUsuario()).isEqualTo(DEFAULT_NOMBRE_USUARIO);
        assertThat(testRendicion.getDniUsuarioAnulador()).isEqualTo(DEFAULT_DNI_USUARIO_ANULADOR);
        assertThat(testRendicion.getNombreUsuarioAnulador()).isEqualTo(DEFAULT_NOMBRE_USUARIO_ANULADOR);
        assertThat(testRendicion.getMotivo()).isEqualTo(DEFAULT_MOTIVO);
        assertThat(testRendicion.getCausaRechazo()).isEqualTo(DEFAULT_CAUSA_RECHAZO);
        assertThat(testRendicion.getFechaAnulacion()).isEqualTo(DEFAULT_FECHA_ANULACION);
        assertThat(testRendicion.getDniAlumno()).isEqualTo(DEFAULT_DNI_ALUMNO);
        assertThat(testRendicion.getNombreAlumno()).isEqualTo(DEFAULT_NOMBRE_ALUMNO);
        assertThat(testRendicion.getAlumnoTituloId()).isEqualTo(DEFAULT_ALUMNO_TITULO_ID);
        assertThat(testRendicion.getEstablecimientoId()).isEqualTo(DEFAULT_ESTABLECIMIENTO_ID);
        assertThat(testRendicion.getClaveEstab()).isEqualTo(DEFAULT_CLAVE_ESTAB);
        assertThat(testRendicion.getRama()).isEqualTo(DEFAULT_RAMA);
        assertThat(testRendicion.getNroFormulario()).isEqualTo(DEFAULT_NRO_FORMULARIO);
    }

    @Test
    @Transactional
    void createRendicionWithExistingId() throws Exception {
        // Create the Rendicion with an existing ID
        rendicion.setId(1L);

        int databaseSizeBeforeCreate = rendicionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRendicionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rendicion)))
            .andExpect(status().isBadRequest());

        // Validate the Rendicion in the database
        List<Rendicion> rendicionList = rendicionRepository.findAll();
        assertThat(rendicionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRendicions() throws Exception {
        // Initialize the database
        rendicionRepository.saveAndFlush(rendicion);

        // Get all the rendicionList
        restRendicionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rendicion.getId().intValue())))
            .andExpect(jsonPath("$.[*].dniUsuario").value(hasItem(DEFAULT_DNI_USUARIO)))
            .andExpect(jsonPath("$.[*].nombreUsuario").value(hasItem(DEFAULT_NOMBRE_USUARIO)))
            .andExpect(jsonPath("$.[*].dniUsuarioAnulador").value(hasItem(DEFAULT_DNI_USUARIO_ANULADOR)))
            .andExpect(jsonPath("$.[*].nombreUsuarioAnulador").value(hasItem(DEFAULT_NOMBRE_USUARIO_ANULADOR)))
            .andExpect(jsonPath("$.[*].motivo").value(hasItem(DEFAULT_MOTIVO)))
            .andExpect(jsonPath("$.[*].causaRechazo").value(hasItem(DEFAULT_CAUSA_RECHAZO)))
            .andExpect(jsonPath("$.[*].fechaAnulacion").value(hasItem(DEFAULT_FECHA_ANULACION.toString())))
            .andExpect(jsonPath("$.[*].dniAlumno").value(hasItem(DEFAULT_DNI_ALUMNO)))
            .andExpect(jsonPath("$.[*].nombreAlumno").value(hasItem(DEFAULT_NOMBRE_ALUMNO)))
            .andExpect(jsonPath("$.[*].alumnoTituloId").value(hasItem(DEFAULT_ALUMNO_TITULO_ID.intValue())))
            .andExpect(jsonPath("$.[*].establecimientoId").value(hasItem(DEFAULT_ESTABLECIMIENTO_ID.intValue())))
            .andExpect(jsonPath("$.[*].claveEstab").value(hasItem(DEFAULT_CLAVE_ESTAB)))
            .andExpect(jsonPath("$.[*].rama").value(hasItem(DEFAULT_RAMA)))
            .andExpect(jsonPath("$.[*].nroFormulario").value(hasItem(DEFAULT_NRO_FORMULARIO)));
    }

    @Test
    @Transactional
    void getRendicion() throws Exception {
        // Initialize the database
        rendicionRepository.saveAndFlush(rendicion);

        // Get the rendicion
        restRendicionMockMvc
            .perform(get(ENTITY_API_URL_ID, rendicion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rendicion.getId().intValue()))
            .andExpect(jsonPath("$.dniUsuario").value(DEFAULT_DNI_USUARIO))
            .andExpect(jsonPath("$.nombreUsuario").value(DEFAULT_NOMBRE_USUARIO))
            .andExpect(jsonPath("$.dniUsuarioAnulador").value(DEFAULT_DNI_USUARIO_ANULADOR))
            .andExpect(jsonPath("$.nombreUsuarioAnulador").value(DEFAULT_NOMBRE_USUARIO_ANULADOR))
            .andExpect(jsonPath("$.motivo").value(DEFAULT_MOTIVO))
            .andExpect(jsonPath("$.causaRechazo").value(DEFAULT_CAUSA_RECHAZO))
            .andExpect(jsonPath("$.fechaAnulacion").value(DEFAULT_FECHA_ANULACION.toString()))
            .andExpect(jsonPath("$.dniAlumno").value(DEFAULT_DNI_ALUMNO))
            .andExpect(jsonPath("$.nombreAlumno").value(DEFAULT_NOMBRE_ALUMNO))
            .andExpect(jsonPath("$.alumnoTituloId").value(DEFAULT_ALUMNO_TITULO_ID.intValue()))
            .andExpect(jsonPath("$.establecimientoId").value(DEFAULT_ESTABLECIMIENTO_ID.intValue()))
            .andExpect(jsonPath("$.claveEstab").value(DEFAULT_CLAVE_ESTAB))
            .andExpect(jsonPath("$.rama").value(DEFAULT_RAMA))
            .andExpect(jsonPath("$.nroFormulario").value(DEFAULT_NRO_FORMULARIO));
    }

    @Test
    @Transactional
    void getNonExistingRendicion() throws Exception {
        // Get the rendicion
        restRendicionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewRendicion() throws Exception {
        // Initialize the database
        rendicionRepository.saveAndFlush(rendicion);

        int databaseSizeBeforeUpdate = rendicionRepository.findAll().size();

        // Update the rendicion
        Rendicion updatedRendicion = rendicionRepository.findById(rendicion.getId()).get();
        // Disconnect from session so that the updates on updatedRendicion are not directly saved in db
        em.detach(updatedRendicion);
        updatedRendicion
            .dniUsuario(UPDATED_DNI_USUARIO)
            .nombreUsuario(UPDATED_NOMBRE_USUARIO)
            .dniUsuarioAnulador(UPDATED_DNI_USUARIO_ANULADOR)
            .nombreUsuarioAnulador(UPDATED_NOMBRE_USUARIO_ANULADOR)
            .motivo(UPDATED_MOTIVO)
            .causaRechazo(UPDATED_CAUSA_RECHAZO)
            .fechaAnulacion(UPDATED_FECHA_ANULACION)
            .dniAlumno(UPDATED_DNI_ALUMNO)
            .nombreAlumno(UPDATED_NOMBRE_ALUMNO)
            .alumnoTituloId(UPDATED_ALUMNO_TITULO_ID)
            .establecimientoId(UPDATED_ESTABLECIMIENTO_ID)
            .claveEstab(UPDATED_CLAVE_ESTAB)
            .rama(UPDATED_RAMA)
            .nroFormulario(UPDATED_NRO_FORMULARIO);

        restRendicionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRendicion.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedRendicion))
            )
            .andExpect(status().isOk());

        // Validate the Rendicion in the database
        List<Rendicion> rendicionList = rendicionRepository.findAll();
        assertThat(rendicionList).hasSize(databaseSizeBeforeUpdate);
        Rendicion testRendicion = rendicionList.get(rendicionList.size() - 1);
        assertThat(testRendicion.getDniUsuario()).isEqualTo(UPDATED_DNI_USUARIO);
        assertThat(testRendicion.getNombreUsuario()).isEqualTo(UPDATED_NOMBRE_USUARIO);
        assertThat(testRendicion.getDniUsuarioAnulador()).isEqualTo(UPDATED_DNI_USUARIO_ANULADOR);
        assertThat(testRendicion.getNombreUsuarioAnulador()).isEqualTo(UPDATED_NOMBRE_USUARIO_ANULADOR);
        assertThat(testRendicion.getMotivo()).isEqualTo(UPDATED_MOTIVO);
        assertThat(testRendicion.getCausaRechazo()).isEqualTo(UPDATED_CAUSA_RECHAZO);
        assertThat(testRendicion.getFechaAnulacion()).isEqualTo(UPDATED_FECHA_ANULACION);
        assertThat(testRendicion.getDniAlumno()).isEqualTo(UPDATED_DNI_ALUMNO);
        assertThat(testRendicion.getNombreAlumno()).isEqualTo(UPDATED_NOMBRE_ALUMNO);
        assertThat(testRendicion.getAlumnoTituloId()).isEqualTo(UPDATED_ALUMNO_TITULO_ID);
        assertThat(testRendicion.getEstablecimientoId()).isEqualTo(UPDATED_ESTABLECIMIENTO_ID);
        assertThat(testRendicion.getClaveEstab()).isEqualTo(UPDATED_CLAVE_ESTAB);
        assertThat(testRendicion.getRama()).isEqualTo(UPDATED_RAMA);
        assertThat(testRendicion.getNroFormulario()).isEqualTo(UPDATED_NRO_FORMULARIO);
    }

    @Test
    @Transactional
    void putNonExistingRendicion() throws Exception {
        int databaseSizeBeforeUpdate = rendicionRepository.findAll().size();
        rendicion.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRendicionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, rendicion.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rendicion))
            )
            .andExpect(status().isBadRequest());

        // Validate the Rendicion in the database
        List<Rendicion> rendicionList = rendicionRepository.findAll();
        assertThat(rendicionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRendicion() throws Exception {
        int databaseSizeBeforeUpdate = rendicionRepository.findAll().size();
        rendicion.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRendicionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rendicion))
            )
            .andExpect(status().isBadRequest());

        // Validate the Rendicion in the database
        List<Rendicion> rendicionList = rendicionRepository.findAll();
        assertThat(rendicionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRendicion() throws Exception {
        int databaseSizeBeforeUpdate = rendicionRepository.findAll().size();
        rendicion.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRendicionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rendicion)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Rendicion in the database
        List<Rendicion> rendicionList = rendicionRepository.findAll();
        assertThat(rendicionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRendicionWithPatch() throws Exception {
        // Initialize the database
        rendicionRepository.saveAndFlush(rendicion);

        int databaseSizeBeforeUpdate = rendicionRepository.findAll().size();

        // Update the rendicion using partial update
        Rendicion partialUpdatedRendicion = new Rendicion();
        partialUpdatedRendicion.setId(rendicion.getId());

        partialUpdatedRendicion.dniUsuario(UPDATED_DNI_USUARIO).dniUsuarioAnulador(UPDATED_DNI_USUARIO_ANULADOR);

        restRendicionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRendicion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRendicion))
            )
            .andExpect(status().isOk());

        // Validate the Rendicion in the database
        List<Rendicion> rendicionList = rendicionRepository.findAll();
        assertThat(rendicionList).hasSize(databaseSizeBeforeUpdate);
        Rendicion testRendicion = rendicionList.get(rendicionList.size() - 1);
        assertThat(testRendicion.getDniUsuario()).isEqualTo(UPDATED_DNI_USUARIO);
        assertThat(testRendicion.getNombreUsuario()).isEqualTo(DEFAULT_NOMBRE_USUARIO);
        assertThat(testRendicion.getDniUsuarioAnulador()).isEqualTo(UPDATED_DNI_USUARIO_ANULADOR);
        assertThat(testRendicion.getNombreUsuarioAnulador()).isEqualTo(DEFAULT_NOMBRE_USUARIO_ANULADOR);
        assertThat(testRendicion.getMotivo()).isEqualTo(DEFAULT_MOTIVO);
        assertThat(testRendicion.getCausaRechazo()).isEqualTo(DEFAULT_CAUSA_RECHAZO);
        assertThat(testRendicion.getFechaAnulacion()).isEqualTo(DEFAULT_FECHA_ANULACION);
        assertThat(testRendicion.getDniAlumno()).isEqualTo(DEFAULT_DNI_ALUMNO);
        assertThat(testRendicion.getNombreAlumno()).isEqualTo(DEFAULT_NOMBRE_ALUMNO);
        assertThat(testRendicion.getAlumnoTituloId()).isEqualTo(DEFAULT_ALUMNO_TITULO_ID);
        assertThat(testRendicion.getEstablecimientoId()).isEqualTo(DEFAULT_ESTABLECIMIENTO_ID);
        assertThat(testRendicion.getClaveEstab()).isEqualTo(DEFAULT_CLAVE_ESTAB);
        assertThat(testRendicion.getRama()).isEqualTo(DEFAULT_RAMA);
        assertThat(testRendicion.getNroFormulario()).isEqualTo(DEFAULT_NRO_FORMULARIO);
    }

    @Test
    @Transactional
    void fullUpdateRendicionWithPatch() throws Exception {
        // Initialize the database
        rendicionRepository.saveAndFlush(rendicion);

        int databaseSizeBeforeUpdate = rendicionRepository.findAll().size();

        // Update the rendicion using partial update
        Rendicion partialUpdatedRendicion = new Rendicion();
        partialUpdatedRendicion.setId(rendicion.getId());

        partialUpdatedRendicion
            .dniUsuario(UPDATED_DNI_USUARIO)
            .nombreUsuario(UPDATED_NOMBRE_USUARIO)
            .dniUsuarioAnulador(UPDATED_DNI_USUARIO_ANULADOR)
            .nombreUsuarioAnulador(UPDATED_NOMBRE_USUARIO_ANULADOR)
            .motivo(UPDATED_MOTIVO)
            .causaRechazo(UPDATED_CAUSA_RECHAZO)
            .fechaAnulacion(UPDATED_FECHA_ANULACION)
            .dniAlumno(UPDATED_DNI_ALUMNO)
            .nombreAlumno(UPDATED_NOMBRE_ALUMNO)
            .alumnoTituloId(UPDATED_ALUMNO_TITULO_ID)
            .establecimientoId(UPDATED_ESTABLECIMIENTO_ID)
            .claveEstab(UPDATED_CLAVE_ESTAB)
            .rama(UPDATED_RAMA)
            .nroFormulario(UPDATED_NRO_FORMULARIO);

        restRendicionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRendicion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRendicion))
            )
            .andExpect(status().isOk());

        // Validate the Rendicion in the database
        List<Rendicion> rendicionList = rendicionRepository.findAll();
        assertThat(rendicionList).hasSize(databaseSizeBeforeUpdate);
        Rendicion testRendicion = rendicionList.get(rendicionList.size() - 1);
        assertThat(testRendicion.getDniUsuario()).isEqualTo(UPDATED_DNI_USUARIO);
        assertThat(testRendicion.getNombreUsuario()).isEqualTo(UPDATED_NOMBRE_USUARIO);
        assertThat(testRendicion.getDniUsuarioAnulador()).isEqualTo(UPDATED_DNI_USUARIO_ANULADOR);
        assertThat(testRendicion.getNombreUsuarioAnulador()).isEqualTo(UPDATED_NOMBRE_USUARIO_ANULADOR);
        assertThat(testRendicion.getMotivo()).isEqualTo(UPDATED_MOTIVO);
        assertThat(testRendicion.getCausaRechazo()).isEqualTo(UPDATED_CAUSA_RECHAZO);
        assertThat(testRendicion.getFechaAnulacion()).isEqualTo(UPDATED_FECHA_ANULACION);
        assertThat(testRendicion.getDniAlumno()).isEqualTo(UPDATED_DNI_ALUMNO);
        assertThat(testRendicion.getNombreAlumno()).isEqualTo(UPDATED_NOMBRE_ALUMNO);
        assertThat(testRendicion.getAlumnoTituloId()).isEqualTo(UPDATED_ALUMNO_TITULO_ID);
        assertThat(testRendicion.getEstablecimientoId()).isEqualTo(UPDATED_ESTABLECIMIENTO_ID);
        assertThat(testRendicion.getClaveEstab()).isEqualTo(UPDATED_CLAVE_ESTAB);
        assertThat(testRendicion.getRama()).isEqualTo(UPDATED_RAMA);
        assertThat(testRendicion.getNroFormulario()).isEqualTo(UPDATED_NRO_FORMULARIO);
    }

    @Test
    @Transactional
    void patchNonExistingRendicion() throws Exception {
        int databaseSizeBeforeUpdate = rendicionRepository.findAll().size();
        rendicion.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRendicionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, rendicion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(rendicion))
            )
            .andExpect(status().isBadRequest());

        // Validate the Rendicion in the database
        List<Rendicion> rendicionList = rendicionRepository.findAll();
        assertThat(rendicionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRendicion() throws Exception {
        int databaseSizeBeforeUpdate = rendicionRepository.findAll().size();
        rendicion.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRendicionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(rendicion))
            )
            .andExpect(status().isBadRequest());

        // Validate the Rendicion in the database
        List<Rendicion> rendicionList = rendicionRepository.findAll();
        assertThat(rendicionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRendicion() throws Exception {
        int databaseSizeBeforeUpdate = rendicionRepository.findAll().size();
        rendicion.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRendicionMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(rendicion))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Rendicion in the database
        List<Rendicion> rendicionList = rendicionRepository.findAll();
        assertThat(rendicionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRendicion() throws Exception {
        // Initialize the database
        rendicionRepository.saveAndFlush(rendicion);

        int databaseSizeBeforeDelete = rendicionRepository.findAll().size();

        // Delete the rendicion
        restRendicionMockMvc
            .perform(delete(ENTITY_API_URL_ID, rendicion.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Rendicion> rendicionList = rendicionRepository.findAll();
        assertThat(rendicionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
