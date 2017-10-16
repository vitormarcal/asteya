package com.vitormarcal.asteya.divida;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "parcela")
@NoArgsConstructor @Getter @Setter @EqualsAndHashCode(of = "id") @ToString
public class Parcela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private Integer recorrencia;

    private Boolean pago;

    @SuppressWarnings("unused")
    public Parcela(BigDecimal valor, Integer recorrencia, Boolean pago) {
        this.valor = valor;
        this.recorrencia = recorrencia;
        this.pago = pago;
    }
}
