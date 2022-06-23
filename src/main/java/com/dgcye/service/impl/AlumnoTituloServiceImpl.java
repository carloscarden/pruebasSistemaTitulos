package com.dgcye.service.impl;

import com.dgcye.domain.AlumnoTitulo;
import com.dgcye.repository.AlumnoTituloRepository;
import com.dgcye.service.AlumnoTituloService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AlumnoTitulo}.
 */
@Service
@Transactional
public class AlumnoTituloServiceImpl implements AlumnoTituloService {

    private final Logger log = LoggerFactory.getLogger(AlumnoTituloServiceImpl.class);

    private final AlumnoTituloRepository alumnoTituloRepository;

    public AlumnoTituloServiceImpl(AlumnoTituloRepository alumnoTituloRepository) {
        this.alumnoTituloRepository = alumnoTituloRepository;
    }

    @Override
    public AlumnoTitulo save(AlumnoTitulo alumnoTitulo) {
        log.debug("Request to save AlumnoTitulo : {}", alumnoTitulo);
        return alumnoTituloRepository.save(alumnoTitulo);
    }

    @Override
    public AlumnoTitulo update(AlumnoTitulo alumnoTitulo) {
        log.debug("Request to save AlumnoTitulo : {}", alumnoTitulo);
        return alumnoTituloRepository.save(alumnoTitulo);
    }

    @Override
    public Optional<AlumnoTitulo> partialUpdate(AlumnoTitulo alumnoTitulo) {
        log.debug("Request to partially update AlumnoTitulo : {}", alumnoTitulo);

        return alumnoTituloRepository
            .findById(alumnoTitulo.getId())
            .map(existingAlumnoTitulo -> {
                if (alumnoTitulo.getIdAlumnoEstabOferta() != null) {
                    existingAlumnoTitulo.setIdAlumnoEstabOferta(alumnoTitulo.getIdAlumnoEstabOferta());
                }
                if (alumnoTitulo.getFechaEmision() != null) {
                    existingAlumnoTitulo.setFechaEmision(alumnoTitulo.getFechaEmision());
                }
                if (alumnoTitulo.getNroSerie() != null) {
                    existingAlumnoTitulo.setNroSerie(alumnoTitulo.getNroSerie());
                }
                if (alumnoTitulo.getNumeroRfifd() != null) {
                    existingAlumnoTitulo.setNumeroRfifd(alumnoTitulo.getNumeroRfifd());
                }
                if (alumnoTitulo.getValidezNacional() != null) {
                    existingAlumnoTitulo.setValidezNacional(alumnoTitulo.getValidezNacional());
                }
                if (alumnoTitulo.getFechaEnvio() != null) {
                    existingAlumnoTitulo.setFechaEnvio(alumnoTitulo.getFechaEnvio());
                }
                if (alumnoTitulo.getFechaEgreso() != null) {
                    existingAlumnoTitulo.setFechaEgreso(alumnoTitulo.getFechaEgreso());
                }
                if (alumnoTitulo.getNroLibroMatriz() != null) {
                    existingAlumnoTitulo.setNroLibroMatriz(alumnoTitulo.getNroLibroMatriz());
                }
                if (alumnoTitulo.getNroActa() != null) {
                    existingAlumnoTitulo.setNroActa(alumnoTitulo.getNroActa());
                }
                if (alumnoTitulo.getNroFolio() != null) {
                    existingAlumnoTitulo.setNroFolio(alumnoTitulo.getNroFolio());
                }
                if (alumnoTitulo.getEstabNombre() != null) {
                    existingAlumnoTitulo.setEstabNombre(alumnoTitulo.getEstabNombre());
                }
                if (alumnoTitulo.getEstabCue() != null) {
                    existingAlumnoTitulo.setEstabCue(alumnoTitulo.getEstabCue());
                }
                if (alumnoTitulo.getEstabUbicadoEn() != null) {
                    existingAlumnoTitulo.setEstabUbicadoEn(alumnoTitulo.getEstabUbicadoEn());
                }
                if (alumnoTitulo.getEstabCiudad() != null) {
                    existingAlumnoTitulo.setEstabCiudad(alumnoTitulo.getEstabCiudad());
                }
                if (alumnoTitulo.getEstabProvincia() != null) {
                    existingAlumnoTitulo.setEstabProvincia(alumnoTitulo.getEstabProvincia());
                }
                if (alumnoTitulo.getIdEstadoAlumnoTitulo() != null) {
                    existingAlumnoTitulo.setIdEstadoAlumnoTitulo(alumnoTitulo.getIdEstadoAlumnoTitulo());
                }
                if (alumnoTitulo.getTituloIntermedio() != null) {
                    existingAlumnoTitulo.setTituloIntermedio(alumnoTitulo.getTituloIntermedio());
                }
                if (alumnoTitulo.getPromedio() != null) {
                    existingAlumnoTitulo.setPromedio(alumnoTitulo.getPromedio());
                }
                if (alumnoTitulo.getFechaRuta() != null) {
                    existingAlumnoTitulo.setFechaRuta(alumnoTitulo.getFechaRuta());
                }
                if (alumnoTitulo.getIdRamaRuta() != null) {
                    existingAlumnoTitulo.setIdRamaRuta(alumnoTitulo.getIdRamaRuta());
                }
                if (alumnoTitulo.getApYnomListoParaEnviar() != null) {
                    existingAlumnoTitulo.setApYnomListoParaEnviar(alumnoTitulo.getApYnomListoParaEnviar());
                }
                if (alumnoTitulo.getDocumentoListoParaEnviar() != null) {
                    existingAlumnoTitulo.setDocumentoListoParaEnviar(alumnoTitulo.getDocumentoListoParaEnviar());
                }
                if (alumnoTitulo.getApYnomEnviado() != null) {
                    existingAlumnoTitulo.setApYnomEnviado(alumnoTitulo.getApYnomEnviado());
                }
                if (alumnoTitulo.getDocumentoEnviado() != null) {
                    existingAlumnoTitulo.setDocumentoEnviado(alumnoTitulo.getDocumentoEnviado());
                }
                if (alumnoTitulo.getFechaUltimoEstado() != null) {
                    existingAlumnoTitulo.setFechaUltimoEstado(alumnoTitulo.getFechaUltimoEstado());
                }

                return existingAlumnoTitulo;
            })
            .map(alumnoTituloRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AlumnoTitulo> findAll(Pageable pageable) {
        log.debug("Request to get all AlumnoTitulos");
        return alumnoTituloRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AlumnoTitulo> findOne(Long id) {
        log.debug("Request to get AlumnoTitulo : {}", id);
        return alumnoTituloRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AlumnoTitulo : {}", id);
        alumnoTituloRepository.deleteById(id);
    }
}
