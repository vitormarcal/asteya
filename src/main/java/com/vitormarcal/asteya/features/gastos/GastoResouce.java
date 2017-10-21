package com.vitormarcal.asteya.features.gastos;

import com.vitormarcal.asteya.features.BaseResource;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/gastos")
@Api(value = "gastos", tags = {"Manter gastos"})
public class GastoResouce implements BaseResource<Gasto> {

    private final GastoService gastoService;

    @Autowired
    public GastoResouce(GastoService gastoService) {
        this.gastoService = gastoService;
    }

    @Override
    public List<Gasto> listar() {
        return gastoService.listar();
    }

    @Override
    public ResponseEntity<Gasto> buscar(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Gasto> salvar(Gasto gasto, HttpServletResponse response) {
        return null;
    }

    @Override
    public ResponseEntity<Gasto> atualizar(Long id, Gasto gasto) {
        return null;
    }

    @Override
    public void remover(Long id) {

    }
}
