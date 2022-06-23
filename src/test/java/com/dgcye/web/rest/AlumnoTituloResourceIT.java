package com.dgcye.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dgcye.IntegrationTest;
import com.dgcye.domain.AlumnoTitulo;
import com.dgcye.repository.AlumnoTituloRepository;
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
 * Integration tests for the {@link AlumnoTituloResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AlumnoTituloResourceIT {

    private static final Long DEFAULT_ID_ALUMNO_ESTAB_OFERTA = 1L;
    private static final Long UPDATED_ID_ALUMNO_ESTAB_OFERTA = 2L;

    private static final Instant DEFAULT_FECHA_EMISION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FECHA_EMISION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_NRO_SERIE = "AAAAAAAAAA";
    private static final String UPDATED_NRO_SERIE = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_RFIFD = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_RFIFD = "BBBBBBBBBB";

    private static final String DEFAULT_VALIDEZ_NACIONAL = "AAAAAAAAAA";
    private static final String UPDATED_VALIDEZ_NACIONAL = "BBBBBBBBBB";

    private static final Instant DEFAULT_FECHA_ENVIO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FECHA_ENVIO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_FECHA_EGRESO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FECHA_EGRESO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_NRO_LIBRO_MATRIZ = 1;
    private static final Integer UPDATED_NRO_LIBRO_MATRIZ = 2;

    private static final Integer DEFAULT_NRO_ACTA = 1;
    private static final Integer UPDATED_NRO_ACTA = 2;

    private static final Integer DEFAULT_NRO_FOLIO = 1;
    private static final Integer UPDATED_NRO_FOLIO = 2;

    private static final String DEFAULT_ESTAB_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_ESTAB_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_ESTAB_CUE = "AAAAAAAAAA";
    private static final String UPDATED_ESTAB_CUE = "BBBBBBBBBB";

    private static final String DEFAULT_ESTAB_UBICADO_EN = "AAAAAAAAAA";
    private static final String UPDATED_ESTAB_UBICADO_EN = "BBBBBBBBBB";

    private static final String DEFAULT_ESTAB_CIUDAD = "AAAAAAAAAA";
    private static final String UPDATED_ESTAB_CIUDAD = "BBBBBBBBBB";

    private static final String DEFAULT_ESTAB_PROVINCIA = "AAAAAAAAAA";
    private static final String UPDATED_ESTAB_PROVINCIA = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_ESTADO_ALUMNO_TITULO = 1L;
    private static final Long UPDATED_ID_ESTADO_ALUMNO_TITULO = 2L;

    private static final Integer DEFAULT_TITULO_INTERMEDIO = 1;
    private static final Integer UPDATED_TITULO_INTERMEDIO = 2;

    private static final String DEFAULT_PROMEDIO = "AAAAAAAAAA";
    private static final String UPDATED_PROMEDIO = "BBBBBBBBBB";

    private static final Instant DEFAULT_FECHA_RUTA = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FECHA_RUTA = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_ID_RAMA_RUTA = 1L;
    private static final Long UPDATED_ID_RAMA_RUTA = 2L;

    private static final String DEFAULT_AP_YNOM_LISTO_PARA_ENVIAR = "AAAAAAAAAA";
    private static final String UPDATED_AP_YNOM_LISTO_PARA_ENVIAR = "BBBBBBBBBB";

    private static final String DEFAULT_DOCUMENTO_LISTO_PARA_ENVIAR = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENTO_LISTO_PARA_ENVIAR = "BBBBBBBBBB";

    private static final String DEFAULT_AP_YNOM_ENVIADO = "AAAAAAAAAA";
    private static final String UPDATED_AP_YNOM_ENVIADO = "BBBBBBBBBB";

    private static final String DEFAULT_DOCUMENTO_ENVIADO = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENTO_ENVIADO = "BBBBBBBBBB";

    private static final Instant DEFAULT_FECHA_ULTIMO_ESTADO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FECHA_ULTIMO_ESTADO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/alumno-titulos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AlumnoTituloRepository alumnoTituloRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAlumnoTituloMockMvc;

    private AlumnoTitulo alumnoTitulo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AlumnoTitulo createEntity(EntityManager em) {
        AlumnoTitulo alumnoTitulo = new AlumnoTitulo()
            .idAlumnoEstabOferta(DEFAULT_ID_ALUMNO_ESTAB_OFERTA)
            .fechaEmision(DEFAULT_FECHA_EMISION)
            .nroSerie(DEFAULT_NRO_SERIE)
            .numeroRfifd(DEFAULT_NUMERO_RFIFD)
            .validezNacional(DEFAULT_VALIDEZ_NACIONAL)
            .fechaEnvio(DEFAULT_FECHA_ENVIO)
            .fechaEgreso(DEFAULT_FECHA_EGRESO)
            .nroLibroMatriz(DEFAULT_NRO_LIBRO_MATRIZ)
            .nroActa(DEFAULT_NRO_ACTA)
            .nroFolio(DEFAULT_NRO_FOLIO)
            .estabNombre(DEFAULT_ESTAB_NOMBRE)
            .estabCue(DEFAULT_ESTAB_CUE)
            .estabUbicadoEn(DEFAULT_ESTAB_UBICADO_EN)
            .estabCiudad(DEFAULT_ESTAB_CIUDAD)
            .estabProvincia(DEFAULT_ESTAB_PROVINCIA)
            .idEstadoAlumnoTitulo(DEFAULT_ID_ESTADO_ALUMNO_TITULO)
            .tituloIntermedio(DEFAULT_TITULO_INTERMEDIO)
            .promedio(DEFAULT_PROMEDIO)
            .fechaRuta(DEFAULT_FECHA_RUTA)
            .idRamaRuta(DEFAULT_ID_RAMA_RUTA)
            .apYnomListoParaEnviar(DEFAULT_AP_YNOM_LISTO_PARA_ENVIAR)
            .documentoListoParaEnviar(DEFAULT_DOCUMENTO_LISTO_PARA_ENVIAR)
            .apYnomEnviado(DEFAULT_AP_YNOM_ENVIADO)
            .documentoEnviado(DEFAULT_DOCUMENTO_ENVIADO)
            .fechaUltimoEstado(DEFAULT_FECHA_ULTIMO_ESTADO);
        return alumnoTitulo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AlumnoTitulo createUpdatedEntity(EntityManager em) {
        AlumnoTitulo alumnoTitulo = new AlumnoTitulo()
            .idAlumnoEstabOferta(UPDATED_ID_ALUMNO_ESTAB_OFERTA)
            .fechaEmision(UPDATED_FECHA_EMISION)
            .nroSerie(UPDATED_NRO_SERIE)
            .numeroRfifd(UPDATED_NUMERO_RFIFD)
            .validezNacional(UPDATED_VALIDEZ_NACIONAL)
            .fechaEnvio(UPDATED_FECHA_ENVIO)
            .fechaEgreso(UPDATED_FECHA_EGRESO)
            .nroLibroMatriz(UPDATED_NRO_LIBRO_MATRIZ)
            .nroActa(UPDATED_NRO_ACTA)
            .nroFolio(UPDATED_NRO_FOLIO)
            .estabNombre(UPDATED_ESTAB_NOMBRE)
            .estabCue(UPDATED_ESTAB_CUE)
            .estabUbicadoEn(UPDATED_ESTAB_UBICADO_EN)
            .estabCiudad(UPDATED_ESTAB_CIUDAD)
            .estabProvincia(UPDATED_ESTAB_PROVINCIA)
            .idEstadoAlumnoTitulo(UPDATED_ID_ESTADO_ALUMNO_TITULO)
            .tituloIntermedio(UPDATED_TITULO_INTERMEDIO)
            .promedio(UPDATED_PROMEDIO)
            .fechaRuta(UPDATED_FECHA_RUTA)
            .idRamaRuta(UPDATED_ID_RAMA_RUTA)
            .apYnomListoParaEnviar(UPDATED_AP_YNOM_LISTO_PARA_ENVIAR)
            .documentoListoParaEnviar(UPDATED_DOCUMENTO_LISTO_PARA_ENVIAR)
            .apYnomEnviado(UPDATED_AP_YNOM_ENVIADO)
            .documentoEnviado(UPDATED_DOCUMENTO_ENVIADO)
            .fechaUltimoEstado(UPDATED_FECHA_ULTIMO_ESTADO);
        return alumnoTitulo;
    }

    @BeforeEach
    public void initTest() {
        alumnoTitulo = createEntity(em);
    }

    @Test
    @Transactional
    void createAlumnoTitulo() throws Exception {
        int databaseSizeBeforeCreate = alumnoTituloRepository.findAll().size();
        // Create the AlumnoTitulo
        restAlumnoTituloMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(alumnoTitulo)))
            .andExpect(status().isCreated());

        // Validate the AlumnoTitulo in the database
        List<AlumnoTitulo> alumnoTituloList = alumnoTituloRepository.findAll();
        assertThat(alumnoTituloList).hasSize(databaseSizeBeforeCreate + 1);
        AlumnoTitulo testAlumnoTitulo = alumnoTituloList.get(alumnoTituloList.size() - 1);
        assertThat(testAlumnoTitulo.getIdAlumnoEstabOferta()).isEqualTo(DEFAULT_ID_ALUMNO_ESTAB_OFERTA);
        assertThat(testAlumnoTitulo.getFechaEmision()).isEqualTo(DEFAULT_FECHA_EMISION);
        assertThat(testAlumnoTitulo.getNroSerie()).isEqualTo(DEFAULT_NRO_SERIE);
        assertThat(testAlumnoTitulo.getNumeroRfifd()).isEqualTo(DEFAULT_NUMERO_RFIFD);
        assertThat(testAlumnoTitulo.getValidezNacional()).isEqualTo(DEFAULT_VALIDEZ_NACIONAL);
        assertThat(testAlumnoTitulo.getFechaEnvio()).isEqualTo(DEFAULT_FECHA_ENVIO);
        assertThat(testAlumnoTitulo.getFechaEgreso()).isEqualTo(DEFAULT_FECHA_EGRESO);
        assertThat(testAlumnoTitulo.getNroLibroMatriz()).isEqualTo(DEFAULT_NRO_LIBRO_MATRIZ);
        assertThat(testAlumnoTitulo.getNroActa()).isEqualTo(DEFAULT_NRO_ACTA);
        assertThat(testAlumnoTitulo.getNroFolio()).isEqualTo(DEFAULT_NRO_FOLIO);
        assertThat(testAlumnoTitulo.getEstabNombre()).isEqualTo(DEFAULT_ESTAB_NOMBRE);
        assertThat(testAlumnoTitulo.getEstabCue()).isEqualTo(DEFAULT_ESTAB_CUE);
        assertThat(testAlumnoTitulo.getEstabUbicadoEn()).isEqualTo(DEFAULT_ESTAB_UBICADO_EN);
        assertThat(testAlumnoTitulo.getEstabCiudad()).isEqualTo(DEFAULT_ESTAB_CIUDAD);
        assertThat(testAlumnoTitulo.getEstabProvincia()).isEqualTo(DEFAULT_ESTAB_PROVINCIA);
        assertThat(testAlumnoTitulo.getIdEstadoAlumnoTitulo()).isEqualTo(DEFAULT_ID_ESTADO_ALUMNO_TITULO);
        assertThat(testAlumnoTitulo.getTituloIntermedio()).isEqualTo(DEFAULT_TITULO_INTERMEDIO);
        assertThat(testAlumnoTitulo.getPromedio()).isEqualTo(DEFAULT_PROMEDIO);
        assertThat(testAlumnoTitulo.getFechaRuta()).isEqualTo(DEFAULT_FECHA_RUTA);
        assertThat(testAlumnoTitulo.getIdRamaRuta()).isEqualTo(DEFAULT_ID_RAMA_RUTA);
        assertThat(testAlumnoTitulo.getApYnomListoParaEnviar()).isEqualTo(DEFAULT_AP_YNOM_LISTO_PARA_ENVIAR);
        assertThat(testAlumnoTitulo.getDocumentoListoParaEnviar()).isEqualTo(DEFAULT_DOCUMENTO_LISTO_PARA_ENVIAR);
        assertThat(testAlumnoTitulo.getApYnomEnviado()).isEqualTo(DEFAULT_AP_YNOM_ENVIADO);
        assertThat(testAlumnoTitulo.getDocumentoEnviado()).isEqualTo(DEFAULT_DOCUMENTO_ENVIADO);
        assertThat(testAlumnoTitulo.getFechaUltimoEstado()).isEqualTo(DEFAULT_FECHA_ULTIMO_ESTADO);
    }

    @Test
    @Transactional
    void createAlumnoTituloWithExistingId() throws Exception {
        // Create the AlumnoTitulo with an existing ID
        alumnoTitulo.setId(1L);

        int databaseSizeBeforeCreate = alumnoTituloRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAlumnoTituloMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(alumnoTitulo)))
            .andExpect(status().isBadRequest());

        // Validate the AlumnoTitulo in the database
        List<AlumnoTitulo> alumnoTituloList = alumnoTituloRepository.findAll();
        assertThat(alumnoTituloList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAlumnoTitulos() throws Exception {
        // Initialize the database
        alumnoTituloRepository.saveAndFlush(alumnoTitulo);

        // Get all the alumnoTituloList
        restAlumnoTituloMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(alumnoTitulo.getId().intValue())))
            .andExpect(jsonPath("$.[*].idAlumnoEstabOferta").value(hasItem(DEFAULT_ID_ALUMNO_ESTAB_OFERTA.intValue())))
            .andExpect(jsonPath("$.[*].fechaEmision").value(hasItem(DEFAULT_FECHA_EMISION.toString())))
            .andExpect(jsonPath("$.[*].nroSerie").value(hasItem(DEFAULT_NRO_SERIE)))
            .andExpect(jsonPath("$.[*].numeroRfifd").value(hasItem(DEFAULT_NUMERO_RFIFD)))
            .andExpect(jsonPath("$.[*].validezNacional").value(hasItem(DEFAULT_VALIDEZ_NACIONAL)))
            .andExpect(jsonPath("$.[*].fechaEnvio").value(hasItem(DEFAULT_FECHA_ENVIO.toString())))
            .andExpect(jsonPath("$.[*].fechaEgreso").value(hasItem(DEFAULT_FECHA_EGRESO.toString())))
            .andExpect(jsonPath("$.[*].nroLibroMatriz").value(hasItem(DEFAULT_NRO_LIBRO_MATRIZ)))
            .andExpect(jsonPath("$.[*].nroActa").value(hasItem(DEFAULT_NRO_ACTA)))
            .andExpect(jsonPath("$.[*].nroFolio").value(hasItem(DEFAULT_NRO_FOLIO)))
            .andExpect(jsonPath("$.[*].estabNombre").value(hasItem(DEFAULT_ESTAB_NOMBRE)))
            .andExpect(jsonPath("$.[*].estabCue").value(hasItem(DEFAULT_ESTAB_CUE)))
            .andExpect(jsonPath("$.[*].estabUbicadoEn").value(hasItem(DEFAULT_ESTAB_UBICADO_EN)))
            .andExpect(jsonPath("$.[*].estabCiudad").value(hasItem(DEFAULT_ESTAB_CIUDAD)))
            .andExpect(jsonPath("$.[*].estabProvincia").value(hasItem(DEFAULT_ESTAB_PROVINCIA)))
            .andExpect(jsonPath("$.[*].idEstadoAlumnoTitulo").value(hasItem(DEFAULT_ID_ESTADO_ALUMNO_TITULO.intValue())))
            .andExpect(jsonPath("$.[*].tituloIntermedio").value(hasItem(DEFAULT_TITULO_INTERMEDIO)))
            .andExpect(jsonPath("$.[*].promedio").value(hasItem(DEFAULT_PROMEDIO)))
            .andExpect(jsonPath("$.[*].fechaRuta").value(hasItem(DEFAULT_FECHA_RUTA.toString())))
            .andExpect(jsonPath("$.[*].idRamaRuta").value(hasItem(DEFAULT_ID_RAMA_RUTA.intValue())))
            .andExpect(jsonPath("$.[*].apYnomListoParaEnviar").value(hasItem(DEFAULT_AP_YNOM_LISTO_PARA_ENVIAR)))
            .andExpect(jsonPath("$.[*].documentoListoParaEnviar").value(hasItem(DEFAULT_DOCUMENTO_LISTO_PARA_ENVIAR)))
            .andExpect(jsonPath("$.[*].apYnomEnviado").value(hasItem(DEFAULT_AP_YNOM_ENVIADO)))
            .andExpect(jsonPath("$.[*].documentoEnviado").value(hasItem(DEFAULT_DOCUMENTO_ENVIADO)))
            .andExpect(jsonPath("$.[*].fechaUltimoEstado").value(hasItem(DEFAULT_FECHA_ULTIMO_ESTADO.toString())));
    }

    @Test
    @Transactional
    void getAlumnoTitulo() throws Exception {
        // Initialize the database
        alumnoTituloRepository.saveAndFlush(alumnoTitulo);

        // Get the alumnoTitulo
        restAlumnoTituloMockMvc
            .perform(get(ENTITY_API_URL_ID, alumnoTitulo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(alumnoTitulo.getId().intValue()))
            .andExpect(jsonPath("$.idAlumnoEstabOferta").value(DEFAULT_ID_ALUMNO_ESTAB_OFERTA.intValue()))
            .andExpect(jsonPath("$.fechaEmision").value(DEFAULT_FECHA_EMISION.toString()))
            .andExpect(jsonPath("$.nroSerie").value(DEFAULT_NRO_SERIE))
            .andExpect(jsonPath("$.numeroRfifd").value(DEFAULT_NUMERO_RFIFD))
            .andExpect(jsonPath("$.validezNacional").value(DEFAULT_VALIDEZ_NACIONAL))
            .andExpect(jsonPath("$.fechaEnvio").value(DEFAULT_FECHA_ENVIO.toString()))
            .andExpect(jsonPath("$.fechaEgreso").value(DEFAULT_FECHA_EGRESO.toString()))
            .andExpect(jsonPath("$.nroLibroMatriz").value(DEFAULT_NRO_LIBRO_MATRIZ))
            .andExpect(jsonPath("$.nroActa").value(DEFAULT_NRO_ACTA))
            .andExpect(jsonPath("$.nroFolio").value(DEFAULT_NRO_FOLIO))
            .andExpect(jsonPath("$.estabNombre").value(DEFAULT_ESTAB_NOMBRE))
            .andExpect(jsonPath("$.estabCue").value(DEFAULT_ESTAB_CUE))
            .andExpect(jsonPath("$.estabUbicadoEn").value(DEFAULT_ESTAB_UBICADO_EN))
            .andExpect(jsonPath("$.estabCiudad").value(DEFAULT_ESTAB_CIUDAD))
            .andExpect(jsonPath("$.estabProvincia").value(DEFAULT_ESTAB_PROVINCIA))
            .andExpect(jsonPath("$.idEstadoAlumnoTitulo").value(DEFAULT_ID_ESTADO_ALUMNO_TITULO.intValue()))
            .andExpect(jsonPath("$.tituloIntermedio").value(DEFAULT_TITULO_INTERMEDIO))
            .andExpect(jsonPath("$.promedio").value(DEFAULT_PROMEDIO))
            .andExpect(jsonPath("$.fechaRuta").value(DEFAULT_FECHA_RUTA.toString()))
            .andExpect(jsonPath("$.idRamaRuta").value(DEFAULT_ID_RAMA_RUTA.intValue()))
            .andExpect(jsonPath("$.apYnomListoParaEnviar").value(DEFAULT_AP_YNOM_LISTO_PARA_ENVIAR))
            .andExpect(jsonPath("$.documentoListoParaEnviar").value(DEFAULT_DOCUMENTO_LISTO_PARA_ENVIAR))
            .andExpect(jsonPath("$.apYnomEnviado").value(DEFAULT_AP_YNOM_ENVIADO))
            .andExpect(jsonPath("$.documentoEnviado").value(DEFAULT_DOCUMENTO_ENVIADO))
            .andExpect(jsonPath("$.fechaUltimoEstado").value(DEFAULT_FECHA_ULTIMO_ESTADO.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAlumnoTitulo() throws Exception {
        // Get the alumnoTitulo
        restAlumnoTituloMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAlumnoTitulo() throws Exception {
        // Initialize the database
        alumnoTituloRepository.saveAndFlush(alumnoTitulo);

        int databaseSizeBeforeUpdate = alumnoTituloRepository.findAll().size();

        // Update the alumnoTitulo
        AlumnoTitulo updatedAlumnoTitulo = alumnoTituloRepository.findById(alumnoTitulo.getId()).get();
        // Disconnect from session so that the updates on updatedAlumnoTitulo are not directly saved in db
        em.detach(updatedAlumnoTitulo);
        updatedAlumnoTitulo
            .idAlumnoEstabOferta(UPDATED_ID_ALUMNO_ESTAB_OFERTA)
            .fechaEmision(UPDATED_FECHA_EMISION)
            .nroSerie(UPDATED_NRO_SERIE)
            .numeroRfifd(UPDATED_NUMERO_RFIFD)
            .validezNacional(UPDATED_VALIDEZ_NACIONAL)
            .fechaEnvio(UPDATED_FECHA_ENVIO)
            .fechaEgreso(UPDATED_FECHA_EGRESO)
            .nroLibroMatriz(UPDATED_NRO_LIBRO_MATRIZ)
            .nroActa(UPDATED_NRO_ACTA)
            .nroFolio(UPDATED_NRO_FOLIO)
            .estabNombre(UPDATED_ESTAB_NOMBRE)
            .estabCue(UPDATED_ESTAB_CUE)
            .estabUbicadoEn(UPDATED_ESTAB_UBICADO_EN)
            .estabCiudad(UPDATED_ESTAB_CIUDAD)
            .estabProvincia(UPDATED_ESTAB_PROVINCIA)
            .idEstadoAlumnoTitulo(UPDATED_ID_ESTADO_ALUMNO_TITULO)
            .tituloIntermedio(UPDATED_TITULO_INTERMEDIO)
            .promedio(UPDATED_PROMEDIO)
            .fechaRuta(UPDATED_FECHA_RUTA)
            .idRamaRuta(UPDATED_ID_RAMA_RUTA)
            .apYnomListoParaEnviar(UPDATED_AP_YNOM_LISTO_PARA_ENVIAR)
            .documentoListoParaEnviar(UPDATED_DOCUMENTO_LISTO_PARA_ENVIAR)
            .apYnomEnviado(UPDATED_AP_YNOM_ENVIADO)
            .documentoEnviado(UPDATED_DOCUMENTO_ENVIADO)
            .fechaUltimoEstado(UPDATED_FECHA_ULTIMO_ESTADO);

        restAlumnoTituloMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAlumnoTitulo.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAlumnoTitulo))
            )
            .andExpect(status().isOk());

        // Validate the AlumnoTitulo in the database
        List<AlumnoTitulo> alumnoTituloList = alumnoTituloRepository.findAll();
        assertThat(alumnoTituloList).hasSize(databaseSizeBeforeUpdate);
        AlumnoTitulo testAlumnoTitulo = alumnoTituloList.get(alumnoTituloList.size() - 1);
        assertThat(testAlumnoTitulo.getIdAlumnoEstabOferta()).isEqualTo(UPDATED_ID_ALUMNO_ESTAB_OFERTA);
        assertThat(testAlumnoTitulo.getFechaEmision()).isEqualTo(UPDATED_FECHA_EMISION);
        assertThat(testAlumnoTitulo.getNroSerie()).isEqualTo(UPDATED_NRO_SERIE);
        assertThat(testAlumnoTitulo.getNumeroRfifd()).isEqualTo(UPDATED_NUMERO_RFIFD);
        assertThat(testAlumnoTitulo.getValidezNacional()).isEqualTo(UPDATED_VALIDEZ_NACIONAL);
        assertThat(testAlumnoTitulo.getFechaEnvio()).isEqualTo(UPDATED_FECHA_ENVIO);
        assertThat(testAlumnoTitulo.getFechaEgreso()).isEqualTo(UPDATED_FECHA_EGRESO);
        assertThat(testAlumnoTitulo.getNroLibroMatriz()).isEqualTo(UPDATED_NRO_LIBRO_MATRIZ);
        assertThat(testAlumnoTitulo.getNroActa()).isEqualTo(UPDATED_NRO_ACTA);
        assertThat(testAlumnoTitulo.getNroFolio()).isEqualTo(UPDATED_NRO_FOLIO);
        assertThat(testAlumnoTitulo.getEstabNombre()).isEqualTo(UPDATED_ESTAB_NOMBRE);
        assertThat(testAlumnoTitulo.getEstabCue()).isEqualTo(UPDATED_ESTAB_CUE);
        assertThat(testAlumnoTitulo.getEstabUbicadoEn()).isEqualTo(UPDATED_ESTAB_UBICADO_EN);
        assertThat(testAlumnoTitulo.getEstabCiudad()).isEqualTo(UPDATED_ESTAB_CIUDAD);
        assertThat(testAlumnoTitulo.getEstabProvincia()).isEqualTo(UPDATED_ESTAB_PROVINCIA);
        assertThat(testAlumnoTitulo.getIdEstadoAlumnoTitulo()).isEqualTo(UPDATED_ID_ESTADO_ALUMNO_TITULO);
        assertThat(testAlumnoTitulo.getTituloIntermedio()).isEqualTo(UPDATED_TITULO_INTERMEDIO);
        assertThat(testAlumnoTitulo.getPromedio()).isEqualTo(UPDATED_PROMEDIO);
        assertThat(testAlumnoTitulo.getFechaRuta()).isEqualTo(UPDATED_FECHA_RUTA);
        assertThat(testAlumnoTitulo.getIdRamaRuta()).isEqualTo(UPDATED_ID_RAMA_RUTA);
        assertThat(testAlumnoTitulo.getApYnomListoParaEnviar()).isEqualTo(UPDATED_AP_YNOM_LISTO_PARA_ENVIAR);
        assertThat(testAlumnoTitulo.getDocumentoListoParaEnviar()).isEqualTo(UPDATED_DOCUMENTO_LISTO_PARA_ENVIAR);
        assertThat(testAlumnoTitulo.getApYnomEnviado()).isEqualTo(UPDATED_AP_YNOM_ENVIADO);
        assertThat(testAlumnoTitulo.getDocumentoEnviado()).isEqualTo(UPDATED_DOCUMENTO_ENVIADO);
        assertThat(testAlumnoTitulo.getFechaUltimoEstado()).isEqualTo(UPDATED_FECHA_ULTIMO_ESTADO);
    }

    @Test
    @Transactional
    void putNonExistingAlumnoTitulo() throws Exception {
        int databaseSizeBeforeUpdate = alumnoTituloRepository.findAll().size();
        alumnoTitulo.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAlumnoTituloMockMvc
            .perform(
                put(ENTITY_API_URL_ID, alumnoTitulo.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(alumnoTitulo))
            )
            .andExpect(status().isBadRequest());

        // Validate the AlumnoTitulo in the database
        List<AlumnoTitulo> alumnoTituloList = alumnoTituloRepository.findAll();
        assertThat(alumnoTituloList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAlumnoTitulo() throws Exception {
        int databaseSizeBeforeUpdate = alumnoTituloRepository.findAll().size();
        alumnoTitulo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAlumnoTituloMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(alumnoTitulo))
            )
            .andExpect(status().isBadRequest());

        // Validate the AlumnoTitulo in the database
        List<AlumnoTitulo> alumnoTituloList = alumnoTituloRepository.findAll();
        assertThat(alumnoTituloList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAlumnoTitulo() throws Exception {
        int databaseSizeBeforeUpdate = alumnoTituloRepository.findAll().size();
        alumnoTitulo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAlumnoTituloMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(alumnoTitulo)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AlumnoTitulo in the database
        List<AlumnoTitulo> alumnoTituloList = alumnoTituloRepository.findAll();
        assertThat(alumnoTituloList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAlumnoTituloWithPatch() throws Exception {
        // Initialize the database
        alumnoTituloRepository.saveAndFlush(alumnoTitulo);

        int databaseSizeBeforeUpdate = alumnoTituloRepository.findAll().size();

        // Update the alumnoTitulo using partial update
        AlumnoTitulo partialUpdatedAlumnoTitulo = new AlumnoTitulo();
        partialUpdatedAlumnoTitulo.setId(alumnoTitulo.getId());

        partialUpdatedAlumnoTitulo
            .idAlumnoEstabOferta(UPDATED_ID_ALUMNO_ESTAB_OFERTA)
            .fechaEmision(UPDATED_FECHA_EMISION)
            .nroSerie(UPDATED_NRO_SERIE)
            .fechaEnvio(UPDATED_FECHA_ENVIO)
            .nroActa(UPDATED_NRO_ACTA)
            .nroFolio(UPDATED_NRO_FOLIO)
            .estabNombre(UPDATED_ESTAB_NOMBRE)
            .estabCue(UPDATED_ESTAB_CUE)
            .estabUbicadoEn(UPDATED_ESTAB_UBICADO_EN)
            .promedio(UPDATED_PROMEDIO)
            .fechaRuta(UPDATED_FECHA_RUTA)
            .idRamaRuta(UPDATED_ID_RAMA_RUTA)
            .apYnomListoParaEnviar(UPDATED_AP_YNOM_LISTO_PARA_ENVIAR)
            .apYnomEnviado(UPDATED_AP_YNOM_ENVIADO)
            .documentoEnviado(UPDATED_DOCUMENTO_ENVIADO);

        restAlumnoTituloMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAlumnoTitulo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAlumnoTitulo))
            )
            .andExpect(status().isOk());

        // Validate the AlumnoTitulo in the database
        List<AlumnoTitulo> alumnoTituloList = alumnoTituloRepository.findAll();
        assertThat(alumnoTituloList).hasSize(databaseSizeBeforeUpdate);
        AlumnoTitulo testAlumnoTitulo = alumnoTituloList.get(alumnoTituloList.size() - 1);
        assertThat(testAlumnoTitulo.getIdAlumnoEstabOferta()).isEqualTo(UPDATED_ID_ALUMNO_ESTAB_OFERTA);
        assertThat(testAlumnoTitulo.getFechaEmision()).isEqualTo(UPDATED_FECHA_EMISION);
        assertThat(testAlumnoTitulo.getNroSerie()).isEqualTo(UPDATED_NRO_SERIE);
        assertThat(testAlumnoTitulo.getNumeroRfifd()).isEqualTo(DEFAULT_NUMERO_RFIFD);
        assertThat(testAlumnoTitulo.getValidezNacional()).isEqualTo(DEFAULT_VALIDEZ_NACIONAL);
        assertThat(testAlumnoTitulo.getFechaEnvio()).isEqualTo(UPDATED_FECHA_ENVIO);
        assertThat(testAlumnoTitulo.getFechaEgreso()).isEqualTo(DEFAULT_FECHA_EGRESO);
        assertThat(testAlumnoTitulo.getNroLibroMatriz()).isEqualTo(DEFAULT_NRO_LIBRO_MATRIZ);
        assertThat(testAlumnoTitulo.getNroActa()).isEqualTo(UPDATED_NRO_ACTA);
        assertThat(testAlumnoTitulo.getNroFolio()).isEqualTo(UPDATED_NRO_FOLIO);
        assertThat(testAlumnoTitulo.getEstabNombre()).isEqualTo(UPDATED_ESTAB_NOMBRE);
        assertThat(testAlumnoTitulo.getEstabCue()).isEqualTo(UPDATED_ESTAB_CUE);
        assertThat(testAlumnoTitulo.getEstabUbicadoEn()).isEqualTo(UPDATED_ESTAB_UBICADO_EN);
        assertThat(testAlumnoTitulo.getEstabCiudad()).isEqualTo(DEFAULT_ESTAB_CIUDAD);
        assertThat(testAlumnoTitulo.getEstabProvincia()).isEqualTo(DEFAULT_ESTAB_PROVINCIA);
        assertThat(testAlumnoTitulo.getIdEstadoAlumnoTitulo()).isEqualTo(DEFAULT_ID_ESTADO_ALUMNO_TITULO);
        assertThat(testAlumnoTitulo.getTituloIntermedio()).isEqualTo(DEFAULT_TITULO_INTERMEDIO);
        assertThat(testAlumnoTitulo.getPromedio()).isEqualTo(UPDATED_PROMEDIO);
        assertThat(testAlumnoTitulo.getFechaRuta()).isEqualTo(UPDATED_FECHA_RUTA);
        assertThat(testAlumnoTitulo.getIdRamaRuta()).isEqualTo(UPDATED_ID_RAMA_RUTA);
        assertThat(testAlumnoTitulo.getApYnomListoParaEnviar()).isEqualTo(UPDATED_AP_YNOM_LISTO_PARA_ENVIAR);
        assertThat(testAlumnoTitulo.getDocumentoListoParaEnviar()).isEqualTo(DEFAULT_DOCUMENTO_LISTO_PARA_ENVIAR);
        assertThat(testAlumnoTitulo.getApYnomEnviado()).isEqualTo(UPDATED_AP_YNOM_ENVIADO);
        assertThat(testAlumnoTitulo.getDocumentoEnviado()).isEqualTo(UPDATED_DOCUMENTO_ENVIADO);
        assertThat(testAlumnoTitulo.getFechaUltimoEstado()).isEqualTo(DEFAULT_FECHA_ULTIMO_ESTADO);
    }

    @Test
    @Transactional
    void fullUpdateAlumnoTituloWithPatch() throws Exception {
        // Initialize the database
        alumnoTituloRepository.saveAndFlush(alumnoTitulo);

        int databaseSizeBeforeUpdate = alumnoTituloRepository.findAll().size();

        // Update the alumnoTitulo using partial update
        AlumnoTitulo partialUpdatedAlumnoTitulo = new AlumnoTitulo();
        partialUpdatedAlumnoTitulo.setId(alumnoTitulo.getId());

        partialUpdatedAlumnoTitulo
            .idAlumnoEstabOferta(UPDATED_ID_ALUMNO_ESTAB_OFERTA)
            .fechaEmision(UPDATED_FECHA_EMISION)
            .nroSerie(UPDATED_NRO_SERIE)
            .numeroRfifd(UPDATED_NUMERO_RFIFD)
            .validezNacional(UPDATED_VALIDEZ_NACIONAL)
            .fechaEnvio(UPDATED_FECHA_ENVIO)
            .fechaEgreso(UPDATED_FECHA_EGRESO)
            .nroLibroMatriz(UPDATED_NRO_LIBRO_MATRIZ)
            .nroActa(UPDATED_NRO_ACTA)
            .nroFolio(UPDATED_NRO_FOLIO)
            .estabNombre(UPDATED_ESTAB_NOMBRE)
            .estabCue(UPDATED_ESTAB_CUE)
            .estabUbicadoEn(UPDATED_ESTAB_UBICADO_EN)
            .estabCiudad(UPDATED_ESTAB_CIUDAD)
            .estabProvincia(UPDATED_ESTAB_PROVINCIA)
            .idEstadoAlumnoTitulo(UPDATED_ID_ESTADO_ALUMNO_TITULO)
            .tituloIntermedio(UPDATED_TITULO_INTERMEDIO)
            .promedio(UPDATED_PROMEDIO)
            .fechaRuta(UPDATED_FECHA_RUTA)
            .idRamaRuta(UPDATED_ID_RAMA_RUTA)
            .apYnomListoParaEnviar(UPDATED_AP_YNOM_LISTO_PARA_ENVIAR)
            .documentoListoParaEnviar(UPDATED_DOCUMENTO_LISTO_PARA_ENVIAR)
            .apYnomEnviado(UPDATED_AP_YNOM_ENVIADO)
            .documentoEnviado(UPDATED_DOCUMENTO_ENVIADO)
            .fechaUltimoEstado(UPDATED_FECHA_ULTIMO_ESTADO);

        restAlumnoTituloMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAlumnoTitulo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAlumnoTitulo))
            )
            .andExpect(status().isOk());

        // Validate the AlumnoTitulo in the database
        List<AlumnoTitulo> alumnoTituloList = alumnoTituloRepository.findAll();
        assertThat(alumnoTituloList).hasSize(databaseSizeBeforeUpdate);
        AlumnoTitulo testAlumnoTitulo = alumnoTituloList.get(alumnoTituloList.size() - 1);
        assertThat(testAlumnoTitulo.getIdAlumnoEstabOferta()).isEqualTo(UPDATED_ID_ALUMNO_ESTAB_OFERTA);
        assertThat(testAlumnoTitulo.getFechaEmision()).isEqualTo(UPDATED_FECHA_EMISION);
        assertThat(testAlumnoTitulo.getNroSerie()).isEqualTo(UPDATED_NRO_SERIE);
        assertThat(testAlumnoTitulo.getNumeroRfifd()).isEqualTo(UPDATED_NUMERO_RFIFD);
        assertThat(testAlumnoTitulo.getValidezNacional()).isEqualTo(UPDATED_VALIDEZ_NACIONAL);
        assertThat(testAlumnoTitulo.getFechaEnvio()).isEqualTo(UPDATED_FECHA_ENVIO);
        assertThat(testAlumnoTitulo.getFechaEgreso()).isEqualTo(UPDATED_FECHA_EGRESO);
        assertThat(testAlumnoTitulo.getNroLibroMatriz()).isEqualTo(UPDATED_NRO_LIBRO_MATRIZ);
        assertThat(testAlumnoTitulo.getNroActa()).isEqualTo(UPDATED_NRO_ACTA);
        assertThat(testAlumnoTitulo.getNroFolio()).isEqualTo(UPDATED_NRO_FOLIO);
        assertThat(testAlumnoTitulo.getEstabNombre()).isEqualTo(UPDATED_ESTAB_NOMBRE);
        assertThat(testAlumnoTitulo.getEstabCue()).isEqualTo(UPDATED_ESTAB_CUE);
        assertThat(testAlumnoTitulo.getEstabUbicadoEn()).isEqualTo(UPDATED_ESTAB_UBICADO_EN);
        assertThat(testAlumnoTitulo.getEstabCiudad()).isEqualTo(UPDATED_ESTAB_CIUDAD);
        assertThat(testAlumnoTitulo.getEstabProvincia()).isEqualTo(UPDATED_ESTAB_PROVINCIA);
        assertThat(testAlumnoTitulo.getIdEstadoAlumnoTitulo()).isEqualTo(UPDATED_ID_ESTADO_ALUMNO_TITULO);
        assertThat(testAlumnoTitulo.getTituloIntermedio()).isEqualTo(UPDATED_TITULO_INTERMEDIO);
        assertThat(testAlumnoTitulo.getPromedio()).isEqualTo(UPDATED_PROMEDIO);
        assertThat(testAlumnoTitulo.getFechaRuta()).isEqualTo(UPDATED_FECHA_RUTA);
        assertThat(testAlumnoTitulo.getIdRamaRuta()).isEqualTo(UPDATED_ID_RAMA_RUTA);
        assertThat(testAlumnoTitulo.getApYnomListoParaEnviar()).isEqualTo(UPDATED_AP_YNOM_LISTO_PARA_ENVIAR);
        assertThat(testAlumnoTitulo.getDocumentoListoParaEnviar()).isEqualTo(UPDATED_DOCUMENTO_LISTO_PARA_ENVIAR);
        assertThat(testAlumnoTitulo.getApYnomEnviado()).isEqualTo(UPDATED_AP_YNOM_ENVIADO);
        assertThat(testAlumnoTitulo.getDocumentoEnviado()).isEqualTo(UPDATED_DOCUMENTO_ENVIADO);
        assertThat(testAlumnoTitulo.getFechaUltimoEstado()).isEqualTo(UPDATED_FECHA_ULTIMO_ESTADO);
    }

    @Test
    @Transactional
    void patchNonExistingAlumnoTitulo() throws Exception {
        int databaseSizeBeforeUpdate = alumnoTituloRepository.findAll().size();
        alumnoTitulo.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAlumnoTituloMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, alumnoTitulo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(alumnoTitulo))
            )
            .andExpect(status().isBadRequest());

        // Validate the AlumnoTitulo in the database
        List<AlumnoTitulo> alumnoTituloList = alumnoTituloRepository.findAll();
        assertThat(alumnoTituloList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAlumnoTitulo() throws Exception {
        int databaseSizeBeforeUpdate = alumnoTituloRepository.findAll().size();
        alumnoTitulo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAlumnoTituloMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(alumnoTitulo))
            )
            .andExpect(status().isBadRequest());

        // Validate the AlumnoTitulo in the database
        List<AlumnoTitulo> alumnoTituloList = alumnoTituloRepository.findAll();
        assertThat(alumnoTituloList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAlumnoTitulo() throws Exception {
        int databaseSizeBeforeUpdate = alumnoTituloRepository.findAll().size();
        alumnoTitulo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAlumnoTituloMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(alumnoTitulo))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AlumnoTitulo in the database
        List<AlumnoTitulo> alumnoTituloList = alumnoTituloRepository.findAll();
        assertThat(alumnoTituloList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAlumnoTitulo() throws Exception {
        // Initialize the database
        alumnoTituloRepository.saveAndFlush(alumnoTitulo);

        int databaseSizeBeforeDelete = alumnoTituloRepository.findAll().size();

        // Delete the alumnoTitulo
        restAlumnoTituloMockMvc
            .perform(delete(ENTITY_API_URL_ID, alumnoTitulo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AlumnoTitulo> alumnoTituloList = alumnoTituloRepository.findAll();
        assertThat(alumnoTituloList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
