package com.dgcye.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dgcye.IntegrationTest;
import com.dgcye.domain.Alumno;
import com.dgcye.repository.AlumnoRepository;
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
 * Integration tests for the {@link AlumnoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AlumnoResourceIT {

    private static final String DEFAULT_DOCUMENTO = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENTO = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_TIPO_DOCUMENTO = 1L;
    private static final Long UPDATED_ID_TIPO_DOCUMENTO = 2L;

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDO = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDO = "BBBBBBBBBB";

    private static final Instant DEFAULT_FECHA_NACIMENTO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FECHA_NACIMENTO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_SEXO = "AAAAAAAAAA";
    private static final String UPDATED_SEXO = "BBBBBBBBBB";

    private static final String DEFAULT_CIUDAD_NACIMIENTO = "AAAAAAAAAA";
    private static final String UPDATED_CIUDAD_NACIMIENTO = "BBBBBBBBBB";

    private static final String DEFAULT_PROVINCIA_NACIMIENTO = "AAAAAAAAAA";
    private static final String UPDATED_PROVINCIA_NACIMIENTO = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_NACIONALIDAD = 1L;
    private static final Long UPDATED_ID_NACIONALIDAD = 2L;

    private static final Long DEFAULT_ID_SER_CREADOR = 1L;
    private static final Long UPDATED_ID_SER_CREADOR = 2L;

    private static final Long DEFAULT_ID_PROVINCIA = 1L;
    private static final Long UPDATED_ID_PROVINCIA = 2L;

    private static final String ENTITY_API_URL = "/api/alumnos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAlumnoMockMvc;

    private Alumno alumno;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Alumno createEntity(EntityManager em) {
        Alumno alumno = new Alumno()
            .documento(DEFAULT_DOCUMENTO)
            .idTipoDocumento(DEFAULT_ID_TIPO_DOCUMENTO)
            .nombre(DEFAULT_NOMBRE)
            .apellido(DEFAULT_APELLIDO)
            .fechaNacimento(DEFAULT_FECHA_NACIMENTO)
            .sexo(DEFAULT_SEXO)
            .ciudadNacimiento(DEFAULT_CIUDAD_NACIMIENTO)
            .provinciaNacimiento(DEFAULT_PROVINCIA_NACIMIENTO)
            .idNacionalidad(DEFAULT_ID_NACIONALIDAD)
            .idSerCreador(DEFAULT_ID_SER_CREADOR)
            .idProvincia(DEFAULT_ID_PROVINCIA);
        return alumno;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Alumno createUpdatedEntity(EntityManager em) {
        Alumno alumno = new Alumno()
            .documento(UPDATED_DOCUMENTO)
            .idTipoDocumento(UPDATED_ID_TIPO_DOCUMENTO)
            .nombre(UPDATED_NOMBRE)
            .apellido(UPDATED_APELLIDO)
            .fechaNacimento(UPDATED_FECHA_NACIMENTO)
            .sexo(UPDATED_SEXO)
            .ciudadNacimiento(UPDATED_CIUDAD_NACIMIENTO)
            .provinciaNacimiento(UPDATED_PROVINCIA_NACIMIENTO)
            .idNacionalidad(UPDATED_ID_NACIONALIDAD)
            .idSerCreador(UPDATED_ID_SER_CREADOR)
            .idProvincia(UPDATED_ID_PROVINCIA);
        return alumno;
    }

    @BeforeEach
    public void initTest() {
        alumno = createEntity(em);
    }

    @Test
    @Transactional
    void createAlumno() throws Exception {
        int databaseSizeBeforeCreate = alumnoRepository.findAll().size();
        // Create the Alumno
        restAlumnoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(alumno)))
            .andExpect(status().isCreated());

        // Validate the Alumno in the database
        List<Alumno> alumnoList = alumnoRepository.findAll();
        assertThat(alumnoList).hasSize(databaseSizeBeforeCreate + 1);
        Alumno testAlumno = alumnoList.get(alumnoList.size() - 1);
        assertThat(testAlumno.getDocumento()).isEqualTo(DEFAULT_DOCUMENTO);
        assertThat(testAlumno.getIdTipoDocumento()).isEqualTo(DEFAULT_ID_TIPO_DOCUMENTO);
        assertThat(testAlumno.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testAlumno.getApellido()).isEqualTo(DEFAULT_APELLIDO);
        assertThat(testAlumno.getFechaNacimento()).isEqualTo(DEFAULT_FECHA_NACIMENTO);
        assertThat(testAlumno.getSexo()).isEqualTo(DEFAULT_SEXO);
        assertThat(testAlumno.getCiudadNacimiento()).isEqualTo(DEFAULT_CIUDAD_NACIMIENTO);
        assertThat(testAlumno.getProvinciaNacimiento()).isEqualTo(DEFAULT_PROVINCIA_NACIMIENTO);
        assertThat(testAlumno.getIdNacionalidad()).isEqualTo(DEFAULT_ID_NACIONALIDAD);
        assertThat(testAlumno.getIdSerCreador()).isEqualTo(DEFAULT_ID_SER_CREADOR);
        assertThat(testAlumno.getIdProvincia()).isEqualTo(DEFAULT_ID_PROVINCIA);
    }

    @Test
    @Transactional
    void createAlumnoWithExistingId() throws Exception {
        // Create the Alumno with an existing ID
        alumno.setId(1L);

        int databaseSizeBeforeCreate = alumnoRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAlumnoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(alumno)))
            .andExpect(status().isBadRequest());

        // Validate the Alumno in the database
        List<Alumno> alumnoList = alumnoRepository.findAll();
        assertThat(alumnoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAlumnos() throws Exception {
        // Initialize the database
        alumnoRepository.saveAndFlush(alumno);

        // Get all the alumnoList
        restAlumnoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(alumno.getId().intValue())))
            .andExpect(jsonPath("$.[*].documento").value(hasItem(DEFAULT_DOCUMENTO)))
            .andExpect(jsonPath("$.[*].idTipoDocumento").value(hasItem(DEFAULT_ID_TIPO_DOCUMENTO.intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].apellido").value(hasItem(DEFAULT_APELLIDO)))
            .andExpect(jsonPath("$.[*].fechaNacimento").value(hasItem(DEFAULT_FECHA_NACIMENTO.toString())))
            .andExpect(jsonPath("$.[*].sexo").value(hasItem(DEFAULT_SEXO)))
            .andExpect(jsonPath("$.[*].ciudadNacimiento").value(hasItem(DEFAULT_CIUDAD_NACIMIENTO)))
            .andExpect(jsonPath("$.[*].provinciaNacimiento").value(hasItem(DEFAULT_PROVINCIA_NACIMIENTO)))
            .andExpect(jsonPath("$.[*].idNacionalidad").value(hasItem(DEFAULT_ID_NACIONALIDAD.intValue())))
            .andExpect(jsonPath("$.[*].idSerCreador").value(hasItem(DEFAULT_ID_SER_CREADOR.intValue())))
            .andExpect(jsonPath("$.[*].idProvincia").value(hasItem(DEFAULT_ID_PROVINCIA.intValue())));
    }

    @Test
    @Transactional
    void getAlumno() throws Exception {
        // Initialize the database
        alumnoRepository.saveAndFlush(alumno);

        // Get the alumno
        restAlumnoMockMvc
            .perform(get(ENTITY_API_URL_ID, alumno.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(alumno.getId().intValue()))
            .andExpect(jsonPath("$.documento").value(DEFAULT_DOCUMENTO))
            .andExpect(jsonPath("$.idTipoDocumento").value(DEFAULT_ID_TIPO_DOCUMENTO.intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.apellido").value(DEFAULT_APELLIDO))
            .andExpect(jsonPath("$.fechaNacimento").value(DEFAULT_FECHA_NACIMENTO.toString()))
            .andExpect(jsonPath("$.sexo").value(DEFAULT_SEXO))
            .andExpect(jsonPath("$.ciudadNacimiento").value(DEFAULT_CIUDAD_NACIMIENTO))
            .andExpect(jsonPath("$.provinciaNacimiento").value(DEFAULT_PROVINCIA_NACIMIENTO))
            .andExpect(jsonPath("$.idNacionalidad").value(DEFAULT_ID_NACIONALIDAD.intValue()))
            .andExpect(jsonPath("$.idSerCreador").value(DEFAULT_ID_SER_CREADOR.intValue()))
            .andExpect(jsonPath("$.idProvincia").value(DEFAULT_ID_PROVINCIA.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingAlumno() throws Exception {
        // Get the alumno
        restAlumnoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAlumno() throws Exception {
        // Initialize the database
        alumnoRepository.saveAndFlush(alumno);

        int databaseSizeBeforeUpdate = alumnoRepository.findAll().size();

        // Update the alumno
        Alumno updatedAlumno = alumnoRepository.findById(alumno.getId()).get();
        // Disconnect from session so that the updates on updatedAlumno are not directly saved in db
        em.detach(updatedAlumno);
        updatedAlumno
            .documento(UPDATED_DOCUMENTO)
            .idTipoDocumento(UPDATED_ID_TIPO_DOCUMENTO)
            .nombre(UPDATED_NOMBRE)
            .apellido(UPDATED_APELLIDO)
            .fechaNacimento(UPDATED_FECHA_NACIMENTO)
            .sexo(UPDATED_SEXO)
            .ciudadNacimiento(UPDATED_CIUDAD_NACIMIENTO)
            .provinciaNacimiento(UPDATED_PROVINCIA_NACIMIENTO)
            .idNacionalidad(UPDATED_ID_NACIONALIDAD)
            .idSerCreador(UPDATED_ID_SER_CREADOR)
            .idProvincia(UPDATED_ID_PROVINCIA);

        restAlumnoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAlumno.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAlumno))
            )
            .andExpect(status().isOk());

        // Validate the Alumno in the database
        List<Alumno> alumnoList = alumnoRepository.findAll();
        assertThat(alumnoList).hasSize(databaseSizeBeforeUpdate);
        Alumno testAlumno = alumnoList.get(alumnoList.size() - 1);
        assertThat(testAlumno.getDocumento()).isEqualTo(UPDATED_DOCUMENTO);
        assertThat(testAlumno.getIdTipoDocumento()).isEqualTo(UPDATED_ID_TIPO_DOCUMENTO);
        assertThat(testAlumno.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testAlumno.getApellido()).isEqualTo(UPDATED_APELLIDO);
        assertThat(testAlumno.getFechaNacimento()).isEqualTo(UPDATED_FECHA_NACIMENTO);
        assertThat(testAlumno.getSexo()).isEqualTo(UPDATED_SEXO);
        assertThat(testAlumno.getCiudadNacimiento()).isEqualTo(UPDATED_CIUDAD_NACIMIENTO);
        assertThat(testAlumno.getProvinciaNacimiento()).isEqualTo(UPDATED_PROVINCIA_NACIMIENTO);
        assertThat(testAlumno.getIdNacionalidad()).isEqualTo(UPDATED_ID_NACIONALIDAD);
        assertThat(testAlumno.getIdSerCreador()).isEqualTo(UPDATED_ID_SER_CREADOR);
        assertThat(testAlumno.getIdProvincia()).isEqualTo(UPDATED_ID_PROVINCIA);
    }

    @Test
    @Transactional
    void putNonExistingAlumno() throws Exception {
        int databaseSizeBeforeUpdate = alumnoRepository.findAll().size();
        alumno.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAlumnoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, alumno.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(alumno))
            )
            .andExpect(status().isBadRequest());

        // Validate the Alumno in the database
        List<Alumno> alumnoList = alumnoRepository.findAll();
        assertThat(alumnoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAlumno() throws Exception {
        int databaseSizeBeforeUpdate = alumnoRepository.findAll().size();
        alumno.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAlumnoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(alumno))
            )
            .andExpect(status().isBadRequest());

        // Validate the Alumno in the database
        List<Alumno> alumnoList = alumnoRepository.findAll();
        assertThat(alumnoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAlumno() throws Exception {
        int databaseSizeBeforeUpdate = alumnoRepository.findAll().size();
        alumno.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAlumnoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(alumno)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Alumno in the database
        List<Alumno> alumnoList = alumnoRepository.findAll();
        assertThat(alumnoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAlumnoWithPatch() throws Exception {
        // Initialize the database
        alumnoRepository.saveAndFlush(alumno);

        int databaseSizeBeforeUpdate = alumnoRepository.findAll().size();

        // Update the alumno using partial update
        Alumno partialUpdatedAlumno = new Alumno();
        partialUpdatedAlumno.setId(alumno.getId());

        partialUpdatedAlumno
            .idTipoDocumento(UPDATED_ID_TIPO_DOCUMENTO)
            .apellido(UPDATED_APELLIDO)
            .fechaNacimento(UPDATED_FECHA_NACIMENTO)
            .sexo(UPDATED_SEXO)
            .idSerCreador(UPDATED_ID_SER_CREADOR);

        restAlumnoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAlumno.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAlumno))
            )
            .andExpect(status().isOk());

        // Validate the Alumno in the database
        List<Alumno> alumnoList = alumnoRepository.findAll();
        assertThat(alumnoList).hasSize(databaseSizeBeforeUpdate);
        Alumno testAlumno = alumnoList.get(alumnoList.size() - 1);
        assertThat(testAlumno.getDocumento()).isEqualTo(DEFAULT_DOCUMENTO);
        assertThat(testAlumno.getIdTipoDocumento()).isEqualTo(UPDATED_ID_TIPO_DOCUMENTO);
        assertThat(testAlumno.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testAlumno.getApellido()).isEqualTo(UPDATED_APELLIDO);
        assertThat(testAlumno.getFechaNacimento()).isEqualTo(UPDATED_FECHA_NACIMENTO);
        assertThat(testAlumno.getSexo()).isEqualTo(UPDATED_SEXO);
        assertThat(testAlumno.getCiudadNacimiento()).isEqualTo(DEFAULT_CIUDAD_NACIMIENTO);
        assertThat(testAlumno.getProvinciaNacimiento()).isEqualTo(DEFAULT_PROVINCIA_NACIMIENTO);
        assertThat(testAlumno.getIdNacionalidad()).isEqualTo(DEFAULT_ID_NACIONALIDAD);
        assertThat(testAlumno.getIdSerCreador()).isEqualTo(UPDATED_ID_SER_CREADOR);
        assertThat(testAlumno.getIdProvincia()).isEqualTo(DEFAULT_ID_PROVINCIA);
    }

    @Test
    @Transactional
    void fullUpdateAlumnoWithPatch() throws Exception {
        // Initialize the database
        alumnoRepository.saveAndFlush(alumno);

        int databaseSizeBeforeUpdate = alumnoRepository.findAll().size();

        // Update the alumno using partial update
        Alumno partialUpdatedAlumno = new Alumno();
        partialUpdatedAlumno.setId(alumno.getId());

        partialUpdatedAlumno
            .documento(UPDATED_DOCUMENTO)
            .idTipoDocumento(UPDATED_ID_TIPO_DOCUMENTO)
            .nombre(UPDATED_NOMBRE)
            .apellido(UPDATED_APELLIDO)
            .fechaNacimento(UPDATED_FECHA_NACIMENTO)
            .sexo(UPDATED_SEXO)
            .ciudadNacimiento(UPDATED_CIUDAD_NACIMIENTO)
            .provinciaNacimiento(UPDATED_PROVINCIA_NACIMIENTO)
            .idNacionalidad(UPDATED_ID_NACIONALIDAD)
            .idSerCreador(UPDATED_ID_SER_CREADOR)
            .idProvincia(UPDATED_ID_PROVINCIA);

        restAlumnoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAlumno.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAlumno))
            )
            .andExpect(status().isOk());

        // Validate the Alumno in the database
        List<Alumno> alumnoList = alumnoRepository.findAll();
        assertThat(alumnoList).hasSize(databaseSizeBeforeUpdate);
        Alumno testAlumno = alumnoList.get(alumnoList.size() - 1);
        assertThat(testAlumno.getDocumento()).isEqualTo(UPDATED_DOCUMENTO);
        assertThat(testAlumno.getIdTipoDocumento()).isEqualTo(UPDATED_ID_TIPO_DOCUMENTO);
        assertThat(testAlumno.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testAlumno.getApellido()).isEqualTo(UPDATED_APELLIDO);
        assertThat(testAlumno.getFechaNacimento()).isEqualTo(UPDATED_FECHA_NACIMENTO);
        assertThat(testAlumno.getSexo()).isEqualTo(UPDATED_SEXO);
        assertThat(testAlumno.getCiudadNacimiento()).isEqualTo(UPDATED_CIUDAD_NACIMIENTO);
        assertThat(testAlumno.getProvinciaNacimiento()).isEqualTo(UPDATED_PROVINCIA_NACIMIENTO);
        assertThat(testAlumno.getIdNacionalidad()).isEqualTo(UPDATED_ID_NACIONALIDAD);
        assertThat(testAlumno.getIdSerCreador()).isEqualTo(UPDATED_ID_SER_CREADOR);
        assertThat(testAlumno.getIdProvincia()).isEqualTo(UPDATED_ID_PROVINCIA);
    }

    @Test
    @Transactional
    void patchNonExistingAlumno() throws Exception {
        int databaseSizeBeforeUpdate = alumnoRepository.findAll().size();
        alumno.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAlumnoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, alumno.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(alumno))
            )
            .andExpect(status().isBadRequest());

        // Validate the Alumno in the database
        List<Alumno> alumnoList = alumnoRepository.findAll();
        assertThat(alumnoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAlumno() throws Exception {
        int databaseSizeBeforeUpdate = alumnoRepository.findAll().size();
        alumno.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAlumnoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(alumno))
            )
            .andExpect(status().isBadRequest());

        // Validate the Alumno in the database
        List<Alumno> alumnoList = alumnoRepository.findAll();
        assertThat(alumnoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAlumno() throws Exception {
        int databaseSizeBeforeUpdate = alumnoRepository.findAll().size();
        alumno.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAlumnoMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(alumno)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Alumno in the database
        List<Alumno> alumnoList = alumnoRepository.findAll();
        assertThat(alumnoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAlumno() throws Exception {
        // Initialize the database
        alumnoRepository.saveAndFlush(alumno);

        int databaseSizeBeforeDelete = alumnoRepository.findAll().size();

        // Delete the alumno
        restAlumnoMockMvc
            .perform(delete(ENTITY_API_URL_ID, alumno.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Alumno> alumnoList = alumnoRepository.findAll();
        assertThat(alumnoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
