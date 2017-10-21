package com.vitormarcal.asteya.features.gastos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GastoRespository extends JpaRepository<Gasto, Long> {
    Optional<Gasto> findById(Long idGasto);
}
