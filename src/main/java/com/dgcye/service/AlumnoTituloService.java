package com.dgcye.service;

import com.dgcye.domain.AlumnoTitulo;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link AlumnoTitulo}.
 */
public interface AlumnoTituloService {
    /**
     * Save a alumnoTitulo.
     *
     * @param alumnoTitulo the entity to save.
     * @return the persisted entity.
     */
    AlumnoTitulo save(AlumnoTitulo alumnoTitulo);

    /**
     * Updates a alumnoTitulo.
     *
     * @param alumnoTitulo the entity to update.
     * @return the persisted entity.
     */
    AlumnoTitulo update(AlumnoTitulo alumnoTitulo);

    /**
     * Partially updates a alumnoTitulo.
     *
     * @param alumnoTitulo the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AlumnoTitulo> partialUpdate(AlumnoTitulo alumnoTitulo);

    /**
     * Get all the alumnoTitulos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AlumnoTitulo> findAll(Pageable pageable);

    /**
     * Get the "id" alumnoTitulo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AlumnoTitulo> findOne(Long id);

    /**
     * Delete the "id" alumnoTitulo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
