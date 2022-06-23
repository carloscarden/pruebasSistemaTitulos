package com.dgcye.service;

import com.dgcye.domain.AlumnoAnalitico;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link AlumnoAnalitico}.
 */
public interface AlumnoAnaliticoService {
    /**
     * Save a alumnoAnalitico.
     *
     * @param alumnoAnalitico the entity to save.
     * @return the persisted entity.
     */
    AlumnoAnalitico save(AlumnoAnalitico alumnoAnalitico);

    /**
     * Updates a alumnoAnalitico.
     *
     * @param alumnoAnalitico the entity to update.
     * @return the persisted entity.
     */
    AlumnoAnalitico update(AlumnoAnalitico alumnoAnalitico);

    /**
     * Partially updates a alumnoAnalitico.
     *
     * @param alumnoAnalitico the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AlumnoAnalitico> partialUpdate(AlumnoAnalitico alumnoAnalitico);

    /**
     * Get all the alumnoAnaliticos.
     *
     * @return the list of entities.
     */
    List<AlumnoAnalitico> findAll();

    /**
     * Get the "id" alumnoAnalitico.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AlumnoAnalitico> findOne(Long id);

    /**
     * Delete the "id" alumnoAnalitico.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
