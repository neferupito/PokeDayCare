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
@Table (name = "EVOLUTIONS")
@NamedQueries ({
        @NamedQuery (name = "EvolutionEntity.findEvolutionByRank", query =
                "SELECT e FROM EvolutionEntity e " +
                        "WHERE e.previousPokemon.evolutionGroup = :evolution_group " +
                        "AND e.evolutionRank = :rank")
})
public class EvolutionEntity extends PokeDayCareEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "EVOL_ID", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "PREV_GEN_PROF_ID", nullable = false)
    private GenerationProfileEntity previousPokemon;

    @OneToOne
    @JoinColumn (name = "NEXT_GEN_PROF_ID", nullable = false)
    private GenerationProfileEntity nextPokemon;

    @Column (name = "EVOL_RANK", nullable = false)
    private Integer evolutionRank;

    @OneToMany (mappedBy = "evolution", cascade = CascadeType.ALL)
    private List<EvolveConditionEntity> conditions;

}
