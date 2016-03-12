package com.nefee.pokedaycare.data.entity.generation;

import com.nefee.pokedaycare.data.entity.ObjectEntity;
import com.nefee.pokedaycare.data.entity.PokeDayCareEntity;
import com.nefee.pokedaycare.data.entity.PokemonEntity;
import com.nefee.pokedaycare.data.entity.Type;
import com.nefee.pokedaycare.data.entity.breeding.EggGroup;
import com.nefee.pokedaycare.data.entity.evolution.EvolutionEntity;
import com.nefee.pokedaycare.data.entity.evolution.EvolutionGroupEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "gen_profiles")
@NamedQueries ({
        @NamedQuery (name = "GenerationProfileEntity.findByGenerationAndNationalId", query =
                "SELECT p FROM GenerationProfileEntity p " +
                        "WHERE p.generation = :generation " +
                        "AND p.pokemon.nationalId = :national_id"),
        @NamedQuery (name = "GenerationProfileEntity.findAllByGeneration", query =
                "SELECT p FROM GenerationProfileEntity p " +
                        "WHERE generation = :generation " +
                        "ORDER BY p.pokemon.nationalId"),
        @NamedQuery (name = "GenerationProfileEntity.findAllByEggGroup", query =
                "SELECT p FROM GenerationProfileEntity p JOIN p.eggGroups e " +
                        "WHERE e = :egg_group " +
                        "AND p.generation = :generation")
})
public class GenerationProfileEntity extends PokeDayCareEntity implements Comparable<GenerationProfileEntity> {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "gen_profile_id", unique = true, nullable = false)
    private Long id;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn (name = "pokemon_id", nullable = false)
    private PokemonEntity pokemon;

    @Column (name = "gen", nullable = false)
    @Enumerated (EnumType.ORDINAL)
    private Generation generation;

    @Column (name = "type1", nullable = false)
    @Enumerated (EnumType.STRING)
    private Type type1;

    @Column (name = "type2")
    @Enumerated (EnumType.STRING)
    private Type type2;

    @Column (name = "percent_male")
    private Double percentMale;

    @Column (name = "percent_female")
    private Double percentFemale;

    @ElementCollection (targetClass = EggGroup.class)
    @CollectionTable (name = "egg_groups",
            joinColumns = @JoinColumn (name = "gen_profile_id"))
    @Column (name = "egg_group_id")
    private List<EggGroup> eggGroups;

    @Column (name = "egg_cycles")
    private Integer eggCycles;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn (name = "evolution_group_id")
    private EvolutionGroupEntity evolutionGroup;

    @ManyToOne
    @JoinColumn (name = "object_id")
    private ObjectEntity requiredObjectForEgg;

    @OneToMany (mappedBy = "previousPokemon")
    private List<EvolutionEntity> nextEvolutions;

    @OneToOne (mappedBy = "nextPokemon")
    private EvolutionEntity previousEvolution;

    public boolean hasEvolutions() {
        return evolutionGroup != null;
    }

    @Override
    public int compareTo(GenerationProfileEntity o) {
        return pokemon.getNationalId().compareTo(o.getPokemon().getNationalId());
    }
}
