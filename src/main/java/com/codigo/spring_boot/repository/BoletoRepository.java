package com.codigo.spring_boot.repository;

import com.codigo.spring_boot.entity.BoletoRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoletoRepository extends JpaRepository<BoletoRequest, Integer> {
}
