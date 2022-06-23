package com.dgcye.web.rest;

import com.dgcye.domain.Jornada;
import com.dgcye.repository.JornadaRepository;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.dgcye.domain.Jornada}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class JornadaResource {

    private final Logger log = LoggerFactory.getLogger(JornadaResource.class);

    private final JornadaRepository jornadaRepository;

    public JornadaResource(JornadaRepository jornadaRepository) {
        this.jornadaRepository = jornadaRepository;
    }

    /**
     * {@code GET  /jornadas} : get all the jornadas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jornadas in body.
     */
    @GetMapping("/jornadas")
    public List<Jornada> getAllJornadas() {
        log.debug("REST request to get all Jornadas");
        return jornadaRepository.findAll();
    }

    /**
     * {@code GET  /jornadas/:id} : get the "id" jornada.
     *
     * @param id the id of the jornada to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the jornada, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/jornadas/{id}")
    public ResponseEntity<Jornada> getJornada(@PathVariable Long id) {
        log.debug("REST request to get Jornada : {}", id);
        Optional<Jornada> jornada = jornadaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(jornada);
    }
}
