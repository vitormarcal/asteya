package com.vitormarcal.asteya.features.dividas;

import com.vitormarcal.asteya.features.BaseService;
import com.vitormarcal.asteya.features.gastos.Gasto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DividaService implements BaseService<Divida> {

    private static final Logger loggerUtil = LoggerFactory.getLogger(DividaService.class);

    private final DividaRepository dividaRepository;

    @Autowired
    public DividaService(DividaRepository dividaRepository) {
        this.dividaRepository = dividaRepository;
    }

    @Override
    public List<Divida> listar() {
        loggerUtil.info("Buscando todas as dívidas");
        return dividaRepository.findAll();
    }

    @Override
    public Divida salvar(Divida divida) {
        loggerUtil.info("Salvando dívida");
        return dividaRepository.save(divida);
    }

    @Override
    public Divida buscar(Long idDivida) {
        return buscarPorCodigo(idDivida);
    }

    @Override
    public void remover(Long idDivida) {
        loggerUtil.info("Excluindo dívida de acordo com id");
        dividaRepository.delete(idDivida);
        loggerUtil.info("Excluído dívida com sucesso");
    }

    @Override
    public Divida atualizar(Long idDivida, Divida divida) {
        Divida dividaBanco = buscarPorCodigo(idDivida);
        loggerUtil.info("Atualizando dívida existente");
        BeanUtils.copyProperties(divida, dividaBanco, "id");
        return dividaRepository.save(dividaBanco);

    }

    private Divida buscarPorCodigo(Long idDivida) {
        loggerUtil.info("Buscando dívida de acordo com id");
        Optional<Divida> dividaBanco = dividaRepository.findById(idDivida);
        if (dividaBanco.isPresent()) {
            return dividaBanco.get();
        } else {
            loggerUtil.info("Dívida não encontrada");
            throw new EmptyResultDataAccessException(1);
        }
    }
}
