package com.dgcye.service;

import com.dgcye.domain.Rendicion;
import com.dgcye.repository.RendicionRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Rendicion}.
 */
@Service
@Transactional
public class RendicionService {

    private final Logger log = LoggerFactory.getLogger(RendicionService.class);

    private final RendicionRepository rendicionRepository;

    public RendicionService(RendicionRepository rendicionRepository) {
        this.rendicionRepository = rendicionRepository;
    }

    /**
     * Save a rendicion.
     *
     * @param rendicion the entity to save.
     * @return the persisted entity.
     */
    public Rendicion save(Rendicion rendicion) {
        log.debug("Request to save Rendicion : {}", rendicion);
        return rendicionRepository.save(rendicion);
    }

    /**
     * Update a rendicion.
     *
     * @param rendicion the entity to save.
     * @return the persisted entity.
     */
    public Rendicion update(Rendicion rendicion) {
        log.debug("Request to save Rendicion : {}", rendicion);
        return rendicionRepository.save(rendicion);
    }

    /**
     * Partially update a rendicion.
     *
     * @param rendicion the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Rendicion> partialUpdate(Rendicion rendicion) {
        log.debug("Request to partially update Rendicion : {}", rendicion);

        return rendicionRepository
            .findById(rendicion.getId())
            .map(existingRendicion -> {
                if (rendicion.getDniUsuario() != null) {
                    existingRendicion.setDniUsuario(rendicion.getDniUsuario());
                }
                if (rendicion.getNombreUsuario() != null) {
                    existingRendicion.setNombreUsuario(rendicion.getNombreUsuario());
                }
                if (rendicion.getDniUsuarioAnulador() != null) {
                    existingRendicion.setDniUsuarioAnulador(rendicion.getDniUsuarioAnulador());
                }
                if (rendicion.getNombreUsuarioAnulador() != null) {
                    existingRendicion.setNombreUsuarioAnulador(rendicion.getNombreUsuarioAnulador());
                }
                if (rendicion.getMotivo() != null) {
                    existingRendicion.setMotivo(rendicion.getMotivo());
                }
                if (rendicion.getCausaRechazo() != null) {
                    existingRendicion.setCausaRechazo(rendicion.getCausaRechazo());
                }
                if (rendicion.getFechaAnulacion() != null) {
                    existingRendicion.setFechaAnulacion(rendicion.getFechaAnulacion());
                }
                if (rendicion.getDniAlumno() != null) {
                    existingRendicion.setDniAlumno(rendicion.getDniAlumno());
                }
                if (rendicion.getNombreAlumno() != null) {
                    existingRendicion.setNombreAlumno(rendicion.getNombreAlumno());
                }
                if (rendicion.getAlumnoTituloId() != null) {
                    existingRendicion.setAlumnoTituloId(rendicion.getAlumnoTituloId());
                }
                if (rendicion.getEstablecimientoId() != null) {
                    existingRendicion.setEstablecimientoId(rendicion.getEstablecimientoId());
                }
                if (rendicion.getClaveEstab() != null) {
                    existingRendicion.setClaveEstab(rendicion.getClaveEstab());
                }
                if (rendicion.getRama() != null) {
                    existingRendicion.setRama(rendicion.getRama());
                }
                if (rendicion.getNroFormulario() != null) {
                    existingRendicion.setNroFormulario(rendicion.getNroFormulario());
                }

                return existingRendicion;
            })
            .map(rendicionRepository::save);
    }

    /**
     * Get all the rendicions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Rendicion> findAll(Pageable pageable) {
        log.debug("Request to get all Rendicions");
        return rendicionRepository.findAll(pageable);
    }

    /**
     * Get one rendicion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Rendicion> findOne(Long id) {
        log.debug("Request to get Rendicion : {}", id);
        return rendicionRepository.findById(id);
    }

    /**
     * Delete the rendicion by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Rendicion : {}", id);
        rendicionRepository.deleteById(id);
    }
}
