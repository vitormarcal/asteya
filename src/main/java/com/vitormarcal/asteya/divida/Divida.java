package com.vitormarcal.asteya.divida;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "divida")
@NoArgsConstructor @Getter @Setter
public class Divida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 10, max = 30)
    private String descricaoCurta;

    @NotEmpty
    @Size(min = 30 , max = 300)
    private String descricaoDetalhada;

    @NotNull
    private LocalDate dataInicioOcorrencia;

    public Divida(String descricaoCurta, String descricaoDetalhada, LocalDate dataInicioOcorrencia) {
        this.descricaoCurta = descricaoCurta;
        this.descricaoDetalhada = descricaoDetalhada;
        this.dataInicioOcorrencia = dataInicioOcorrencia;
    }
}
