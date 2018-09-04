package br.com.vitormarcal.asteya.api.movimentos.pagamentos;

import br.com.vitormarcal.asteya.api.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@EqualsAndHashCode(of = "id", callSuper = false)
public class MeioPagamento extends BaseModel {

    @Id
    private String id;
    private String descricao;
}
