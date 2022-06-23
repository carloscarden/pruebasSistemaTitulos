package com.dgcye.web.rest;

import com.dgcye.domain.AlumnoTitulo;
import com.dgcye.repository.AlumnoTituloRepository;
import com.dgcye.service.AlumnoTituloService;
import com.dgcye.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.dgcye.domain.AlumnoTitulo}.
 */
@RestController
@RequestMapping("/api")
public class AlumnoTituloResource {

    private final Logger log = LoggerFactory.getLogger(AlumnoTituloResource.class);

    private static final String ENTITY_NAME = "alumnoTitulo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AlumnoTituloService alumnoTituloService;

    private final AlumnoTituloRepository alumnoTituloRepository;

    public AlumnoTituloResource(AlumnoTituloService alumnoTituloService, AlumnoTituloRepository alumnoTituloRepository) {
        this.alumnoTituloService = alumnoTituloService;
        this.alumnoTituloRepository = alumnoTituloRepository;
    }

    /**
     * {@code POST  /alumno-titulos} : Create a new alumnoTitulo.
     *
     * @param alumnoTitulo the alumnoTitulo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new alumnoTitulo, or with status {@code 400 (Bad Request)} if the alumnoTitulo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/alumno-titulos")
    public ResponseEntity<AlumnoTitulo> createAlumnoTitulo(@RequestBody AlumnoTitulo alumnoTitulo) throws URISyntaxException {
        log.debug("REST request to save AlumnoTitulo : {}", alumnoTitulo);
        if (alumnoTitulo.getId() != null) {
            throw new BadRequestAlertException("A new alumnoTitulo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AlumnoTitulo result = alumnoTituloService.save(alumnoTitulo);
        return ResponseEntity
            .created(new URI("/api/alumno-titulos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /alumno-titulos/:id} : Updates an existing alumnoTitulo.
     *
     * @param id the id of the alumnoTitulo to save.
     * @param alumnoTitulo the alumnoTitulo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated alumnoTitulo,
     * or with status {@code 400 (Bad Request)} if the alumnoTitulo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the alumnoTitulo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/alumno-titulos/{id}")
    public ResponseEntity<AlumnoTitulo> updateAlumnoTitulo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AlumnoTitulo alumnoTitulo
    ) throws URISyntaxException {
        log.debug("REST request to update AlumnoTitulo : {}, {}", id, alumnoTitulo);
        if (alumnoTitulo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, alumnoTitulo.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!alumnoTituloRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AlumnoTitulo result = alumnoTituloService.update(alumnoTitulo);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, alumnoTitulo.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /alumno-titulos/:id} : Partial updates given fields of an existing alumnoTitulo, field will ignore if it is null
     *
     * @param id the id of the alumnoTitulo to save.
     * @param alumnoTitulo the alumnoTitulo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated alumnoTitulo,
     * or with status {@code 400 (Bad Request)} if the alumnoTitulo is not valid,
     * or with status {@code 404 (Not Found)} if the alumnoTitulo is not found,
     * or with status {@code 500 (Internal Server Error)} if the alumnoTitulo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/alumno-titulos/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AlumnoTitulo> partialUpdateAlumnoTitulo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AlumnoTitulo alumnoTitulo
    ) throws URISyntaxException {
        log.debug("REST request to partial update AlumnoTitulo partially : {}, {}", id, alumnoTitulo);
        if (alumnoTitulo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, alumnoTitulo.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!alumnoTituloRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AlumnoTitulo> result = alumnoTituloService.partialUpdate(alumnoTitulo);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, alumnoTitulo.getId().toString())
        );
    }

    /**
     * {@code GET  /alumno-titulos} : get all the alumnoTitulos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of alumnoTitulos in body.
     */
    @GetMapping("/alumno-titulos")
    public ResponseEntity<List<AlumnoTitulo>> getAllAlumnoTitulos(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of AlumnoTitulos");
        Page<AlumnoTitulo> page = alumnoTituloService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /alumno-titulos/:id} : get the "id" alumnoTitulo.
     *
     * @param id the id of the alumnoTitulo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the alumnoTitulo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/alumno-titulos/{id}")
    public ResponseEntity<AlumnoTitulo> getAlumnoTitulo(@PathVariable Long id) {
        log.debug("REST request to get AlumnoTitulo : {}", id);
        Optional<AlumnoTitulo> alumnoTitulo = alumnoTituloService.findOne(id);
        return ResponseUtil.wrapOrNotFound(alumnoTitulo);
    }

    /**
     * {@code DELETE  /alumno-titulos/:id} : delete the "id" alumnoTitulo.
     *
     * @param id the id of the alumnoTitulo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/alumno-titulos/{id}")
    public ResponseEntity<Void> deleteAlumnoTitulo(@PathVariable Long id) {
        log.debug("REST request to delete AlumnoTitulo : {}", id);
        alumnoTituloService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
