package br.com.vitormarcal.asteya.api.movimentos.pagamentos;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meiosPagamento")
@RequiredArgsConstructor
public class MeioPagamentoResource {
    @NonNull
    private final MeioPagamentoService meioPagamentoService;

    @GetMapping
    public List<MeioPagamento> listar() {
        return this.meioPagamentoService.listar();
    }


    @GetMapping("/{id}")
    public MeioPagamento buscar(@PathVariable String id) {
        return this.meioPagamentoService.buscar(id);
    }

    @PostMapping
    public MeioPagamento criar(@RequestBody MeioPagamento meioPagamento) {
        return this.meioPagamentoService.criar(meioPagamento);
    }
}
