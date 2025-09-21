package com.codigo.spring_boot.repository;

import com.codigo.spring_boot.entity.PasajeroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasajeroRepository extends JpaRepository<PasajeroEntity, Integer> {
}
