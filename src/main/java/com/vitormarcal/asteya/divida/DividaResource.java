package com.vitormarcal.asteya.divida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("/dividas")
public class DividaResource {

    @Autowired
    private DividaService dividaService;

    @GetMapping("/{id}")
    public ResponseEntity<Divida> buscar(@PathVariable Integer idDivida) {
        Divida divida =  dividaService.buscar(idDivida);
        return divida != null ? ResponseEntity.ok(divida) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Divida> salvar(@RequestBody @Valid Divida divida) {
        Divida dividaBanco = dividaService.salvar(divida);
        return ResponseEntity.status(HttpStatus.CREATED).body(dividaBanco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Divida> atualizar(@PathVariable Integer idDivida, @RequestBody @Valid Divida divida) {
        Divida dividaAtualizada = dividaService.atualizar(idDivida, divida);
        return ResponseEntity.ok(dividaAtualizada);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Integer idDivida) {
        dividaService.remover(idDivida);
    }

}
