package com.dgcye.web.rest;

import com.dgcye.domain.Turno;
import com.dgcye.repository.TurnoRepository;
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
 * REST controller for managing {@link com.dgcye.domain.Turno}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TurnoResource {

    private final Logger log = LoggerFactory.getLogger(TurnoResource.class);

    private final TurnoRepository turnoRepository;

    public TurnoResource(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    /**
     * {@code GET  /turnos} : get all the turnos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of turnos in body.
     */
    @GetMapping("/turnos")
    public List<Turno> getAllTurnos() {
        log.debug("REST request to get all Turnos");
        return turnoRepository.findAll();
    }

    /**
     * {@code GET  /turnos/:id} : get the "id" turno.
     *
     * @param id the id of the turno to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the turno, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/turnos/{id}")
    public ResponseEntity<Turno> getTurno(@PathVariable Long id) {
        log.debug("REST request to get Turno : {}", id);
        Optional<Turno> turno = turnoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(turno);
    }
}
