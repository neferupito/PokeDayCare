package com.nefee.pokedaycare.data.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "egg_groups")
public class EggGroupEntity extends PokeDayCareEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "egg_group_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @ManyToMany (mappedBy="eggGroups")
    private List<PokemonEntity> pokemons;

}
