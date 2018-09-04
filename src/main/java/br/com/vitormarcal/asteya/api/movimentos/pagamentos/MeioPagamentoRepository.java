package br.com.vitormarcal.asteya.api.movimentos.pagamentos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MeioPagamentoRepository extends MongoRepository<MeioPagamento, String> {

    Optional<MeioPagamento> findByDescricaoEquals(String descricao);
}
