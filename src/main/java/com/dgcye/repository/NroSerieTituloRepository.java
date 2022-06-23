package com.dgcye.repository;

import com.dgcye.domain.NroSerieTitulo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the NroSerieTitulo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NroSerieTituloRepository extends JpaRepository<NroSerieTitulo, Long> {}
