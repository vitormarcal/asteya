package com.vitormarcal.asteya.divida;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "parcela")
@NoArgsConstructor @Getter @Setter @EqualsAndHashCode @ToString
public class Parcela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "O identificador da Parcela gerado pelo database")
    private Long id;

    @NotNull
    @ApiModelProperty(notes = "O valor da parcela", required = true)
    private BigDecimal valor;

    @NotNull
    @ApiModelProperty(notes = "A quantidade de parcelas com este valor", required = true)
    private Integer recorrencia;

    @ApiModelProperty(notes = "identifica se a parcela está paga")
    private Boolean pago;

    @SuppressWarnings("unused")
    public Parcela(BigDecimal valor, Integer recorrencia, Boolean pago) {
        this.valor = valor;
        this.recorrencia = recorrencia;
        this.pago = pago;
    }
}
