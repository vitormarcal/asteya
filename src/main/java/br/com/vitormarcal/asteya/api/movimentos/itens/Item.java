package br.com.vitormarcal.asteya.api.movimentos.itens;

import br.com.vitormarcal.asteya.api.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@EqualsAndHashCode(of = "id", callSuper = false)
public class Item extends BaseModel {

    @Id
    private String id;
    private String descricao;

}
