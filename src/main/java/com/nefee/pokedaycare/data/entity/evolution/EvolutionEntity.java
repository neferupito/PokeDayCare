package com.nefee.pokedaycare.data.entity.evolution;

import com.nefee.pokedaycare.data.entity.PokeDayCareEntity;
import com.nefee.pokedaycare.data.entity.generation.GenerationProfileEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "evolutions")
@NamedQueries ({
        @NamedQuery (name = "EvolutionEntity.findEvolutionByRank", query =
                "SELECT e FROM EvolutionEntity e " +
                        "WHERE e.previousPokemon.evolutionGroup = :evolution_group " +
                        "AND e.evolutionRank = :rank")
})
public class EvolutionEntity extends PokeDayCareEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "evolution_id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "previous_gen_profil_id", nullable = false)
    private GenerationProfileEntity previousPokemon;

    @OneToOne
    @JoinColumn (name = "next_gen_profile_id", nullable = false)
    private GenerationProfileEntity nextPokemon;

    @Column (name = "evolution_rank", nullable = false)
    private Integer evolutionRank;

    @OneToMany (mappedBy = "evolution", cascade = CascadeType.ALL)
    private List<EvolveConditionEntity> conditions;

}
