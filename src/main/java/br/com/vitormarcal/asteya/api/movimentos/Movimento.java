package br.com.vitormarcal.asteya.api.movimentos;

import br.com.vitormarcal.asteya.api.BaseModel;
import br.com.vitormarcal.asteya.api.movimentos.itens.Item;
import br.com.vitormarcal.asteya.api.movimentos.pagamentos.MeioPagamento;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@Data
@EqualsAndHashCode(of = "id", callSuper = false)
class Movimento extends BaseModel {

    @Id
    private String id;
    private BigDecimal valor;
    private Integer recorrencia;
    private String descricao;
    private TipoMovimento tipoMovimento;
    @DBRef
    private Item item;
    @DBRef
    private MeioPagamento meioPagamento;

}
