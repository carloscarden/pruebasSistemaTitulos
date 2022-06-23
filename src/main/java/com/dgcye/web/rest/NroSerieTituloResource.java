package com.dgcye.web.rest;

import com.dgcye.domain.NroSerieTitulo;
import com.dgcye.repository.NroSerieTituloRepository;
import com.dgcye.service.NroSerieTituloService;
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
 * REST controller for managing {@link com.dgcye.domain.NroSerieTitulo}.
 */
@RestController
@RequestMapping("/api")
public class NroSerieTituloResource {

    private final Logger log = LoggerFactory.getLogger(NroSerieTituloResource.class);

    private static final String ENTITY_NAME = "nroSerieTitulo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NroSerieTituloService nroSerieTituloService;

    private final NroSerieTituloRepository nroSerieTituloRepository;

    public NroSerieTituloResource(NroSerieTituloService nroSerieTituloService, NroSerieTituloRepository nroSerieTituloRepository) {
        this.nroSerieTituloService = nroSerieTituloService;
        this.nroSerieTituloRepository = nroSerieTituloRepository;
    }

    /**
     * {@code POST  /nro-serie-titulos} : Create a new nroSerieTitulo.
     *
     * @param nroSerieTitulo the nroSerieTitulo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nroSerieTitulo, or with status {@code 400 (Bad Request)} if the nroSerieTitulo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nro-serie-titulos")
    public ResponseEntity<NroSerieTitulo> createNroSerieTitulo(@RequestBody NroSerieTitulo nroSerieTitulo) throws URISyntaxException {
        log.debug("REST request to save NroSerieTitulo : {}", nroSerieTitulo);
        if (nroSerieTitulo.getId() != null) {
            throw new BadRequestAlertException("A new nroSerieTitulo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NroSerieTitulo result = nroSerieTituloService.save(nroSerieTitulo);
        return ResponseEntity
            .created(new URI("/api/nro-serie-titulos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nro-serie-titulos/:id} : Updates an existing nroSerieTitulo.
     *
     * @param id the id of the nroSerieTitulo to save.
     * @param nroSerieTitulo the nroSerieTitulo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nroSerieTitulo,
     * or with status {@code 400 (Bad Request)} if the nroSerieTitulo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nroSerieTitulo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nro-serie-titulos/{id}")
    public ResponseEntity<NroSerieTitulo> updateNroSerieTitulo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody NroSerieTitulo nroSerieTitulo
    ) throws URISyntaxException {
        log.debug("REST request to update NroSerieTitulo : {}, {}", id, nroSerieTitulo);
        if (nroSerieTitulo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, nroSerieTitulo.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!nroSerieTituloRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        NroSerieTitulo result = nroSerieTituloService.update(nroSerieTitulo);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nroSerieTitulo.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /nro-serie-titulos/:id} : Partial updates given fields of an existing nroSerieTitulo, field will ignore if it is null
     *
     * @param id the id of the nroSerieTitulo to save.
     * @param nroSerieTitulo the nroSerieTitulo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nroSerieTitulo,
     * or with status {@code 400 (Bad Request)} if the nroSerieTitulo is not valid,
     * or with status {@code 404 (Not Found)} if the nroSerieTitulo is not found,
     * or with status {@code 500 (Internal Server Error)} if the nroSerieTitulo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/nro-serie-titulos/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<NroSerieTitulo> partialUpdateNroSerieTitulo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody NroSerieTitulo nroSerieTitulo
    ) throws URISyntaxException {
        log.debug("REST request to partial update NroSerieTitulo partially : {}, {}", id, nroSerieTitulo);
        if (nroSerieTitulo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, nroSerieTitulo.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!nroSerieTituloRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<NroSerieTitulo> result = nroSerieTituloService.partialUpdate(nroSerieTitulo);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nroSerieTitulo.getId().toString())
        );
    }

    /**
     * {@code GET  /nro-serie-titulos} : get all the nroSerieTitulos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nroSerieTitulos in body.
     */
    @GetMapping("/nro-serie-titulos")
    public List<NroSerieTitulo> getAllNroSerieTitulos() {
        log.debug("REST request to get all NroSerieTitulos");
        return nroSerieTituloService.findAll();
    }

    /**
     * {@code GET  /nro-serie-titulos/:id} : get the "id" nroSerieTitulo.
     *
     * @param id the id of the nroSerieTitulo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nroSerieTitulo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nro-serie-titulos/{id}")
    public ResponseEntity<NroSerieTitulo> getNroSerieTitulo(@PathVariable Long id) {
        log.debug("REST request to get NroSerieTitulo : {}", id);
        Optional<NroSerieTitulo> nroSerieTitulo = nroSerieTituloService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nroSerieTitulo);
    }

    /**
     * {@code DELETE  /nro-serie-titulos/:id} : delete the "id" nroSerieTitulo.
     *
     * @param id the id of the nroSerieTitulo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nro-serie-titulos/{id}")
    public ResponseEntity<Void> deleteNroSerieTitulo(@PathVariable Long id) {
        log.debug("REST request to delete NroSerieTitulo : {}", id);
        nroSerieTituloService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
