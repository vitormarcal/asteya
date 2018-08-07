package com.vitormarcal.asteya.features.dividas;

import com.vitormarcal.asteya.features.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "divida")
@Data @EqualsAndHashCode(callSuper = true)
public class Divida extends BaseEntity {

    @NotEmpty
    @Size(min = 10, max = 15)
    @Column(name = "des_curta", nullable = false)
    @ApiModelProperty(notes = "Uma descriçao curta", required = true)
    private String descricaoCurta;

    @NotEmpty
    @Size(min = 16 , max = 300)
    @Column(name = "des_detalhada", nullable = false)
    @ApiModelProperty(notes = "Uma descrição mais informativa da dívida", required = true)
    private String descricaoDetalhada;

    @NotNull
    @Column(name = "dt_ini_ocorrencia", nullable = false, columnDefinition = "DATE")
    @ApiModelProperty(notes = "A data em que a primeira parcela da dívida irá ser cobrada")
    private LocalDateTime dataInicioOcorrencia;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_divida")
    @ApiModelProperty(notes = "As parcelas da dívida")
    private Set<Parcela> parcelas;

    @SuppressWarnings("unused")
    public Divida(String descricaoCurta, String descricaoDetalhada,
                  LocalDateTime dataInicioOcorrencia, Set<Parcela> parcelas) {
        this.descricaoCurta = descricaoCurta;
        this.descricaoDetalhada = descricaoDetalhada;
        this.dataInicioOcorrencia = dataInicioOcorrencia;
        this.parcelas = parcelas;
    }
}
