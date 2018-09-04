package br.com.vitormarcal.asteya.api.movimentos;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentos")
@RequiredArgsConstructor
class MovimentoResource {

    @NonNull private final MovimentoService movimentoService;

    @GetMapping
    public List<Movimento> listar() {
        return this.movimentoService.listar();
    }


    @GetMapping("/{id}")
    public Movimento buscar(@PathVariable String id) {
        return this.movimentoService.buscar(id);
    }

    @PostMapping
    public Movimento criar(@RequestBody Movimento movimento) {
        return this.movimentoService.criar(movimento);
    }


}
