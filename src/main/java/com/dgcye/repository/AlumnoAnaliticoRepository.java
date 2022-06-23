package com.dgcye.repository;

import com.dgcye.domain.AlumnoAnalitico;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AlumnoAnalitico entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AlumnoAnaliticoRepository extends JpaRepository<AlumnoAnalitico, Long> {}
