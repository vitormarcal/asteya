package br.com.vitormarcal.asteya.api.movimentos.pagamentos;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeioPagamentoService {
    private final MeioPagamentoRepository meioPagamentoRepository;

    List<MeioPagamento> listar() {
        return this.meioPagamentoRepository.findAll();
    }

    MeioPagamento buscar(String id) {
        return this.meioPagamentoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    MeioPagamento criar(@NonNull MeioPagamento meioPagamento) {
        if (meioPagamento.getId() != null) throw new IllegalCallerException("Recurso já existe.");
        return meioPagamentoRepository.save(meioPagamento);
    }
}
