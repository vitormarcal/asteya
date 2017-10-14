package com.vitormarcal.asteya.divida;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "divida")
@NoArgsConstructor @Getter @Setter @ToString
public class Divida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 10, max = 15)
    @Column(name = "des_curta", nullable = false)
    private String descricaoCurta;

    @NotEmpty
    @Size(min = 16 , max = 300)
    @Column(name = "des_detalhada", nullable = false)
    private String descricaoDetalhada;

    @NotNull
    @Column(name = "dt_ini_ocorrencia", nullable = false, columnDefinition = "DATE")
    private LocalDateTime dataInicioOcorrencia;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_divida")
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
