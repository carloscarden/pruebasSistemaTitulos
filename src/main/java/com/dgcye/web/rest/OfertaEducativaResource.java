package com.dgcye.web.rest;

import com.dgcye.domain.OfertaEducativa;
import com.dgcye.repository.OfertaEducativaRepository;
import com.dgcye.service.OfertaEducativaService;
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
 * REST controller for managing {@link com.dgcye.domain.OfertaEducativa}.
 */
@RestController
@RequestMapping("/api")
public class OfertaEducativaResource {

    private final Logger log = LoggerFactory.getLogger(OfertaEducativaResource.class);

    private static final String ENTITY_NAME = "ofertaEducativa";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OfertaEducativaService ofertaEducativaService;

    private final OfertaEducativaRepository ofertaEducativaRepository;

    public OfertaEducativaResource(OfertaEducativaService ofertaEducativaService, OfertaEducativaRepository ofertaEducativaRepository) {
        this.ofertaEducativaService = ofertaEducativaService;
        this.ofertaEducativaRepository = ofertaEducativaRepository;
    }

    /**
     * {@code POST  /oferta-educativas} : Create a new ofertaEducativa.
     *
     * @param ofertaEducativa the ofertaEducativa to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ofertaEducativa, or with status {@code 400 (Bad Request)} if the ofertaEducativa has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/oferta-educativas")
    public ResponseEntity<OfertaEducativa> createOfertaEducativa(@RequestBody OfertaEducativa ofertaEducativa) throws URISyntaxException {
        log.debug("REST request to save OfertaEducativa : {}", ofertaEducativa);
        if (ofertaEducativa.getId() != null) {
            throw new BadRequestAlertException("A new ofertaEducativa cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OfertaEducativa result = ofertaEducativaService.save(ofertaEducativa);
        return ResponseEntity
            .created(new URI("/api/oferta-educativas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /oferta-educativas/:id} : Updates an existing ofertaEducativa.
     *
     * @param id the id of the ofertaEducativa to save.
     * @param ofertaEducativa the ofertaEducativa to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ofertaEducativa,
     * or with status {@code 400 (Bad Request)} if the ofertaEducativa is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ofertaEducativa couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/oferta-educativas/{id}")
    public ResponseEntity<OfertaEducativa> updateOfertaEducativa(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OfertaEducativa ofertaEducativa
    ) throws URISyntaxException {
        log.debug("REST request to update OfertaEducativa : {}, {}", id, ofertaEducativa);
        if (ofertaEducativa.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ofertaEducativa.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ofertaEducativaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        OfertaEducativa result = ofertaEducativaService.update(ofertaEducativa);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ofertaEducativa.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /oferta-educativas/:id} : Partial updates given fields of an existing ofertaEducativa, field will ignore if it is null
     *
     * @param id the id of the ofertaEducativa to save.
     * @param ofertaEducativa the ofertaEducativa to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ofertaEducativa,
     * or with status {@code 400 (Bad Request)} if the ofertaEducativa is not valid,
     * or with status {@code 404 (Not Found)} if the ofertaEducativa is not found,
     * or with status {@code 500 (Internal Server Error)} if the ofertaEducativa couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/oferta-educativas/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OfertaEducativa> partialUpdateOfertaEducativa(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OfertaEducativa ofertaEducativa
    ) throws URISyntaxException {
        log.debug("REST request to partial update OfertaEducativa partially : {}, {}", id, ofertaEducativa);
        if (ofertaEducativa.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ofertaEducativa.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ofertaEducativaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OfertaEducativa> result = ofertaEducativaService.partialUpdate(ofertaEducativa);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ofertaEducativa.getId().toString())
        );
    }

    /**
     * {@code GET  /oferta-educativas} : get all the ofertaEducativas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ofertaEducativas in body.
     */
    @GetMapping("/oferta-educativas")
    public List<OfertaEducativa> getAllOfertaEducativas() {
        log.debug("REST request to get all OfertaEducativas");
        return ofertaEducativaService.findAll();
    }

    /**
     * {@code GET  /oferta-educativas/:id} : get the "id" ofertaEducativa.
     *
     * @param id the id of the ofertaEducativa to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ofertaEducativa, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/oferta-educativas/{id}")
    public ResponseEntity<OfertaEducativa> getOfertaEducativa(@PathVariable Long id) {
        log.debug("REST request to get OfertaEducativa : {}", id);
        Optional<OfertaEducativa> ofertaEducativa = ofertaEducativaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ofertaEducativa);
    }

    /**
     * {@code DELETE  /oferta-educativas/:id} : delete the "id" ofertaEducativa.
     *
     * @param id the id of the ofertaEducativa to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/oferta-educativas/{id}")
    public ResponseEntity<Void> deleteOfertaEducativa(@PathVariable Long id) {
        log.debug("REST request to delete OfertaEducativa : {}", id);
        ofertaEducativaService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
