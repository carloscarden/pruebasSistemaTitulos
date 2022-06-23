package com.dgcye.repository;

import com.dgcye.domain.Rendicion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Rendicion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RendicionRepository extends JpaRepository<Rendicion, Long> {}
