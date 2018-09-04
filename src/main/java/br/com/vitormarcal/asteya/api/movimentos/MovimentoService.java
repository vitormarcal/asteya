package br.com.vitormarcal.asteya.api.movimentos;

import br.com.vitormarcal.asteya.api.movimentos.itens.Item;
import br.com.vitormarcal.asteya.api.movimentos.itens.ItemRepository;
import br.com.vitormarcal.asteya.api.movimentos.pagamentos.MeioPagamento;
import br.com.vitormarcal.asteya.api.movimentos.pagamentos.MeioPagamentoRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class MovimentoService {

    @NonNull
    MovimentoRepository movimentoRepository;
    @NonNull
    ItemRepository itemRepository;
    @NonNull
    MeioPagamentoRepository meioPagamentoRepository;

    List<Movimento> listar() {
        return movimentoRepository.findAll();
    }

    Movimento buscar(String id) {
        return movimentoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    Movimento criar(@NonNull Movimento movimento) {
        if (movimento.getId() != null) throw new IllegalCallerException("Recurso já existe.");
        movimento.setItem(saveItem(movimento.getItem()));
        movimento.setMeioPagamento(saveMeioPagamento(movimento.getMeioPagamento()));
        return movimentoRepository.save(movimento);
    }

    private MeioPagamento saveMeioPagamento(MeioPagamento meioPagamento) {
        if (meioPagamento != null && meioPagamento.getId() == null) {
            Optional<MeioPagamento> meio = meioPagamentoRepository.findByDescricaoEquals(meioPagamento.getDescricao());
            if (!meio.isPresent())
                return meioPagamentoRepository.save(meioPagamento);
        }
        return meioPagamento;
    }

    private Item saveItem(Item item) {
        if (item != null && item.getId() == null) {
            return itemRepository.save(item);
        }
        return item;
    }
}
