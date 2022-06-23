package com.dgcye.service;

import com.dgcye.domain.OfertaEducativa;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link OfertaEducativa}.
 */
public interface OfertaEducativaService {
    /**
     * Save a ofertaEducativa.
     *
     * @param ofertaEducativa the entity to save.
     * @return the persisted entity.
     */
    OfertaEducativa save(OfertaEducativa ofertaEducativa);

    /**
     * Updates a ofertaEducativa.
     *
     * @param ofertaEducativa the entity to update.
     * @return the persisted entity.
     */
    OfertaEducativa update(OfertaEducativa ofertaEducativa);

    /**
     * Partially updates a ofertaEducativa.
     *
     * @param ofertaEducativa the entity to update partially.
     * @return the persisted entity.
     */
    Optional<OfertaEducativa> partialUpdate(OfertaEducativa ofertaEducativa);

    /**
     * Get all the ofertaEducativas.
     *
     * @return the list of entities.
     */
    List<OfertaEducativa> findAll();

    /**
     * Get the "id" ofertaEducativa.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OfertaEducativa> findOne(Long id);

    /**
     * Delete the "id" ofertaEducativa.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
