package com.dgcye.service.impl;

import com.dgcye.domain.Alumno;
import com.dgcye.repository.AlumnoRepository;
import com.dgcye.service.AlumnoService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Alumno}.
 */
@Service
@Transactional
public class AlumnoServiceImpl implements AlumnoService {

    private final Logger log = LoggerFactory.getLogger(AlumnoServiceImpl.class);

    private final AlumnoRepository alumnoRepository;

    public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public Alumno save(Alumno alumno) {
        log.debug("Request to save Alumno : {}", alumno);
        return alumnoRepository.save(alumno);
    }

    @Override
    public Alumno update(Alumno alumno) {
        log.debug("Request to save Alumno : {}", alumno);
        return alumnoRepository.save(alumno);
    }

    @Override
    public Optional<Alumno> partialUpdate(Alumno alumno) {
        log.debug("Request to partially update Alumno : {}", alumno);

        return alumnoRepository
            .findById(alumno.getId())
            .map(existingAlumno -> {
                if (alumno.getDocumento() != null) {
                    existingAlumno.setDocumento(alumno.getDocumento());
                }
                if (alumno.getIdTipoDocumento() != null) {
                    existingAlumno.setIdTipoDocumento(alumno.getIdTipoDocumento());
                }
                if (alumno.getNombre() != null) {
                    existingAlumno.setNombre(alumno.getNombre());
                }
                if (alumno.getApellido() != null) {
                    existingAlumno.setApellido(alumno.getApellido());
                }
                if (alumno.getFechaNacimento() != null) {
                    existingAlumno.setFechaNacimento(alumno.getFechaNacimento());
                }
                if (alumno.getSexo() != null) {
                    existingAlumno.setSexo(alumno.getSexo());
                }
                if (alumno.getCiudadNacimiento() != null) {
                    existingAlumno.setCiudadNacimiento(alumno.getCiudadNacimiento());
                }
                if (alumno.getProvinciaNacimiento() != null) {
                    existingAlumno.setProvinciaNacimiento(alumno.getProvinciaNacimiento());
                }
                if (alumno.getIdNacionalidad() != null) {
                    existingAlumno.setIdNacionalidad(alumno.getIdNacionalidad());
                }
                if (alumno.getIdSerCreador() != null) {
                    existingAlumno.setIdSerCreador(alumno.getIdSerCreador());
                }
                if (alumno.getIdProvincia() != null) {
                    existingAlumno.setIdProvincia(alumno.getIdProvincia());
                }

                return existingAlumno;
            })
            .map(alumnoRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> findAll() {
        log.debug("Request to get all Alumnos");
        return alumnoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Alumno> findOne(Long id) {
        log.debug("Request to get Alumno : {}", id);
        return alumnoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Alumno : {}", id);
        alumnoRepository.deleteById(id);
    }
}
