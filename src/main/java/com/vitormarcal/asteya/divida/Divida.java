package com.vitormarcal.asteya.divida;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "divida")
@NoArgsConstructor @Getter @Setter @EqualsAndHashCode(of = "id") @ToString
public class Divida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "O identificador da Dívida gerado pelo database")
    private Long id;

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
