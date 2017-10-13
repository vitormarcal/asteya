package com.vitormarcal.asteya.divida;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DividaRepository extends JpaRepository<Divida, Integer>{

    Optional<Divida> findById(Integer idDivida);

}
