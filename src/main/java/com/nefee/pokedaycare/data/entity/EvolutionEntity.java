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
@Table (name = "evolutions")
public class EvolutionEntity extends PokeDayCareEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "evolution_id", unique = true, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn (name = "pokemon_id")
    private PokemonEntity pokemon;

    @Column (name = "evolution_rank")
    @NotNull
    private Integer evolutionRank;

    @ManyToOne
    @JoinColumn (name = "evolution_group_id")
    private EvolutionGroupEntity evolutionGroup;

}
