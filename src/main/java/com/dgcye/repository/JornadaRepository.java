package com.dgcye.repository;

import com.dgcye.domain.Jornada;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Jornada entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JornadaRepository extends JpaRepository<Jornada, Long> {}
