package com.vitormarcal.asteya.features;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface BaseResource<T> {

    @GetMapping
    List<T> listar();

    @GetMapping("/{id}")
    ResponseEntity<T> buscar(Long id);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<T> salvar(T t, HttpServletResponse response);

    @PutMapping("{id}")
    ResponseEntity<T> atualizar(Long id , T t);

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void remover(Long id);


}
