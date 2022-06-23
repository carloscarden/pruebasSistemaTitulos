package com.dgcye.service;

import com.dgcye.domain.AlumnoEstabOferta;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link AlumnoEstabOferta}.
 */
public interface AlumnoEstabOfertaService {
    /**
     * Save a alumnoEstabOferta.
     *
     * @param alumnoEstabOferta the entity to save.
     * @return the persisted entity.
     */
    AlumnoEstabOferta save(AlumnoEstabOferta alumnoEstabOferta);

    /**
     * Updates a alumnoEstabOferta.
     *
     * @param alumnoEstabOferta the entity to update.
     * @return the persisted entity.
     */
    AlumnoEstabOferta update(AlumnoEstabOferta alumnoEstabOferta);

    /**
     * Partially updates a alumnoEstabOferta.
     *
     * @param alumnoEstabOferta the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AlumnoEstabOferta> partialUpdate(AlumnoEstabOferta alumnoEstabOferta);

    /**
     * Get all the alumnoEstabOfertas.
     *
     * @return the list of entities.
     */
    List<AlumnoEstabOferta> findAll();

    /**
     * Get the "id" alumnoEstabOferta.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AlumnoEstabOferta> findOne(Long id);

    /**
     * Delete the "id" alumnoEstabOferta.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
