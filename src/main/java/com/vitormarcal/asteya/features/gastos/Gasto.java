package com.vitormarcal.asteya.features.gastos;

import com.vitormarcal.asteya.features.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "gasto")
@Data @EqualsAndHashCode(callSuper = false)
public class Gasto extends BaseEntity {

    @NotEmpty
    @Size(min = 10 , max = 50)
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

    @NotNull
    @Column(name = "num_parcelas", nullable = false)
    @ApiModelProperty(notes = "O número de parcelas do gasto")
    private Integer numeroParcelas;

    @SuppressWarnings("unused")
    public Gasto(Long id, String descricao, BigDecimal valor, LocalDateTime dataOcorrencia, Integer numeroParcelas) {
        super.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.dataOcorrencia = dataOcorrencia;
        this.numeroParcelas = numeroParcelas;
    }
}


