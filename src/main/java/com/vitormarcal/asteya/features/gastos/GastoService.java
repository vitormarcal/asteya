package com.vitormarcal.asteya.features.gastos;

import com.vitormarcal.asteya.features.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GastoService implements BaseService<Gasto> {

    private static final Logger loggerUtil = LoggerFactory.getLogger(GastoService.class);

    private final GastoRespository gastoRespository;

    @Autowired
    public GastoService(GastoRespository gastoRespository) {
        this.gastoRespository = gastoRespository;
    }

    @Override
    public List<Gasto> listar() {
        loggerUtil.info("Buscando todos os gastos");
        return gastoRespository.findAll();
    }

    @Override
    public Gasto buscar(Long id) {
        return buscarPorCodigo(id);
    }


    @Override
    public void remover(Long id) {
        loggerUtil.info("Excluindo gasto de acordo com id");
        gastoRespository.deleteById(id);
        loggerUtil.info("Excluído gasto com sucesso");
    }

    @Override
    public Gasto atualizar(Long idGasto, Gasto gasto) {
        Gasto gastobanco = buscarPorCodigo(idGasto);
        loggerUtil.info("Atualizando gasto existente");
        BeanUtils.copyProperties(gasto, gastobanco, "id");
        return gastoRespository.save(gastobanco);
    }

    @Override
    public Gasto salvar(Gasto gasto) {
        loggerUtil.info("Salvando gasto");
        return gastoRespository.save(gasto);
    }

    private Gasto buscarPorCodigo(Long idGasto) {
        loggerUtil.info("Buscando gasto de acordo com id");
        Optional<Gasto> gastoBanco = gastoRespository.findById(idGasto);
        if (gastoBanco.isPresent()) {
            return gastoBanco.get();
        } else {
            loggerUtil.info("Gasto não encontrado");
            throw new EmptyResultDataAccessException(1);
        }
    }
}
