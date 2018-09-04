package br.com.vitormarcal.asteya.api.movimentos.itens;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class ItemService {

    private final ItemRepository itemRepository;

    List<Item> listar() {
        return this.itemRepository.findAll();
    }

    Item buscar(String id) {
        return this.itemRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    Item criar(@NonNull Item item) {
        if (item.getId() != null) throw new IllegalCallerException("Recurso já existe.");
        return itemRepository.save(item);
    }
}
