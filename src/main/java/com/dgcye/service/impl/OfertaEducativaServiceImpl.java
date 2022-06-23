package com.dgcye.service.impl;

import com.dgcye.domain.OfertaEducativa;
import com.dgcye.repository.OfertaEducativaRepository;
import com.dgcye.service.OfertaEducativaService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link OfertaEducativa}.
 */
@Service
@Transactional
public class OfertaEducativaServiceImpl implements OfertaEducativaService {

    private final Logger log = LoggerFactory.getLogger(OfertaEducativaServiceImpl.class);

    private final OfertaEducativaRepository ofertaEducativaRepository;

    public OfertaEducativaServiceImpl(OfertaEducativaRepository ofertaEducativaRepository) {
        this.ofertaEducativaRepository = ofertaEducativaRepository;
    }

    @Override
    public OfertaEducativa save(OfertaEducativa ofertaEducativa) {
        log.debug("Request to save OfertaEducativa : {}", ofertaEducativa);
        return ofertaEducativaRepository.save(ofertaEducativa);
    }

    @Override
    public OfertaEducativa update(OfertaEducativa ofertaEducativa) {
        log.debug("Request to save OfertaEducativa : {}", ofertaEducativa);
        return ofertaEducativaRepository.save(ofertaEducativa);
    }

    @Override
    public Optional<OfertaEducativa> partialUpdate(OfertaEducativa ofertaEducativa) {
        log.debug("Request to partially update OfertaEducativa : {}", ofertaEducativa);

        return ofertaEducativaRepository
            .findById(ofertaEducativa.getId())
            .map(existingOfertaEducativa -> {
                if (ofertaEducativa.getIdModalidad() != null) {
                    existingOfertaEducativa.setIdModalidad(ofertaEducativa.getIdModalidad());
                }
                if (ofertaEducativa.getIdNivel() != null) {
                    existingOfertaEducativa.setIdNivel(ofertaEducativa.getIdNivel());
                }
                if (ofertaEducativa.getIdOrientacion() != null) {
                    existingOfertaEducativa.setIdOrientacion(ofertaEducativa.getIdOrientacion());
                }
                if (ofertaEducativa.getIdTituloDenominacion() != null) {
                    existingOfertaEducativa.setIdTituloDenominacion(ofertaEducativa.getIdTituloDenominacion());
                }
                if (ofertaEducativa.getObserviaciones() != null) {
                    existingOfertaEducativa.setObserviaciones(ofertaEducativa.getObserviaciones());
                }
                if (ofertaEducativa.getIdTipoTitulo() != null) {
                    existingOfertaEducativa.setIdTipoTitulo(ofertaEducativa.getIdTipoTitulo());
                }
                if (ofertaEducativa.getIdUser() != null) {
                    existingOfertaEducativa.setIdUser(ofertaEducativa.getIdUser());
                }
                if (ofertaEducativa.getFechaInicio() != null) {
                    existingOfertaEducativa.setFechaInicio(ofertaEducativa.getFechaInicio());
                }
                if (ofertaEducativa.getFechaFin() != null) {
                    existingOfertaEducativa.setFechaFin(ofertaEducativa.getFechaFin());
                }
                if (ofertaEducativa.getFechaEstado() != null) {
                    existingOfertaEducativa.setFechaEstado(ofertaEducativa.getFechaEstado());
                }
                if (ofertaEducativa.getIdEstadoOferta() != null) {
                    existingOfertaEducativa.setIdEstadoOferta(ofertaEducativa.getIdEstadoOferta());
                }
                if (ofertaEducativa.getIdLeyEducacion() != null) {
                    existingOfertaEducativa.setIdLeyEducacion(ofertaEducativa.getIdLeyEducacion());
                }
                if (ofertaEducativa.getIdNormaAprobacionDen() != null) {
                    existingOfertaEducativa.setIdNormaAprobacionDen(ofertaEducativa.getIdNormaAprobacionDen());
                }
                if (ofertaEducativa.getIdNormaDictamenDen() != null) {
                    existingOfertaEducativa.setIdNormaDictamenDen(ofertaEducativa.getIdNormaDictamenDen());
                }
                if (ofertaEducativa.getIdSeCorrespondeCon() != null) {
                    existingOfertaEducativa.setIdSeCorrespondeCon(ofertaEducativa.getIdSeCorrespondeCon());
                }
                if (ofertaEducativa.getTitulo() != null) {
                    existingOfertaEducativa.setTitulo(ofertaEducativa.getTitulo());
                }
                if (ofertaEducativa.getTituloImpresion() != null) {
                    existingOfertaEducativa.setTituloImpresion(ofertaEducativa.getTituloImpresion());
                }
                if (ofertaEducativa.getTituloIntermedio() != null) {
                    existingOfertaEducativa.setTituloIntermedio(ofertaEducativa.getTituloIntermedio());
                }
                if (ofertaEducativa.getTituloIntermedioImpresion() != null) {
                    existingOfertaEducativa.setTituloIntermedioImpresion(ofertaEducativa.getTituloIntermedioImpresion());
                }
                if (ofertaEducativa.getOrientacion() != null) {
                    existingOfertaEducativa.setOrientacion(ofertaEducativa.getOrientacion());
                }
                if (ofertaEducativa.getIdRama() != null) {
                    existingOfertaEducativa.setIdRama(ofertaEducativa.getIdRama());
                }
                if (ofertaEducativa.getIdSeccionTituloIntermedio() != null) {
                    existingOfertaEducativa.setIdSeccionTituloIntermedio(ofertaEducativa.getIdSeccionTituloIntermedio());
                }
                if (ofertaEducativa.getIdCursoGrupoNombre() != null) {
                    existingOfertaEducativa.setIdCursoGrupoNombre(ofertaEducativa.getIdCursoGrupoNombre());
                }
                if (ofertaEducativa.getCorrelatividad() != null) {
                    existingOfertaEducativa.setCorrelatividad(ofertaEducativa.getCorrelatividad());
                }
                if (ofertaEducativa.getIdModalidadDictado() != null) {
                    existingOfertaEducativa.setIdModalidadDictado(ofertaEducativa.getIdModalidadDictado());
                }
                if (ofertaEducativa.getTitula() != null) {
                    existingOfertaEducativa.setTitula(ofertaEducativa.getTitula());
                }
                if (ofertaEducativa.getCicloBasico() != null) {
                    existingOfertaEducativa.setCicloBasico(ofertaEducativa.getCicloBasico());
                }

                return existingOfertaEducativa;
            })
            .map(ofertaEducativaRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OfertaEducativa> findAll() {
        log.debug("Request to get all OfertaEducativas");
        return ofertaEducativaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OfertaEducativa> findOne(Long id) {
        log.debug("Request to get OfertaEducativa : {}", id);
        return ofertaEducativaRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete OfertaEducativa : {}", id);
        ofertaEducativaRepository.deleteById(id);
    }
}
