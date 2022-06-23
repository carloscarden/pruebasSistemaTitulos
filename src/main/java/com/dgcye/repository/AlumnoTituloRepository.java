package com.dgcye.repository;

import com.dgcye.domain.AlumnoTitulo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AlumnoTitulo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AlumnoTituloRepository extends JpaRepository<AlumnoTitulo, Long> {}
