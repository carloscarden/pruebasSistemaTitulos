package com.dgcye.service.impl;

import com.dgcye.domain.AlumnoAnalitico;
import com.dgcye.repository.AlumnoAnaliticoRepository;
import com.dgcye.service.AlumnoAnaliticoService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AlumnoAnalitico}.
 */
@Service
@Transactional
public class AlumnoAnaliticoServiceImpl implements AlumnoAnaliticoService {

    private final Logger log = LoggerFactory.getLogger(AlumnoAnaliticoServiceImpl.class);

    private final AlumnoAnaliticoRepository alumnoAnaliticoRepository;

    public AlumnoAnaliticoServiceImpl(AlumnoAnaliticoRepository alumnoAnaliticoRepository) {
        this.alumnoAnaliticoRepository = alumnoAnaliticoRepository;
    }

    @Override
    public AlumnoAnalitico save(AlumnoAnalitico alumnoAnalitico) {
        log.debug("Request to save AlumnoAnalitico : {}", alumnoAnalitico);
        return alumnoAnaliticoRepository.save(alumnoAnalitico);
    }

    @Override
    public AlumnoAnalitico update(AlumnoAnalitico alumnoAnalitico) {
        log.debug("Request to save AlumnoAnalitico : {}", alumnoAnalitico);
        return alumnoAnaliticoRepository.save(alumnoAnalitico);
    }

    @Override
    public Optional<AlumnoAnalitico> partialUpdate(AlumnoAnalitico alumnoAnalitico) {
        log.debug("Request to partially update AlumnoAnalitico : {}", alumnoAnalitico);

        return alumnoAnaliticoRepository
            .findById(alumnoAnalitico.getId())
            .map(existingAlumnoAnalitico -> {
                if (alumnoAnalitico.getNota() != null) {
                    existingAlumnoAnalitico.setNota(alumnoAnalitico.getNota());
                }
                if (alumnoAnalitico.getIdEstadoAlumnoAnalitico() != null) {
                    existingAlumnoAnalitico.setIdEstadoAlumnoAnalitico(alumnoAnalitico.getIdEstadoAlumnoAnalitico());
                }
                if (alumnoAnalitico.getIdAlumnoEstabOferta() != null) {
                    existingAlumnoAnalitico.setIdAlumnoEstabOferta(alumnoAnalitico.getIdAlumnoEstabOferta());
                }
                if (alumnoAnalitico.getIdMateria() != null) {
                    existingAlumnoAnalitico.setIdMateria(alumnoAnalitico.getIdMateria());
                }
                if (alumnoAnalitico.getIdMesImp() != null) {
                    existingAlumnoAnalitico.setIdMesImp(alumnoAnalitico.getIdMesImp());
                }
                if (alumnoAnalitico.getIdAnoImp() != null) {
                    existingAlumnoAnalitico.setIdAnoImp(alumnoAnalitico.getIdAnoImp());
                }
                if (alumnoAnalitico.getEstablecimientoImp() != null) {
                    existingAlumnoAnalitico.setEstablecimientoImp(alumnoAnalitico.getEstablecimientoImp());
                }

                return existingAlumnoAnalitico;
            })
            .map(alumnoAnaliticoRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlumnoAnalitico> findAll() {
        log.debug("Request to get all AlumnoAnaliticos");
        return alumnoAnaliticoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AlumnoAnalitico> findOne(Long id) {
        log.debug("Request to get AlumnoAnalitico : {}", id);
        return alumnoAnaliticoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AlumnoAnalitico : {}", id);
        alumnoAnaliticoRepository.deleteById(id);
    }
}
