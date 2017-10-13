package com.vitormarcal.asteya.divida;

import com.vitormarcal.asteya.AsteyaApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DividaService {

    private static final Logger loggerUtil = LoggerFactory.getLogger(AsteyaApplication.class);

    private final DividaRepository dividaRepository;

    @Autowired
    public DividaService(DividaRepository dividaRepository) {
        this.dividaRepository = dividaRepository;
    }

    public Divida salvar(Divida divida) {
        loggerUtil.info("Salvando divida");
        return dividaRepository.save(divida);
    }

    public Divida buscar(Integer idDivida) {
        return buscarPorCodigo(idDivida);
    }

    public void remover(Integer idDivida) {
        loggerUtil.info("Excluindo divida de acordo com id");
        dividaRepository.delete(idDivida);
        loggerUtil.info("Excluído divida com sucesso");
    }

    public Divida atualizar(Integer idDivida, Divida divida) {
        Divida dividaBanco = buscarPorCodigo(idDivida);
        BeanUtils.copyProperties(divida, dividaBanco, "id");
        return dividaRepository.save(dividaBanco);

    }

    private Divida buscarPorCodigo(Integer idDivida) {
        loggerUtil.info("Buscando divida de acordo com id");
        Optional<Divida> dividaBanco = dividaRepository.findById(idDivida);
        if (dividaBanco.isPresent()) {
            return dividaBanco.get();
        } else {
            loggerUtil.info("Divida não encontrada");
            throw new EmptyResultDataAccessException(1);
        }
    }
}
