package com.dgcye.web.rest;

import com.dgcye.domain.AlumnoEstabOferta;
import com.dgcye.repository.AlumnoEstabOfertaRepository;
import com.dgcye.service.AlumnoEstabOfertaService;
import com.dgcye.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.dgcye.domain.AlumnoEstabOferta}.
 */
@RestController
@RequestMapping("/api")
public class AlumnoEstabOfertaResource {

    private final Logger log = LoggerFactory.getLogger(AlumnoEstabOfertaResource.class);

    private static final String ENTITY_NAME = "alumnoEstabOferta";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AlumnoEstabOfertaService alumnoEstabOfertaService;

    private final AlumnoEstabOfertaRepository alumnoEstabOfertaRepository;

    public AlumnoEstabOfertaResource(
        AlumnoEstabOfertaService alumnoEstabOfertaService,
        AlumnoEstabOfertaRepository alumnoEstabOfertaRepository
    ) {
        this.alumnoEstabOfertaService = alumnoEstabOfertaService;
        this.alumnoEstabOfertaRepository = alumnoEstabOfertaRepository;
    }

    /**
     * {@code POST  /alumno-estab-ofertas} : Create a new alumnoEstabOferta.
     *
     * @param alumnoEstabOferta the alumnoEstabOferta to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new alumnoEstabOferta, or with status {@code 400 (Bad Request)} if the alumnoEstabOferta has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/alumno-estab-ofertas")
    public ResponseEntity<AlumnoEstabOferta> createAlumnoEstabOferta(@RequestBody AlumnoEstabOferta alumnoEstabOferta)
        throws URISyntaxException {
        log.debug("REST request to save AlumnoEstabOferta : {}", alumnoEstabOferta);
        if (alumnoEstabOferta.getId() != null) {
            throw new BadRequestAlertException("A new alumnoEstabOferta cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AlumnoEstabOferta result = alumnoEstabOfertaService.save(alumnoEstabOferta);
        return ResponseEntity
            .created(new URI("/api/alumno-estab-ofertas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /alumno-estab-ofertas/:id} : Updates an existing alumnoEstabOferta.
     *
     * @param id the id of the alumnoEstabOferta to save.
     * @param alumnoEstabOferta the alumnoEstabOferta to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated alumnoEstabOferta,
     * or with status {@code 400 (Bad Request)} if the alumnoEstabOferta is not valid,
     * or with status {@code 500 (Internal Server Error)} if the alumnoEstabOferta couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/alumno-estab-ofertas/{id}")
    public ResponseEntity<AlumnoEstabOferta> updateAlumnoEstabOferta(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AlumnoEstabOferta alumnoEstabOferta
    ) throws URISyntaxException {
        log.debug("REST request to update AlumnoEstabOferta : {}, {}", id, alumnoEstabOferta);
        if (alumnoEstabOferta.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, alumnoEstabOferta.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!alumnoEstabOfertaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AlumnoEstabOferta result = alumnoEstabOfertaService.update(alumnoEstabOferta);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, alumnoEstabOferta.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /alumno-estab-ofertas/:id} : Partial updates given fields of an existing alumnoEstabOferta, field will ignore if it is null
     *
     * @param id the id of the alumnoEstabOferta to save.
     * @param alumnoEstabOferta the alumnoEstabOferta to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated alumnoEstabOferta,
     * or with status {@code 400 (Bad Request)} if the alumnoEstabOferta is not valid,
     * or with status {@code 404 (Not Found)} if the alumnoEstabOferta is not found,
     * or with status {@code 500 (Internal Server Error)} if the alumnoEstabOferta couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/alumno-estab-ofertas/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AlumnoEstabOferta> partialUpdateAlumnoEstabOferta(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AlumnoEstabOferta alumnoEstabOferta
    ) throws URISyntaxException {
        log.debug("REST request to partial update AlumnoEstabOferta partially : {}, {}", id, alumnoEstabOferta);
        if (alumnoEstabOferta.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, alumnoEstabOferta.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!alumnoEstabOfertaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AlumnoEstabOferta> result = alumnoEstabOfertaService.partialUpdate(alumnoEstabOferta);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, alumnoEstabOferta.getId().toString())
        );
    }

    /**
     * {@code GET  /alumno-estab-ofertas} : get all the alumnoEstabOfertas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of alumnoEstabOfertas in body.
     */
    @GetMapping("/alumno-estab-ofertas")
    public List<AlumnoEstabOferta> getAllAlumnoEstabOfertas() {
        log.debug("REST request to get all AlumnoEstabOfertas");
        return alumnoEstabOfertaService.findAll();
    }

    /**
     * {@code GET  /alumno-estab-ofertas/:id} : get the "id" alumnoEstabOferta.
     *
     * @param id the id of the alumnoEstabOferta to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the alumnoEstabOferta, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/alumno-estab-ofertas/{id}")
    public ResponseEntity<AlumnoEstabOferta> getAlumnoEstabOferta(@PathVariable Long id) {
        log.debug("REST request to get AlumnoEstabOferta : {}", id);
        Optional<AlumnoEstabOferta> alumnoEstabOferta = alumnoEstabOfertaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(alumnoEstabOferta);
    }

    /**
     * {@code DELETE  /alumno-estab-ofertas/:id} : delete the "id" alumnoEstabOferta.
     *
     * @param id the id of the alumnoEstabOferta to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/alumno-estab-ofertas/{id}")
    public ResponseEntity<Void> deleteAlumnoEstabOferta(@PathVariable Long id) {
        log.debug("REST request to delete AlumnoEstabOferta : {}", id);
        alumnoEstabOfertaService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
