package com.dgcye.repository;

import com.dgcye.domain.OfertaEducativa;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the OfertaEducativa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OfertaEducativaRepository extends JpaRepository<OfertaEducativa, Long> {}
