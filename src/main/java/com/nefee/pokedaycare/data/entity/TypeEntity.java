package com.nefee.pokedaycare.data.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "types")
public class TypeEntity extends PokeDayCareEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

}
