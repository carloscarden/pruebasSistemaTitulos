package com.dgcye.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dgcye.IntegrationTest;
import com.dgcye.domain.OfertaEducativa;
import com.dgcye.repository.OfertaEducativaRepository;
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
 * Integration tests for the {@link OfertaEducativaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OfertaEducativaResourceIT {

    private static final Long DEFAULT_ID_MODALIDAD = 1L;
    private static final Long UPDATED_ID_MODALIDAD = 2L;

    private static final Long DEFAULT_ID_NIVEL = 1L;
    private static final Long UPDATED_ID_NIVEL = 2L;

    private static final Long DEFAULT_ID_ORIENTACION = 1L;
    private static final Long UPDATED_ID_ORIENTACION = 2L;

    private static final Long DEFAULT_ID_TITULO_DENOMINACION = 1L;
    private static final Long UPDATED_ID_TITULO_DENOMINACION = 2L;

    private static final String DEFAULT_OBSERVIACIONES = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVIACIONES = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_TIPO_TITULO = 1L;
    private static final Long UPDATED_ID_TIPO_TITULO = 2L;

    private static final Long DEFAULT_ID_USER = 1L;
    private static final Long UPDATED_ID_USER = 2L;

    private static final Instant DEFAULT_FECHA_INICIO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FECHA_INICIO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_FECHA_FIN = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FECHA_FIN = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_FECHA_ESTADO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FECHA_ESTADO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_ID_ESTADO_OFERTA = 1L;
    private static final Long UPDATED_ID_ESTADO_OFERTA = 2L;

    private static final Long DEFAULT_ID_LEY_EDUCACION = 1L;
    private static final Long UPDATED_ID_LEY_EDUCACION = 2L;

    private static final Long DEFAULT_ID_NORMA_APROBACION_DEN = 1L;
    private static final Long UPDATED_ID_NORMA_APROBACION_DEN = 2L;

    private static final Long DEFAULT_ID_NORMA_DICTAMEN_DEN = 1L;
    private static final Long UPDATED_ID_NORMA_DICTAMEN_DEN = 2L;

    private static final Long DEFAULT_ID_SE_CORRESPONDE_CON = 1L;
    private static final Long UPDATED_ID_SE_CORRESPONDE_CON = 2L;

    private static final String DEFAULT_TITULO = "AAAAAAAAAA";
    private static final String UPDATED_TITULO = "BBBBBBBBBB";

    private static final String DEFAULT_TITULO_IMPRESION = "AAAAAAAAAA";
    private static final String UPDATED_TITULO_IMPRESION = "BBBBBBBBBB";

    private static final String DEFAULT_TITULO_INTERMEDIO = "AAAAAAAAAA";
    private static final String UPDATED_TITULO_INTERMEDIO = "BBBBBBBBBB";

    private static final String DEFAULT_TITULO_INTERMEDIO_IMPRESION = "AAAAAAAAAA";
    private static final String UPDATED_TITULO_INTERMEDIO_IMPRESION = "BBBBBBBBBB";

    private static final String DEFAULT_ORIENTACION = "AAAAAAAAAA";
    private static final String UPDATED_ORIENTACION = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_RAMA = 1L;
    private static final Long UPDATED_ID_RAMA = 2L;

    private static final Long DEFAULT_ID_SECCION_TITULO_INTERMEDIO = 1L;
    private static final Long UPDATED_ID_SECCION_TITULO_INTERMEDIO = 2L;

    private static final Long DEFAULT_ID_CURSO_GRUPO_NOMBRE = 1L;
    private static final Long UPDATED_ID_CURSO_GRUPO_NOMBRE = 2L;

    private static final Integer DEFAULT_CORRELATIVIDAD = 1;
    private static final Integer UPDATED_CORRELATIVIDAD = 2;

    private static final Long DEFAULT_ID_MODALIDAD_DICTADO = 1L;
    private static final Long UPDATED_ID_MODALIDAD_DICTADO = 2L;

    private static final Integer DEFAULT_TITULA = 1;
    private static final Integer UPDATED_TITULA = 2;

    private static final Integer DEFAULT_CICLO_BASICO = 1;
    private static final Integer UPDATED_CICLO_BASICO = 2;

    private static final String ENTITY_API_URL = "/api/oferta-educativas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private OfertaEducativaRepository ofertaEducativaRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOfertaEducativaMockMvc;

    private OfertaEducativa ofertaEducativa;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OfertaEducativa createEntity(EntityManager em) {
        OfertaEducativa ofertaEducativa = new OfertaEducativa()
            .idModalidad(DEFAULT_ID_MODALIDAD)
            .idNivel(DEFAULT_ID_NIVEL)
            .idOrientacion(DEFAULT_ID_ORIENTACION)
            .idTituloDenominacion(DEFAULT_ID_TITULO_DENOMINACION)
            .observiaciones(DEFAULT_OBSERVIACIONES)
            .idTipoTitulo(DEFAULT_ID_TIPO_TITULO)
            .idUser(DEFAULT_ID_USER)
            .fechaInicio(DEFAULT_FECHA_INICIO)
            .fechaFin(DEFAULT_FECHA_FIN)
            .fechaEstado(DEFAULT_FECHA_ESTADO)
            .idEstadoOferta(DEFAULT_ID_ESTADO_OFERTA)
            .idLeyEducacion(DEFAULT_ID_LEY_EDUCACION)
            .idNormaAprobacionDen(DEFAULT_ID_NORMA_APROBACION_DEN)
            .idNormaDictamenDen(DEFAULT_ID_NORMA_DICTAMEN_DEN)
            .idSeCorrespondeCon(DEFAULT_ID_SE_CORRESPONDE_CON)
            .titulo(DEFAULT_TITULO)
            .tituloImpresion(DEFAULT_TITULO_IMPRESION)
            .tituloIntermedio(DEFAULT_TITULO_INTERMEDIO)
            .tituloIntermedioImpresion(DEFAULT_TITULO_INTERMEDIO_IMPRESION)
            .orientacion(DEFAULT_ORIENTACION)
            .idRama(DEFAULT_ID_RAMA)
            .idSeccionTituloIntermedio(DEFAULT_ID_SECCION_TITULO_INTERMEDIO)
            .idCursoGrupoNombre(DEFAULT_ID_CURSO_GRUPO_NOMBRE)
            .correlatividad(DEFAULT_CORRELATIVIDAD)
            .idModalidadDictado(DEFAULT_ID_MODALIDAD_DICTADO)
            .titula(DEFAULT_TITULA)
            .cicloBasico(DEFAULT_CICLO_BASICO);
        return ofertaEducativa;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OfertaEducativa createUpdatedEntity(EntityManager em) {
        OfertaEducativa ofertaEducativa = new OfertaEducativa()
            .idModalidad(UPDATED_ID_MODALIDAD)
            .idNivel(UPDATED_ID_NIVEL)
            .idOrientacion(UPDATED_ID_ORIENTACION)
            .idTituloDenominacion(UPDATED_ID_TITULO_DENOMINACION)
            .observiaciones(UPDATED_OBSERVIACIONES)
            .idTipoTitulo(UPDATED_ID_TIPO_TITULO)
            .idUser(UPDATED_ID_USER)
            .fechaInicio(UPDATED_FECHA_INICIO)
            .fechaFin(UPDATED_FECHA_FIN)
            .fechaEstado(UPDATED_FECHA_ESTADO)
            .idEstadoOferta(UPDATED_ID_ESTADO_OFERTA)
            .idLeyEducacion(UPDATED_ID_LEY_EDUCACION)
            .idNormaAprobacionDen(UPDATED_ID_NORMA_APROBACION_DEN)
            .idNormaDictamenDen(UPDATED_ID_NORMA_DICTAMEN_DEN)
            .idSeCorrespondeCon(UPDATED_ID_SE_CORRESPONDE_CON)
            .titulo(UPDATED_TITULO)
            .tituloImpresion(UPDATED_TITULO_IMPRESION)
            .tituloIntermedio(UPDATED_TITULO_INTERMEDIO)
            .tituloIntermedioImpresion(UPDATED_TITULO_INTERMEDIO_IMPRESION)
            .orientacion(UPDATED_ORIENTACION)
            .idRama(UPDATED_ID_RAMA)
            .idSeccionTituloIntermedio(UPDATED_ID_SECCION_TITULO_INTERMEDIO)
            .idCursoGrupoNombre(UPDATED_ID_CURSO_GRUPO_NOMBRE)
            .correlatividad(UPDATED_CORRELATIVIDAD)
            .idModalidadDictado(UPDATED_ID_MODALIDAD_DICTADO)
            .titula(UPDATED_TITULA)
            .cicloBasico(UPDATED_CICLO_BASICO);
        return ofertaEducativa;
    }

    @BeforeEach
    public void initTest() {
        ofertaEducativa = createEntity(em);
    }

    @Test
    @Transactional
    void createOfertaEducativa() throws Exception {
        int databaseSizeBeforeCreate = ofertaEducativaRepository.findAll().size();
        // Create the OfertaEducativa
        restOfertaEducativaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ofertaEducativa))
            )
            .andExpect(status().isCreated());

        // Validate the OfertaEducativa in the database
        List<OfertaEducativa> ofertaEducativaList = ofertaEducativaRepository.findAll();
        assertThat(ofertaEducativaList).hasSize(databaseSizeBeforeCreate + 1);
        OfertaEducativa testOfertaEducativa = ofertaEducativaList.get(ofertaEducativaList.size() - 1);
        assertThat(testOfertaEducativa.getIdModalidad()).isEqualTo(DEFAULT_ID_MODALIDAD);
        assertThat(testOfertaEducativa.getIdNivel()).isEqualTo(DEFAULT_ID_NIVEL);
        assertThat(testOfertaEducativa.getIdOrientacion()).isEqualTo(DEFAULT_ID_ORIENTACION);
        assertThat(testOfertaEducativa.getIdTituloDenominacion()).isEqualTo(DEFAULT_ID_TITULO_DENOMINACION);
        assertThat(testOfertaEducativa.getObserviaciones()).isEqualTo(DEFAULT_OBSERVIACIONES);
        assertThat(testOfertaEducativa.getIdTipoTitulo()).isEqualTo(DEFAULT_ID_TIPO_TITULO);
        assertThat(testOfertaEducativa.getIdUser()).isEqualTo(DEFAULT_ID_USER);
        assertThat(testOfertaEducativa.getFechaInicio()).isEqualTo(DEFAULT_FECHA_INICIO);
        assertThat(testOfertaEducativa.getFechaFin()).isEqualTo(DEFAULT_FECHA_FIN);
        assertThat(testOfertaEducativa.getFechaEstado()).isEqualTo(DEFAULT_FECHA_ESTADO);
        assertThat(testOfertaEducativa.getIdEstadoOferta()).isEqualTo(DEFAULT_ID_ESTADO_OFERTA);
        assertThat(testOfertaEducativa.getIdLeyEducacion()).isEqualTo(DEFAULT_ID_LEY_EDUCACION);
        assertThat(testOfertaEducativa.getIdNormaAprobacionDen()).isEqualTo(DEFAULT_ID_NORMA_APROBACION_DEN);
        assertThat(testOfertaEducativa.getIdNormaDictamenDen()).isEqualTo(DEFAULT_ID_NORMA_DICTAMEN_DEN);
        assertThat(testOfertaEducativa.getIdSeCorrespondeCon()).isEqualTo(DEFAULT_ID_SE_CORRESPONDE_CON);
        assertThat(testOfertaEducativa.getTitulo()).isEqualTo(DEFAULT_TITULO);
        assertThat(testOfertaEducativa.getTituloImpresion()).isEqualTo(DEFAULT_TITULO_IMPRESION);
        assertThat(testOfertaEducativa.getTituloIntermedio()).isEqualTo(DEFAULT_TITULO_INTERMEDIO);
        assertThat(testOfertaEducativa.getTituloIntermedioImpresion()).isEqualTo(DEFAULT_TITULO_INTERMEDIO_IMPRESION);
        assertThat(testOfertaEducativa.getOrientacion()).isEqualTo(DEFAULT_ORIENTACION);
        assertThat(testOfertaEducativa.getIdRama()).isEqualTo(DEFAULT_ID_RAMA);
        assertThat(testOfertaEducativa.getIdSeccionTituloIntermedio()).isEqualTo(DEFAULT_ID_SECCION_TITULO_INTERMEDIO);
        assertThat(testOfertaEducativa.getIdCursoGrupoNombre()).isEqualTo(DEFAULT_ID_CURSO_GRUPO_NOMBRE);
        assertThat(testOfertaEducativa.getCorrelatividad()).isEqualTo(DEFAULT_CORRELATIVIDAD);
        assertThat(testOfertaEducativa.getIdModalidadDictado()).isEqualTo(DEFAULT_ID_MODALIDAD_DICTADO);
        assertThat(testOfertaEducativa.getTitula()).isEqualTo(DEFAULT_TITULA);
        assertThat(testOfertaEducativa.getCicloBasico()).isEqualTo(DEFAULT_CICLO_BASICO);
    }

    @Test
    @Transactional
    void createOfertaEducativaWithExistingId() throws Exception {
        // Create the OfertaEducativa with an existing ID
        ofertaEducativa.setId(1L);

        int databaseSizeBeforeCreate = ofertaEducativaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOfertaEducativaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ofertaEducativa))
            )
            .andExpect(status().isBadRequest());

        // Validate the OfertaEducativa in the database
        List<OfertaEducativa> ofertaEducativaList = ofertaEducativaRepository.findAll();
        assertThat(ofertaEducativaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllOfertaEducativas() throws Exception {
        // Initialize the database
        ofertaEducativaRepository.saveAndFlush(ofertaEducativa);

        // Get all the ofertaEducativaList
        restOfertaEducativaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ofertaEducativa.getId().intValue())))
            .andExpect(jsonPath("$.[*].idModalidad").value(hasItem(DEFAULT_ID_MODALIDAD.intValue())))
            .andExpect(jsonPath("$.[*].idNivel").value(hasItem(DEFAULT_ID_NIVEL.intValue())))
            .andExpect(jsonPath("$.[*].idOrientacion").value(hasItem(DEFAULT_ID_ORIENTACION.intValue())))
            .andExpect(jsonPath("$.[*].idTituloDenominacion").value(hasItem(DEFAULT_ID_TITULO_DENOMINACION.intValue())))
            .andExpect(jsonPath("$.[*].observiaciones").value(hasItem(DEFAULT_OBSERVIACIONES)))
            .andExpect(jsonPath("$.[*].idTipoTitulo").value(hasItem(DEFAULT_ID_TIPO_TITULO.intValue())))
            .andExpect(jsonPath("$.[*].idUser").value(hasItem(DEFAULT_ID_USER.intValue())))
            .andExpect(jsonPath("$.[*].fechaInicio").value(hasItem(DEFAULT_FECHA_INICIO.toString())))
            .andExpect(jsonPath("$.[*].fechaFin").value(hasItem(DEFAULT_FECHA_FIN.toString())))
            .andExpect(jsonPath("$.[*].fechaEstado").value(hasItem(DEFAULT_FECHA_ESTADO.toString())))
            .andExpect(jsonPath("$.[*].idEstadoOferta").value(hasItem(DEFAULT_ID_ESTADO_OFERTA.intValue())))
            .andExpect(jsonPath("$.[*].idLeyEducacion").value(hasItem(DEFAULT_ID_LEY_EDUCACION.intValue())))
            .andExpect(jsonPath("$.[*].idNormaAprobacionDen").value(hasItem(DEFAULT_ID_NORMA_APROBACION_DEN.intValue())))
            .andExpect(jsonPath("$.[*].idNormaDictamenDen").value(hasItem(DEFAULT_ID_NORMA_DICTAMEN_DEN.intValue())))
            .andExpect(jsonPath("$.[*].idSeCorrespondeCon").value(hasItem(DEFAULT_ID_SE_CORRESPONDE_CON.intValue())))
            .andExpect(jsonPath("$.[*].titulo").value(hasItem(DEFAULT_TITULO)))
            .andExpect(jsonPath("$.[*].tituloImpresion").value(hasItem(DEFAULT_TITULO_IMPRESION)))
            .andExpect(jsonPath("$.[*].tituloIntermedio").value(hasItem(DEFAULT_TITULO_INTERMEDIO)))
            .andExpect(jsonPath("$.[*].tituloIntermedioImpresion").value(hasItem(DEFAULT_TITULO_INTERMEDIO_IMPRESION)))
            .andExpect(jsonPath("$.[*].orientacion").value(hasItem(DEFAULT_ORIENTACION)))
            .andExpect(jsonPath("$.[*].idRama").value(hasItem(DEFAULT_ID_RAMA.intValue())))
            .andExpect(jsonPath("$.[*].idSeccionTituloIntermedio").value(hasItem(DEFAULT_ID_SECCION_TITULO_INTERMEDIO.intValue())))
            .andExpect(jsonPath("$.[*].idCursoGrupoNombre").value(hasItem(DEFAULT_ID_CURSO_GRUPO_NOMBRE.intValue())))
            .andExpect(jsonPath("$.[*].correlatividad").value(hasItem(DEFAULT_CORRELATIVIDAD)))
            .andExpect(jsonPath("$.[*].idModalidadDictado").value(hasItem(DEFAULT_ID_MODALIDAD_DICTADO.intValue())))
            .andExpect(jsonPath("$.[*].titula").value(hasItem(DEFAULT_TITULA)))
            .andExpect(jsonPath("$.[*].cicloBasico").value(hasItem(DEFAULT_CICLO_BASICO)));
    }

    @Test
    @Transactional
    void getOfertaEducativa() throws Exception {
        // Initialize the database
        ofertaEducativaRepository.saveAndFlush(ofertaEducativa);

        // Get the ofertaEducativa
        restOfertaEducativaMockMvc
            .perform(get(ENTITY_API_URL_ID, ofertaEducativa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ofertaEducativa.getId().intValue()))
            .andExpect(jsonPath("$.idModalidad").value(DEFAULT_ID_MODALIDAD.intValue()))
            .andExpect(jsonPath("$.idNivel").value(DEFAULT_ID_NIVEL.intValue()))
            .andExpect(jsonPath("$.idOrientacion").value(DEFAULT_ID_ORIENTACION.intValue()))
            .andExpect(jsonPath("$.idTituloDenominacion").value(DEFAULT_ID_TITULO_DENOMINACION.intValue()))
            .andExpect(jsonPath("$.observiaciones").value(DEFAULT_OBSERVIACIONES))
            .andExpect(jsonPath("$.idTipoTitulo").value(DEFAULT_ID_TIPO_TITULO.intValue()))
            .andExpect(jsonPath("$.idUser").value(DEFAULT_ID_USER.intValue()))
            .andExpect(jsonPath("$.fechaInicio").value(DEFAULT_FECHA_INICIO.toString()))
            .andExpect(jsonPath("$.fechaFin").value(DEFAULT_FECHA_FIN.toString()))
            .andExpect(jsonPath("$.fechaEstado").value(DEFAULT_FECHA_ESTADO.toString()))
            .andExpect(jsonPath("$.idEstadoOferta").value(DEFAULT_ID_ESTADO_OFERTA.intValue()))
            .andExpect(jsonPath("$.idLeyEducacion").value(DEFAULT_ID_LEY_EDUCACION.intValue()))
            .andExpect(jsonPath("$.idNormaAprobacionDen").value(DEFAULT_ID_NORMA_APROBACION_DEN.intValue()))
            .andExpect(jsonPath("$.idNormaDictamenDen").value(DEFAULT_ID_NORMA_DICTAMEN_DEN.intValue()))
            .andExpect(jsonPath("$.idSeCorrespondeCon").value(DEFAULT_ID_SE_CORRESPONDE_CON.intValue()))
            .andExpect(jsonPath("$.titulo").value(DEFAULT_TITULO))
            .andExpect(jsonPath("$.tituloImpresion").value(DEFAULT_TITULO_IMPRESION))
            .andExpect(jsonPath("$.tituloIntermedio").value(DEFAULT_TITULO_INTERMEDIO))
            .andExpect(jsonPath("$.tituloIntermedioImpresion").value(DEFAULT_TITULO_INTERMEDIO_IMPRESION))
            .andExpect(jsonPath("$.orientacion").value(DEFAULT_ORIENTACION))
            .andExpect(jsonPath("$.idRama").value(DEFAULT_ID_RAMA.intValue()))
            .andExpect(jsonPath("$.idSeccionTituloIntermedio").value(DEFAULT_ID_SECCION_TITULO_INTERMEDIO.intValue()))
            .andExpect(jsonPath("$.idCursoGrupoNombre").value(DEFAULT_ID_CURSO_GRUPO_NOMBRE.intValue()))
            .andExpect(jsonPath("$.correlatividad").value(DEFAULT_CORRELATIVIDAD))
            .andExpect(jsonPath("$.idModalidadDictado").value(DEFAULT_ID_MODALIDAD_DICTADO.intValue()))
            .andExpect(jsonPath("$.titula").value(DEFAULT_TITULA))
            .andExpect(jsonPath("$.cicloBasico").value(DEFAULT_CICLO_BASICO));
    }

    @Test
    @Transactional
    void getNonExistingOfertaEducativa() throws Exception {
        // Get the ofertaEducativa
        restOfertaEducativaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewOfertaEducativa() throws Exception {
        // Initialize the database
        ofertaEducativaRepository.saveAndFlush(ofertaEducativa);

        int databaseSizeBeforeUpdate = ofertaEducativaRepository.findAll().size();

        // Update the ofertaEducativa
        OfertaEducativa updatedOfertaEducativa = ofertaEducativaRepository.findById(ofertaEducativa.getId()).get();
        // Disconnect from session so that the updates on updatedOfertaEducativa are not directly saved in db
        em.detach(updatedOfertaEducativa);
        updatedOfertaEducativa
            .idModalidad(UPDATED_ID_MODALIDAD)
            .idNivel(UPDATED_ID_NIVEL)
            .idOrientacion(UPDATED_ID_ORIENTACION)
            .idTituloDenominacion(UPDATED_ID_TITULO_DENOMINACION)
            .observiaciones(UPDATED_OBSERVIACIONES)
            .idTipoTitulo(UPDATED_ID_TIPO_TITULO)
            .idUser(UPDATED_ID_USER)
            .fechaInicio(UPDATED_FECHA_INICIO)
            .fechaFin(UPDATED_FECHA_FIN)
            .fechaEstado(UPDATED_FECHA_ESTADO)
            .idEstadoOferta(UPDATED_ID_ESTADO_OFERTA)
            .idLeyEducacion(UPDATED_ID_LEY_EDUCACION)
            .idNormaAprobacionDen(UPDATED_ID_NORMA_APROBACION_DEN)
            .idNormaDictamenDen(UPDATED_ID_NORMA_DICTAMEN_DEN)
            .idSeCorrespondeCon(UPDATED_ID_SE_CORRESPONDE_CON)
            .titulo(UPDATED_TITULO)
            .tituloImpresion(UPDATED_TITULO_IMPRESION)
            .tituloIntermedio(UPDATED_TITULO_INTERMEDIO)
            .tituloIntermedioImpresion(UPDATED_TITULO_INTERMEDIO_IMPRESION)
            .orientacion(UPDATED_ORIENTACION)
            .idRama(UPDATED_ID_RAMA)
            .idSeccionTituloIntermedio(UPDATED_ID_SECCION_TITULO_INTERMEDIO)
            .idCursoGrupoNombre(UPDATED_ID_CURSO_GRUPO_NOMBRE)
            .correlatividad(UPDATED_CORRELATIVIDAD)
            .idModalidadDictado(UPDATED_ID_MODALIDAD_DICTADO)
            .titula(UPDATED_TITULA)
            .cicloBasico(UPDATED_CICLO_BASICO);

        restOfertaEducativaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOfertaEducativa.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedOfertaEducativa))
            )
            .andExpect(status().isOk());

        // Validate the OfertaEducativa in the database
        List<OfertaEducativa> ofertaEducativaList = ofertaEducativaRepository.findAll();
        assertThat(ofertaEducativaList).hasSize(databaseSizeBeforeUpdate);
        OfertaEducativa testOfertaEducativa = ofertaEducativaList.get(ofertaEducativaList.size() - 1);
        assertThat(testOfertaEducativa.getIdModalidad()).isEqualTo(UPDATED_ID_MODALIDAD);
        assertThat(testOfertaEducativa.getIdNivel()).isEqualTo(UPDATED_ID_NIVEL);
        assertThat(testOfertaEducativa.getIdOrientacion()).isEqualTo(UPDATED_ID_ORIENTACION);
        assertThat(testOfertaEducativa.getIdTituloDenominacion()).isEqualTo(UPDATED_ID_TITULO_DENOMINACION);
        assertThat(testOfertaEducativa.getObserviaciones()).isEqualTo(UPDATED_OBSERVIACIONES);
        assertThat(testOfertaEducativa.getIdTipoTitulo()).isEqualTo(UPDATED_ID_TIPO_TITULO);
        assertThat(testOfertaEducativa.getIdUser()).isEqualTo(UPDATED_ID_USER);
        assertThat(testOfertaEducativa.getFechaInicio()).isEqualTo(UPDATED_FECHA_INICIO);
        assertThat(testOfertaEducativa.getFechaFin()).isEqualTo(UPDATED_FECHA_FIN);
        assertThat(testOfertaEducativa.getFechaEstado()).isEqualTo(UPDATED_FECHA_ESTADO);
        assertThat(testOfertaEducativa.getIdEstadoOferta()).isEqualTo(UPDATED_ID_ESTADO_OFERTA);
        assertThat(testOfertaEducativa.getIdLeyEducacion()).isEqualTo(UPDATED_ID_LEY_EDUCACION);
        assertThat(testOfertaEducativa.getIdNormaAprobacionDen()).isEqualTo(UPDATED_ID_NORMA_APROBACION_DEN);
        assertThat(testOfertaEducativa.getIdNormaDictamenDen()).isEqualTo(UPDATED_ID_NORMA_DICTAMEN_DEN);
        assertThat(testOfertaEducativa.getIdSeCorrespondeCon()).isEqualTo(UPDATED_ID_SE_CORRESPONDE_CON);
        assertThat(testOfertaEducativa.getTitulo()).isEqualTo(UPDATED_TITULO);
        assertThat(testOfertaEducativa.getTituloImpresion()).isEqualTo(UPDATED_TITULO_IMPRESION);
        assertThat(testOfertaEducativa.getTituloIntermedio()).isEqualTo(UPDATED_TITULO_INTERMEDIO);
        assertThat(testOfertaEducativa.getTituloIntermedioImpresion()).isEqualTo(UPDATED_TITULO_INTERMEDIO_IMPRESION);
        assertThat(testOfertaEducativa.getOrientacion()).isEqualTo(UPDATED_ORIENTACION);
        assertThat(testOfertaEducativa.getIdRama()).isEqualTo(UPDATED_ID_RAMA);
        assertThat(testOfertaEducativa.getIdSeccionTituloIntermedio()).isEqualTo(UPDATED_ID_SECCION_TITULO_INTERMEDIO);
        assertThat(testOfertaEducativa.getIdCursoGrupoNombre()).isEqualTo(UPDATED_ID_CURSO_GRUPO_NOMBRE);
        assertThat(testOfertaEducativa.getCorrelatividad()).isEqualTo(UPDATED_CORRELATIVIDAD);
        assertThat(testOfertaEducativa.getIdModalidadDictado()).isEqualTo(UPDATED_ID_MODALIDAD_DICTADO);
        assertThat(testOfertaEducativa.getTitula()).isEqualTo(UPDATED_TITULA);
        assertThat(testOfertaEducativa.getCicloBasico()).isEqualTo(UPDATED_CICLO_BASICO);
    }

    @Test
    @Transactional
    void putNonExistingOfertaEducativa() throws Exception {
        int databaseSizeBeforeUpdate = ofertaEducativaRepository.findAll().size();
        ofertaEducativa.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOfertaEducativaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ofertaEducativa.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ofertaEducativa))
            )
            .andExpect(status().isBadRequest());

        // Validate the OfertaEducativa in the database
        List<OfertaEducativa> ofertaEducativaList = ofertaEducativaRepository.findAll();
        assertThat(ofertaEducativaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOfertaEducativa() throws Exception {
        int databaseSizeBeforeUpdate = ofertaEducativaRepository.findAll().size();
        ofertaEducativa.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOfertaEducativaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ofertaEducativa))
            )
            .andExpect(status().isBadRequest());

        // Validate the OfertaEducativa in the database
        List<OfertaEducativa> ofertaEducativaList = ofertaEducativaRepository.findAll();
        assertThat(ofertaEducativaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOfertaEducativa() throws Exception {
        int databaseSizeBeforeUpdate = ofertaEducativaRepository.findAll().size();
        ofertaEducativa.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOfertaEducativaMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ofertaEducativa))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OfertaEducativa in the database
        List<OfertaEducativa> ofertaEducativaList = ofertaEducativaRepository.findAll();
        assertThat(ofertaEducativaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOfertaEducativaWithPatch() throws Exception {
        // Initialize the database
        ofertaEducativaRepository.saveAndFlush(ofertaEducativa);

        int databaseSizeBeforeUpdate = ofertaEducativaRepository.findAll().size();

        // Update the ofertaEducativa using partial update
        OfertaEducativa partialUpdatedOfertaEducativa = new OfertaEducativa();
        partialUpdatedOfertaEducativa.setId(ofertaEducativa.getId());

        partialUpdatedOfertaEducativa
            .idNivel(UPDATED_ID_NIVEL)
            .idTituloDenominacion(UPDATED_ID_TITULO_DENOMINACION)
            .observiaciones(UPDATED_OBSERVIACIONES)
            .idTipoTitulo(UPDATED_ID_TIPO_TITULO)
            .fechaInicio(UPDATED_FECHA_INICIO)
            .fechaEstado(UPDATED_FECHA_ESTADO)
            .idEstadoOferta(UPDATED_ID_ESTADO_OFERTA)
            .idLeyEducacion(UPDATED_ID_LEY_EDUCACION)
            .idNormaAprobacionDen(UPDATED_ID_NORMA_APROBACION_DEN)
            .tituloImpresion(UPDATED_TITULO_IMPRESION)
            .tituloIntermedio(UPDATED_TITULO_INTERMEDIO)
            .tituloIntermedioImpresion(UPDATED_TITULO_INTERMEDIO_IMPRESION)
            .idModalidadDictado(UPDATED_ID_MODALIDAD_DICTADO)
            .titula(UPDATED_TITULA);

        restOfertaEducativaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOfertaEducativa.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOfertaEducativa))
            )
            .andExpect(status().isOk());

        // Validate the OfertaEducativa in the database
        List<OfertaEducativa> ofertaEducativaList = ofertaEducativaRepository.findAll();
        assertThat(ofertaEducativaList).hasSize(databaseSizeBeforeUpdate);
        OfertaEducativa testOfertaEducativa = ofertaEducativaList.get(ofertaEducativaList.size() - 1);
        assertThat(testOfertaEducativa.getIdModalidad()).isEqualTo(DEFAULT_ID_MODALIDAD);
        assertThat(testOfertaEducativa.getIdNivel()).isEqualTo(UPDATED_ID_NIVEL);
        assertThat(testOfertaEducativa.getIdOrientacion()).isEqualTo(DEFAULT_ID_ORIENTACION);
        assertThat(testOfertaEducativa.getIdTituloDenominacion()).isEqualTo(UPDATED_ID_TITULO_DENOMINACION);
        assertThat(testOfertaEducativa.getObserviaciones()).isEqualTo(UPDATED_OBSERVIACIONES);
        assertThat(testOfertaEducativa.getIdTipoTitulo()).isEqualTo(UPDATED_ID_TIPO_TITULO);
        assertThat(testOfertaEducativa.getIdUser()).isEqualTo(DEFAULT_ID_USER);
        assertThat(testOfertaEducativa.getFechaInicio()).isEqualTo(UPDATED_FECHA_INICIO);
        assertThat(testOfertaEducativa.getFechaFin()).isEqualTo(DEFAULT_FECHA_FIN);
        assertThat(testOfertaEducativa.getFechaEstado()).isEqualTo(UPDATED_FECHA_ESTADO);
        assertThat(testOfertaEducativa.getIdEstadoOferta()).isEqualTo(UPDATED_ID_ESTADO_OFERTA);
        assertThat(testOfertaEducativa.getIdLeyEducacion()).isEqualTo(UPDATED_ID_LEY_EDUCACION);
        assertThat(testOfertaEducativa.getIdNormaAprobacionDen()).isEqualTo(UPDATED_ID_NORMA_APROBACION_DEN);
        assertThat(testOfertaEducativa.getIdNormaDictamenDen()).isEqualTo(DEFAULT_ID_NORMA_DICTAMEN_DEN);
        assertThat(testOfertaEducativa.getIdSeCorrespondeCon()).isEqualTo(DEFAULT_ID_SE_CORRESPONDE_CON);
        assertThat(testOfertaEducativa.getTitulo()).isEqualTo(DEFAULT_TITULO);
        assertThat(testOfertaEducativa.getTituloImpresion()).isEqualTo(UPDATED_TITULO_IMPRESION);
        assertThat(testOfertaEducativa.getTituloIntermedio()).isEqualTo(UPDATED_TITULO_INTERMEDIO);
        assertThat(testOfertaEducativa.getTituloIntermedioImpresion()).isEqualTo(UPDATED_TITULO_INTERMEDIO_IMPRESION);
        assertThat(testOfertaEducativa.getOrientacion()).isEqualTo(DEFAULT_ORIENTACION);
        assertThat(testOfertaEducativa.getIdRama()).isEqualTo(DEFAULT_ID_RAMA);
        assertThat(testOfertaEducativa.getIdSeccionTituloIntermedio()).isEqualTo(DEFAULT_ID_SECCION_TITULO_INTERMEDIO);
        assertThat(testOfertaEducativa.getIdCursoGrupoNombre()).isEqualTo(DEFAULT_ID_CURSO_GRUPO_NOMBRE);
        assertThat(testOfertaEducativa.getCorrelatividad()).isEqualTo(DEFAULT_CORRELATIVIDAD);
        assertThat(testOfertaEducativa.getIdModalidadDictado()).isEqualTo(UPDATED_ID_MODALIDAD_DICTADO);
        assertThat(testOfertaEducativa.getTitula()).isEqualTo(UPDATED_TITULA);
        assertThat(testOfertaEducativa.getCicloBasico()).isEqualTo(DEFAULT_CICLO_BASICO);
    }

    @Test
    @Transactional
    void fullUpdateOfertaEducativaWithPatch() throws Exception {
        // Initialize the database
        ofertaEducativaRepository.saveAndFlush(ofertaEducativa);

        int databaseSizeBeforeUpdate = ofertaEducativaRepository.findAll().size();

        // Update the ofertaEducativa using partial update
        OfertaEducativa partialUpdatedOfertaEducativa = new OfertaEducativa();
        partialUpdatedOfertaEducativa.setId(ofertaEducativa.getId());

        partialUpdatedOfertaEducativa
            .idModalidad(UPDATED_ID_MODALIDAD)
            .idNivel(UPDATED_ID_NIVEL)
            .idOrientacion(UPDATED_ID_ORIENTACION)
            .idTituloDenominacion(UPDATED_ID_TITULO_DENOMINACION)
            .observiaciones(UPDATED_OBSERVIACIONES)
            .idTipoTitulo(UPDATED_ID_TIPO_TITULO)
            .idUser(UPDATED_ID_USER)
            .fechaInicio(UPDATED_FECHA_INICIO)
            .fechaFin(UPDATED_FECHA_FIN)
            .fechaEstado(UPDATED_FECHA_ESTADO)
            .idEstadoOferta(UPDATED_ID_ESTADO_OFERTA)
            .idLeyEducacion(UPDATED_ID_LEY_EDUCACION)
            .idNormaAprobacionDen(UPDATED_ID_NORMA_APROBACION_DEN)
            .idNormaDictamenDen(UPDATED_ID_NORMA_DICTAMEN_DEN)
            .idSeCorrespondeCon(UPDATED_ID_SE_CORRESPONDE_CON)
            .titulo(UPDATED_TITULO)
            .tituloImpresion(UPDATED_TITULO_IMPRESION)
            .tituloIntermedio(UPDATED_TITULO_INTERMEDIO)
            .tituloIntermedioImpresion(UPDATED_TITULO_INTERMEDIO_IMPRESION)
            .orientacion(UPDATED_ORIENTACION)
            .idRama(UPDATED_ID_RAMA)
            .idSeccionTituloIntermedio(UPDATED_ID_SECCION_TITULO_INTERMEDIO)
            .idCursoGrupoNombre(UPDATED_ID_CURSO_GRUPO_NOMBRE)
            .correlatividad(UPDATED_CORRELATIVIDAD)
            .idModalidadDictado(UPDATED_ID_MODALIDAD_DICTADO)
            .titula(UPDATED_TITULA)
            .cicloBasico(UPDATED_CICLO_BASICO);

        restOfertaEducativaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOfertaEducativa.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOfertaEducativa))
            )
            .andExpect(status().isOk());

        // Validate the OfertaEducativa in the database
        List<OfertaEducativa> ofertaEducativaList = ofertaEducativaRepository.findAll();
        assertThat(ofertaEducativaList).hasSize(databaseSizeBeforeUpdate);
        OfertaEducativa testOfertaEducativa = ofertaEducativaList.get(ofertaEducativaList.size() - 1);
        assertThat(testOfertaEducativa.getIdModalidad()).isEqualTo(UPDATED_ID_MODALIDAD);
        assertThat(testOfertaEducativa.getIdNivel()).isEqualTo(UPDATED_ID_NIVEL);
        assertThat(testOfertaEducativa.getIdOrientacion()).isEqualTo(UPDATED_ID_ORIENTACION);
        assertThat(testOfertaEducativa.getIdTituloDenominacion()).isEqualTo(UPDATED_ID_TITULO_DENOMINACION);
        assertThat(testOfertaEducativa.getObserviaciones()).isEqualTo(UPDATED_OBSERVIACIONES);
        assertThat(testOfertaEducativa.getIdTipoTitulo()).isEqualTo(UPDATED_ID_TIPO_TITULO);
        assertThat(testOfertaEducativa.getIdUser()).isEqualTo(UPDATED_ID_USER);
        assertThat(testOfertaEducativa.getFechaInicio()).isEqualTo(UPDATED_FECHA_INICIO);
        assertThat(testOfertaEducativa.getFechaFin()).isEqualTo(UPDATED_FECHA_FIN);
        assertThat(testOfertaEducativa.getFechaEstado()).isEqualTo(UPDATED_FECHA_ESTADO);
        assertThat(testOfertaEducativa.getIdEstadoOferta()).isEqualTo(UPDATED_ID_ESTADO_OFERTA);
        assertThat(testOfertaEducativa.getIdLeyEducacion()).isEqualTo(UPDATED_ID_LEY_EDUCACION);
        assertThat(testOfertaEducativa.getIdNormaAprobacionDen()).isEqualTo(UPDATED_ID_NORMA_APROBACION_DEN);
        assertThat(testOfertaEducativa.getIdNormaDictamenDen()).isEqualTo(UPDATED_ID_NORMA_DICTAMEN_DEN);
        assertThat(testOfertaEducativa.getIdSeCorrespondeCon()).isEqualTo(UPDATED_ID_SE_CORRESPONDE_CON);
        assertThat(testOfertaEducativa.getTitulo()).isEqualTo(UPDATED_TITULO);
        assertThat(testOfertaEducativa.getTituloImpresion()).isEqualTo(UPDATED_TITULO_IMPRESION);
        assertThat(testOfertaEducativa.getTituloIntermedio()).isEqualTo(UPDATED_TITULO_INTERMEDIO);
        assertThat(testOfertaEducativa.getTituloIntermedioImpresion()).isEqualTo(UPDATED_TITULO_INTERMEDIO_IMPRESION);
        assertThat(testOfertaEducativa.getOrientacion()).isEqualTo(UPDATED_ORIENTACION);
        assertThat(testOfertaEducativa.getIdRama()).isEqualTo(UPDATED_ID_RAMA);
        assertThat(testOfertaEducativa.getIdSeccionTituloIntermedio()).isEqualTo(UPDATED_ID_SECCION_TITULO_INTERMEDIO);
        assertThat(testOfertaEducativa.getIdCursoGrupoNombre()).isEqualTo(UPDATED_ID_CURSO_GRUPO_NOMBRE);
        assertThat(testOfertaEducativa.getCorrelatividad()).isEqualTo(UPDATED_CORRELATIVIDAD);
        assertThat(testOfertaEducativa.getIdModalidadDictado()).isEqualTo(UPDATED_ID_MODALIDAD_DICTADO);
        assertThat(testOfertaEducativa.getTitula()).isEqualTo(UPDATED_TITULA);
        assertThat(testOfertaEducativa.getCicloBasico()).isEqualTo(UPDATED_CICLO_BASICO);
    }

    @Test
    @Transactional
    void patchNonExistingOfertaEducativa() throws Exception {
        int databaseSizeBeforeUpdate = ofertaEducativaRepository.findAll().size();
        ofertaEducativa.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOfertaEducativaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, ofertaEducativa.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ofertaEducativa))
            )
            .andExpect(status().isBadRequest());

        // Validate the OfertaEducativa in the database
        List<OfertaEducativa> ofertaEducativaList = ofertaEducativaRepository.findAll();
        assertThat(ofertaEducativaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOfertaEducativa() throws Exception {
        int databaseSizeBeforeUpdate = ofertaEducativaRepository.findAll().size();
        ofertaEducativa.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOfertaEducativaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ofertaEducativa))
            )
            .andExpect(status().isBadRequest());

        // Validate the OfertaEducativa in the database
        List<OfertaEducativa> ofertaEducativaList = ofertaEducativaRepository.findAll();
        assertThat(ofertaEducativaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOfertaEducativa() throws Exception {
        int databaseSizeBeforeUpdate = ofertaEducativaRepository.findAll().size();
        ofertaEducativa.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOfertaEducativaMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ofertaEducativa))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OfertaEducativa in the database
        List<OfertaEducativa> ofertaEducativaList = ofertaEducativaRepository.findAll();
        assertThat(ofertaEducativaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOfertaEducativa() throws Exception {
        // Initialize the database
        ofertaEducativaRepository.saveAndFlush(ofertaEducativa);

        int databaseSizeBeforeDelete = ofertaEducativaRepository.findAll().size();

        // Delete the ofertaEducativa
        restOfertaEducativaMockMvc
            .perform(delete(ENTITY_API_URL_ID, ofertaEducativa.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OfertaEducativa> ofertaEducativaList = ofertaEducativaRepository.findAll();
        assertThat(ofertaEducativaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
