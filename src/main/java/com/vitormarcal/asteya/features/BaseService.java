package com.vitormarcal.asteya.features;

import java.util.List;

public interface BaseService<T> {

    List<T> listar();

    T buscar(Long id);

    T salvar(T t);

    T atualizar(Long id, T t);

    void remover(Long id);
}
