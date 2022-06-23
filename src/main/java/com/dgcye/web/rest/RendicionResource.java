package com.dgcye.web.rest;

import com.dgcye.domain.Rendicion;
import com.dgcye.repository.RendicionRepository;
import com.dgcye.service.RendicionService;
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
 * REST controller for managing {@link com.dgcye.domain.Rendicion}.
 */
@RestController
@RequestMapping("/api")
public class RendicionResource {

    private final Logger log = LoggerFactory.getLogger(RendicionResource.class);

    private static final String ENTITY_NAME = "rendicion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RendicionService rendicionService;

    private final RendicionRepository rendicionRepository;

    public RendicionResource(RendicionService rendicionService, RendicionRepository rendicionRepository) {
        this.rendicionService = rendicionService;
        this.rendicionRepository = rendicionRepository;
    }

    /**
     * {@code POST  /rendicions} : Create a new rendicion.
     *
     * @param rendicion the rendicion to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rendicion, or with status {@code 400 (Bad Request)} if the rendicion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rendicions")
    public ResponseEntity<Rendicion> createRendicion(@RequestBody Rendicion rendicion) throws URISyntaxException {
        log.debug("REST request to save Rendicion : {}", rendicion);
        if (rendicion.getId() != null) {
            throw new BadRequestAlertException("A new rendicion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Rendicion result = rendicionService.save(rendicion);
        return ResponseEntity
            .created(new URI("/api/rendicions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /rendicions/:id} : Updates an existing rendicion.
     *
     * @param id the id of the rendicion to save.
     * @param rendicion the rendicion to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rendicion,
     * or with status {@code 400 (Bad Request)} if the rendicion is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rendicion couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rendicions/{id}")
    public ResponseEntity<Rendicion> updateRendicion(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Rendicion rendicion
    ) throws URISyntaxException {
        log.debug("REST request to update Rendicion : {}, {}", id, rendicion);
        if (rendicion.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rendicion.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rendicionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Rendicion result = rendicionService.update(rendicion);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rendicion.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /rendicions/:id} : Partial updates given fields of an existing rendicion, field will ignore if it is null
     *
     * @param id the id of the rendicion to save.
     * @param rendicion the rendicion to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rendicion,
     * or with status {@code 400 (Bad Request)} if the rendicion is not valid,
     * or with status {@code 404 (Not Found)} if the rendicion is not found,
     * or with status {@code 500 (Internal Server Error)} if the rendicion couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/rendicions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Rendicion> partialUpdateRendicion(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Rendicion rendicion
    ) throws URISyntaxException {
        log.debug("REST request to partial update Rendicion partially : {}, {}", id, rendicion);
        if (rendicion.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rendicion.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rendicionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Rendicion> result = rendicionService.partialUpdate(rendicion);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rendicion.getId().toString())
        );
    }

    /**
     * {@code GET  /rendicions} : get all the rendicions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rendicions in body.
     */
    @GetMapping("/rendicions")
    public ResponseEntity<List<Rendicion>> getAllRendicions(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Rendicions");
        Page<Rendicion> page = rendicionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /rendicions/:id} : get the "id" rendicion.
     *
     * @param id the id of the rendicion to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rendicion, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rendicions/{id}")
    public ResponseEntity<Rendicion> getRendicion(@PathVariable Long id) {
        log.debug("REST request to get Rendicion : {}", id);
        Optional<Rendicion> rendicion = rendicionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rendicion);
    }

    /**
     * {@code DELETE  /rendicions/:id} : delete the "id" rendicion.
     *
     * @param id the id of the rendicion to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rendicions/{id}")
    public ResponseEntity<Void> deleteRendicion(@PathVariable Long id) {
        log.debug("REST request to delete Rendicion : {}", id);
        rendicionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
