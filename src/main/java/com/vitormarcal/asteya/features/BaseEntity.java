package com.vitormarcal.asteya.features;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id") @ToString
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(notes = "O identificador gerado pelo database")
    protected Long id;
}
