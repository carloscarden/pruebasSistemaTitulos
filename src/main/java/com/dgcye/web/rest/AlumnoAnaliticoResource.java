package com.dgcye.web.rest;

import com.dgcye.domain.AlumnoAnalitico;
import com.dgcye.repository.AlumnoAnaliticoRepository;
import com.dgcye.service.AlumnoAnaliticoService;
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
 * REST controller for managing {@link com.dgcye.domain.AlumnoAnalitico}.
 */
@RestController
@RequestMapping("/api")
public class AlumnoAnaliticoResource {

    private final Logger log = LoggerFactory.getLogger(AlumnoAnaliticoResource.class);

    private static final String ENTITY_NAME = "alumnoAnalitico";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AlumnoAnaliticoService alumnoAnaliticoService;

    private final AlumnoAnaliticoRepository alumnoAnaliticoRepository;

    public AlumnoAnaliticoResource(AlumnoAnaliticoService alumnoAnaliticoService, AlumnoAnaliticoRepository alumnoAnaliticoRepository) {
        this.alumnoAnaliticoService = alumnoAnaliticoService;
        this.alumnoAnaliticoRepository = alumnoAnaliticoRepository;
    }

    /**
     * {@code POST  /alumno-analiticos} : Create a new alumnoAnalitico.
     *
     * @param alumnoAnalitico the alumnoAnalitico to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new alumnoAnalitico, or with status {@code 400 (Bad Request)} if the alumnoAnalitico has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/alumno-analiticos")
    public ResponseEntity<AlumnoAnalitico> createAlumnoAnalitico(@RequestBody AlumnoAnalitico alumnoAnalitico) throws URISyntaxException {
        log.debug("REST request to save AlumnoAnalitico : {}", alumnoAnalitico);
        if (alumnoAnalitico.getId() != null) {
            throw new BadRequestAlertException("A new alumnoAnalitico cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AlumnoAnalitico result = alumnoAnaliticoService.save(alumnoAnalitico);
        return ResponseEntity
            .created(new URI("/api/alumno-analiticos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /alumno-analiticos/:id} : Updates an existing alumnoAnalitico.
     *
     * @param id the id of the alumnoAnalitico to save.
     * @param alumnoAnalitico the alumnoAnalitico to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated alumnoAnalitico,
     * or with status {@code 400 (Bad Request)} if the alumnoAnalitico is not valid,
     * or with status {@code 500 (Internal Server Error)} if the alumnoAnalitico couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/alumno-analiticos/{id}")
    public ResponseEntity<AlumnoAnalitico> updateAlumnoAnalitico(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AlumnoAnalitico alumnoAnalitico
    ) throws URISyntaxException {
        log.debug("REST request to update AlumnoAnalitico : {}, {}", id, alumnoAnalitico);
        if (alumnoAnalitico.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, alumnoAnalitico.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!alumnoAnaliticoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AlumnoAnalitico result = alumnoAnaliticoService.update(alumnoAnalitico);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, alumnoAnalitico.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /alumno-analiticos/:id} : Partial updates given fields of an existing alumnoAnalitico, field will ignore if it is null
     *
     * @param id the id of the alumnoAnalitico to save.
     * @param alumnoAnalitico the alumnoAnalitico to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated alumnoAnalitico,
     * or with status {@code 400 (Bad Request)} if the alumnoAnalitico is not valid,
     * or with status {@code 404 (Not Found)} if the alumnoAnalitico is not found,
     * or with status {@code 500 (Internal Server Error)} if the alumnoAnalitico couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/alumno-analiticos/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AlumnoAnalitico> partialUpdateAlumnoAnalitico(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AlumnoAnalitico alumnoAnalitico
    ) throws URISyntaxException {
        log.debug("REST request to partial update AlumnoAnalitico partially : {}, {}", id, alumnoAnalitico);
        if (alumnoAnalitico.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, alumnoAnalitico.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!alumnoAnaliticoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AlumnoAnalitico> result = alumnoAnaliticoService.partialUpdate(alumnoAnalitico);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, alumnoAnalitico.getId().toString())
        );
    }

    /**
     * {@code GET  /alumno-analiticos} : get all the alumnoAnaliticos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of alumnoAnaliticos in body.
     */
    @GetMapping("/alumno-analiticos")
    public List<AlumnoAnalitico> getAllAlumnoAnaliticos() {
        log.debug("REST request to get all AlumnoAnaliticos");
        return alumnoAnaliticoService.findAll();
    }

    /**
     * {@code GET  /alumno-analiticos/:id} : get the "id" alumnoAnalitico.
     *
     * @param id the id of the alumnoAnalitico to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the alumnoAnalitico, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/alumno-analiticos/{id}")
    public ResponseEntity<AlumnoAnalitico> getAlumnoAnalitico(@PathVariable Long id) {
        log.debug("REST request to get AlumnoAnalitico : {}", id);
        Optional<AlumnoAnalitico> alumnoAnalitico = alumnoAnaliticoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(alumnoAnalitico);
    }

    /**
     * {@code DELETE  /alumno-analiticos/:id} : delete the "id" alumnoAnalitico.
     *
     * @param id the id of the alumnoAnalitico to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/alumno-analiticos/{id}")
    public ResponseEntity<Void> deleteAlumnoAnalitico(@PathVariable Long id) {
        log.debug("REST request to delete AlumnoAnalitico : {}", id);
        alumnoAnaliticoService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
