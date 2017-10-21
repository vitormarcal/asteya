package com.vitormarcal.asteya.features.gastos;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "gasto")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id") @ToString
public class Gasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "O identificador do Gasto gerado pelo database")
    private Long id;

    @NotEmpty
    @Size(min = 16 , max = 50)
    @Column(name = "des_curta", nullable = false)
    @ApiModelProperty(notes = "Uma descrição curta do gasto", required = true)
    private String descricao;

    @NotNull
    @ApiModelProperty(notes = "O valor do gasto", required = true)
    private BigDecimal valor;

    @NotNull
    @Column(name = "dt_ocorrencia", nullable = false, columnDefinition = "DATE")
    @ApiModelProperty(notes = "A data em que o gasto foo feito")
    private LocalDateTime dataOcorrencia;


}
