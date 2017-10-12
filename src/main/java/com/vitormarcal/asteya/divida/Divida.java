package com.vitormarcal.asteya.divida;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "divida")
@Getter @Setter @NoArgsConstructor
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

}
