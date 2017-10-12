package com.vitormarcal.asteya.divida;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "parcela")
@Getter @Setter @NoArgsConstructor
public class Parcela {

    @NotNull
    private BigDecimal valor;

    @NotNull
    private Integer recorrencia;

    private boolean pago;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_divida")
    private Divida divida;

}
