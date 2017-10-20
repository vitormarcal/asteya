package com.vitormarcal.asteya.divida;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dividas")
@Api(value = "dividas", tags = {"Manter dividas"})
public class DividaResource {

    private final DividaService dividaService;

    @Autowired
    public DividaResource(DividaService dividaService) {
        this.dividaService = dividaService;
    }

    @GetMapping
    @ApiOperation(value = "Lista todas as dívidas cadastradas", response = Divida.class, responseContainer = "List")
    public List<Divida> listar() {
        return dividaService.listar();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Busca uma dívida cadastrada", response = Divida.class)
    public ResponseEntity<Divida> buscar(@PathVariable("id") Long idDivida) {
        Divida divida =  dividaService.buscar(idDivida);
        return divida != null ? ResponseEntity.ok(divida) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Guarda uma dívida", response = Divida.class)
    public ResponseEntity<Divida> salvar(@RequestBody @Valid Divida divida) {
        Divida dividaBanco = dividaService.salvar(divida);
        return ResponseEntity.status(HttpStatus.CREATED).body(dividaBanco);
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Atualiza uma dívida", response = Divida.class)
    public ResponseEntity<Divida> atualizar(@PathVariable("id") Long idDivida, @RequestBody @Valid Divida divida) {
        Divida dividaAtualizada = dividaService.atualizar(idDivida, divida);
        return ResponseEntity.ok(dividaAtualizada);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Remove uma dívida")
    public void remover(@PathVariable("id") Long idDivida) {
        dividaService.remover(idDivida);
    }

}
