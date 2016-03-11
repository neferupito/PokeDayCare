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
@Table (name = "GEN_PROFILES")
@NamedQueries ({
        @NamedQuery (name = "GenerationProfileEntity.findByGenerationAndNationalId", query =
                "SELECT p FROM GenerationProfileEntity p " +
                        "WHERE p.generation = :generation " +
                        "AND p.pokemon.nationalId = :national_id"),
        @NamedQuery (name = "GenerationProfileEntity.findAllByGeneration", query =
                "SELECT p FROM GenerationProfileEntity p " +
                        "WHERE generation = :generation"),
        @NamedQuery (name = "GenerationProfileEntity.findAllByEggGroup", query =
                "SELECT p FROM GenerationProfileEntity p JOIN p.eggGroups e " +
                        "WHERE e = :egg_group " +
                        "AND p.generation = :generation")
})
public class GenerationProfileEntity extends PokeDayCareEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "GEN_PROF_ID", unique = true, nullable = false)
    private Long id;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn (name = "PKMN_ID", nullable = false)
    private PokemonEntity pokemon;

    @Column (name = "GEN", nullable = false)
    @Enumerated (EnumType.ORDINAL)
    private Generation generation;

    @Column (name = "TYPE1", nullable = false)
    @Enumerated (EnumType.STRING)
    private Type type1;

    @Column (name = "TYPE2")
    @Enumerated (EnumType.STRING)
    private Type type2;

    @Column (name = "PRCT_MALE")
    private Double percentMale;

    @Column (name = "PRCT_FEMALE")
    private Double percentFemale;

    @ElementCollection (targetClass = EggGroup.class)
    @CollectionTable (name = "EGG_GROUPS",
            joinColumns = @JoinColumn (name = "POKEMON_ID"))
    @Column (name = "EGG_GROUP_ID")
    private List<EggGroup> eggGroups;

    @Column (name = "EGGCYCLES")
    private Integer eggCycles;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn (name = "EVOL_GROUP_ID")
    private EvolutionGroupEntity evolutionGroup;

    @ManyToOne
    @JoinColumn (name = "OBJ_ID")
    private ObjectEntity requiredObjectForEgg;

    @OneToMany (mappedBy = "previousPokemon")
    private List<EvolutionEntity> nextEvolutions;

    @OneToOne (mappedBy = "nextPokemon")
    private EvolutionEntity previousEvolution;

    public boolean hasEvolutions() {
        return evolutionGroup != null;
    }

}
