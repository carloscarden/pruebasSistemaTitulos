package com.dgcye.service;

import com.dgcye.domain.NroSerieTitulo;
import com.dgcye.repository.NroSerieTituloRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NroSerieTitulo}.
 */
@Service
@Transactional
public class NroSerieTituloService {

    private final Logger log = LoggerFactory.getLogger(NroSerieTituloService.class);

    private final NroSerieTituloRepository nroSerieTituloRepository;

    public NroSerieTituloService(NroSerieTituloRepository nroSerieTituloRepository) {
        this.nroSerieTituloRepository = nroSerieTituloRepository;
    }

    /**
     * Save a nroSerieTitulo.
     *
     * @param nroSerieTitulo the entity to save.
     * @return the persisted entity.
     */
    public NroSerieTitulo save(NroSerieTitulo nroSerieTitulo) {
        log.debug("Request to save NroSerieTitulo : {}", nroSerieTitulo);
        return nroSerieTituloRepository.save(nroSerieTitulo);
    }

    /**
     * Update a nroSerieTitulo.
     *
     * @param nroSerieTitulo the entity to save.
     * @return the persisted entity.
     */
    public NroSerieTitulo update(NroSerieTitulo nroSerieTitulo) {
        log.debug("Request to save NroSerieTitulo : {}", nroSerieTitulo);
        return nroSerieTituloRepository.save(nroSerieTitulo);
    }

    /**
     * Partially update a nroSerieTitulo.
     *
     * @param nroSerieTitulo the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<NroSerieTitulo> partialUpdate(NroSerieTitulo nroSerieTitulo) {
        log.debug("Request to partially update NroSerieTitulo : {}", nroSerieTitulo);

        return nroSerieTituloRepository
            .findById(nroSerieTitulo.getId())
            .map(existingNroSerieTitulo -> {
                if (nroSerieTitulo.getNroSerieInicio() != null) {
                    existingNroSerieTitulo.setNroSerieInicio(nroSerieTitulo.getNroSerieInicio());
                }
                if (nroSerieTitulo.getNroSerieFin() != null) {
                    existingNroSerieTitulo.setNroSerieFin(nroSerieTitulo.getNroSerieFin());
                }
                if (nroSerieTitulo.getFechaInicio() != null) {
                    existingNroSerieTitulo.setFechaInicio(nroSerieTitulo.getFechaInicio());
                }
                if (nroSerieTitulo.getFechaFin() != null) {
                    existingNroSerieTitulo.setFechaFin(nroSerieTitulo.getFechaFin());
                }
                if (nroSerieTitulo.getUsuarioAlta() != null) {
                    existingNroSerieTitulo.setUsuarioAlta(nroSerieTitulo.getUsuarioAlta());
                }
                if (nroSerieTitulo.getUsuarioModificacion() != null) {
                    existingNroSerieTitulo.setUsuarioModificacion(nroSerieTitulo.getUsuarioModificacion());
                }
                if (nroSerieTitulo.getSerie() != null) {
                    existingNroSerieTitulo.setSerie(nroSerieTitulo.getSerie());
                }

                return existingNroSerieTitulo;
            })
            .map(nroSerieTituloRepository::save);
    }

    /**
     * Get all the nroSerieTitulos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<NroSerieTitulo> findAll() {
        log.debug("Request to get all NroSerieTitulos");
        return nroSerieTituloRepository.findAll();
    }

    /**
     * Get one nroSerieTitulo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NroSerieTitulo> findOne(Long id) {
        log.debug("Request to get NroSerieTitulo : {}", id);
        return nroSerieTituloRepository.findById(id);
    }

    /**
     * Delete the nroSerieTitulo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NroSerieTitulo : {}", id);
        nroSerieTituloRepository.deleteById(id);
    }
}
