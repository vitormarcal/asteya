package br.com.vitormarcal.asteya.api.movimentos.itens;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens")
@RequiredArgsConstructor
class ItemResource {
    @NonNull
    private final ItemService itemService;

    @GetMapping
    public List<Item> listar() {
        return this.itemService.listar();
    }


    @GetMapping("/{id}")
    public Item buscar(@PathVariable String id) {
        return this.itemService.buscar(id);
    }

    @PostMapping
    public Item criar(@RequestBody Item item) {
        return this.itemService.criar(item);
    }
}
