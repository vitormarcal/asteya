package com.vitormarcal.asteya.features.gastos;

import com.vitormarcal.asteya.features.BaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class GastoService implements BaseService<Gasto> {

    private final GastoRespository gastoRespository;

    @Override
    public List<Gasto> listar() {
        log.info("Buscando todos os gastos");
        return gastoRespository.findAll();
    }

    @Override
    public Gasto buscar(Long id) {
        return buscarPorCodigo(id);
    }


    @Override
    public void remover(Long id) {
        log.info("Excluindo gasto de acordo com id");
        gastoRespository.deleteById(id);
        log.info("Excluído gasto com sucesso");
    }

    @Override
    public Gasto atualizar(Long idGasto, Gasto gasto) {
        Gasto gastobanco = buscarPorCodigo(idGasto);
        log.info("Atualizando gasto existente");
        BeanUtils.copyProperties(gasto, gastobanco, "id");
        return gastoRespository.save(gastobanco);
    }

    @Override
    public Gasto salvar(Gasto gasto) {
        log.info("Salvando gasto");
        return gastoRespository.save(gasto);
    }

    private Gasto buscarPorCodigo(Long idGasto) {
        log.info("Buscando gasto de acordo com id");
        Optional<Gasto> gastoBanco = gastoRespository.findById(idGasto);
        if (gastoBanco.isPresent()) {
            return gastoBanco.get();
        } else {
            log.info("Gasto não encontrado");
            throw new EmptyResultDataAccessException(1);
        }
    }
}
