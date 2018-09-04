package br.com.vitormarcal.asteya.api.movimentos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface MovimentoRepository extends MongoRepository<Movimento, String> {
}
