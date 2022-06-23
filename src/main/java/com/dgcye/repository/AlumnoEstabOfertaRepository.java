package com.dgcye.repository;

import com.dgcye.domain.AlumnoEstabOferta;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AlumnoEstabOferta entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AlumnoEstabOfertaRepository extends JpaRepository<AlumnoEstabOferta, Long> {}
