package com.nefee.pokedaycare.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "evolution_groups")
@NamedQueries({
        @NamedQuery(name = "EvolutionGroupEntity.findPokemonByRank", query =
                "SELECT p FROM PokemonEntity p " +
                "WHERE p.evolution.evolutionGroup = :evolution_group AND " +
                "WHERE p.evolution.rank = :rank")
})
public class EvolutionGroupEntity extends PokeDayCareEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "evolution_group_id", unique = true, nullable = false)
    private Long id;

    @OneToMany(mappedBy = "evolutionGroup")
    private List<EvolutionEntity> evolutions;

}
