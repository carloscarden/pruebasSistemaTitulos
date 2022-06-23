package com.dgcye.service.impl;

import com.dgcye.domain.AlumnoEstabOferta;
import com.dgcye.repository.AlumnoEstabOfertaRepository;
import com.dgcye.service.AlumnoEstabOfertaService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AlumnoEstabOferta}.
 */
@Service
@Transactional
public class AlumnoEstabOfertaServiceImpl implements AlumnoEstabOfertaService {

    private final Logger log = LoggerFactory.getLogger(AlumnoEstabOfertaServiceImpl.class);

    private final AlumnoEstabOfertaRepository alumnoEstabOfertaRepository;

    public AlumnoEstabOfertaServiceImpl(AlumnoEstabOfertaRepository alumnoEstabOfertaRepository) {
        this.alumnoEstabOfertaRepository = alumnoEstabOfertaRepository;
    }

    @Override
    public AlumnoEstabOferta save(AlumnoEstabOferta alumnoEstabOferta) {
        log.debug("Request to save AlumnoEstabOferta : {}", alumnoEstabOferta);
        return alumnoEstabOfertaRepository.save(alumnoEstabOferta);
    }

    @Override
    public AlumnoEstabOferta update(AlumnoEstabOferta alumnoEstabOferta) {
        log.debug("Request to save AlumnoEstabOferta : {}", alumnoEstabOferta);
        return alumnoEstabOfertaRepository.save(alumnoEstabOferta);
    }

    @Override
    public Optional<AlumnoEstabOferta> partialUpdate(AlumnoEstabOferta alumnoEstabOferta) {
        log.debug("Request to partially update AlumnoEstabOferta : {}", alumnoEstabOferta);

        return alumnoEstabOfertaRepository
            .findById(alumnoEstabOferta.getId())
            .map(existingAlumnoEstabOferta -> {
                if (alumnoEstabOferta.getIdSer() != null) {
                    existingAlumnoEstabOferta.setIdSer(alumnoEstabOferta.getIdSer());
                }
                if (alumnoEstabOferta.getIdOfertaEducativa() != null) {
                    existingAlumnoEstabOferta.setIdOfertaEducativa(alumnoEstabOferta.getIdOfertaEducativa());
                }
                if (alumnoEstabOferta.getIdAlumno() != null) {
                    existingAlumnoEstabOferta.setIdAlumno(alumnoEstabOferta.getIdAlumno());
                }
                if (alumnoEstabOferta.getIdEstadoAlumnoEstabOferta() != null) {
                    existingAlumnoEstabOferta.setIdEstadoAlumnoEstabOferta(alumnoEstabOferta.getIdEstadoAlumnoEstabOferta());
                }
                if (alumnoEstabOferta.getFechaInicio() != null) {
                    existingAlumnoEstabOferta.setFechaInicio(alumnoEstabOferta.getFechaInicio());
                }
                if (alumnoEstabOferta.getFechaFin() != null) {
                    existingAlumnoEstabOferta.setFechaFin(alumnoEstabOferta.getFechaFin());
                }

                return existingAlumnoEstabOferta;
            })
            .map(alumnoEstabOfertaRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlumnoEstabOferta> findAll() {
        log.debug("Request to get all AlumnoEstabOfertas");
        return alumnoEstabOfertaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AlumnoEstabOferta> findOne(Long id) {
        log.debug("Request to get AlumnoEstabOferta : {}", id);
        return alumnoEstabOfertaRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AlumnoEstabOferta : {}", id);
        alumnoEstabOfertaRepository.deleteById(id);
    }
}
