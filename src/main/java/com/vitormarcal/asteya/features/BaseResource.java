package com.vitormarcal.asteya.features;

import com.vitormarcal.asteya.events.RecursoCriadoEvent;
import org.springframework.context.ApplicationEventPublisher;
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

    /**
     * Aciona evento que escreve no header do response o Location (localização) do recurso criado.
     * @param id codigo identificador do recurso no banco de dados
     * @param response response da requisição que será adicionado o Location
     * @param publisher
     * @see RecursoCriadoEvent
     */
    default void publicaLocalizacaoRecurso(Long id, HttpServletResponse response, ApplicationEventPublisher publisher) {
        publisher.publishEvent(new RecursoCriadoEvent(this, response, id));
    }


}
