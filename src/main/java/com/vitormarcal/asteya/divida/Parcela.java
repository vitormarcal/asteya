package com.vitormarcal.asteya.divida;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "parcela")
@Getter @Setter @NoArgsConstructor
public class Parcela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private Integer recorrencia;

    private Boolean pago;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_divida")
    private Divida divida;

}
