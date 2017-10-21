package com.vitormarcal.asteya.features.gastos;

import com.vitormarcal.asteya.features.BaseResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gastos")
@Api(value = "gastos", tags = {"Manter gastos"})
public class GastoResouce implements BaseResource<Gasto> {

    private final GastoService gastoService;
    private ApplicationEventPublisher publisher;

    @Autowired
    public GastoResouce(GastoService gastoService, ApplicationEventPublisher publisher) {
        this.gastoService = gastoService;
        this.publisher = publisher;
    }

    @Override
    @ApiOperation(value = "Lista todas os gastos cadastrados", response = Gasto.class, responseContainer = "List")
    public List<Gasto> listar() {
        return gastoService.listar();
    }

    @Override
    @ApiOperation(value = "Busca um gasto cadastrado", response = Gasto.class)
    public ResponseEntity<Gasto> buscar(@PathVariable("id") Long idGasto) {
        Gasto gasto =  gastoService.buscar(idGasto);
        return gasto != null ? ResponseEntity.ok(gasto) : ResponseEntity.notFound().build();
    }

    @Override
    @ApiOperation(value = "Registra um gasto", response = Gasto.class)
    public ResponseEntity<Gasto> salvar(@RequestBody @Valid Gasto gasto, HttpServletResponse response) {
        Gasto gastoBanco = gastoService.salvar(gasto);
        publicaLocalizacaoRecurso(gastoBanco.getId(), response, publisher);
        return ResponseEntity.status(HttpStatus.CREATED).body(gastoBanco);
    }

    @Override
    @ApiOperation(value = "Atualiza um gasto", response = Gasto.class)
    public ResponseEntity<Gasto> atualizar(@PathVariable("id") Long idGasto, @RequestBody @Valid  Gasto gasto) {
        Gasto gastoAtualizado = gastoService.atualizar(idGasto, gasto);
        return ResponseEntity.ok(gastoAtualizado);
    }

    @Override
    @ApiOperation(value = "Remove um gasto")
    public void remover(@PathVariable("id") Long idGasto) {
        gastoService.remover(idGasto);
    }
}
