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
@Table (name = "POKEMONS")
@NamedQueries ({
        @NamedQuery (name = "PokemonEntity.findByName", query =
                "SELECT p FROM PokemonEntity p " +
                        "WHERE p.name = :pokemon_name"),
        @NamedQuery (name = "PokemonEntity.findAll", query =
                "SELECT p FROM PokemonEntity p")
        ,
        @NamedQuery (name = "PokemonEntity.findAllPokemonsByEggGroup", query =
                "SELECT p FROM PokemonEntity p JOIN p.eggGroups e " +
                        "WHERE e = :egg_group")
})
public class PokemonEntity extends PokeDayCareEntity {

    @Id
    @Column (name = "NATIONAL_ID", unique = true, nullable = false)
    private Integer nationalId;

    @Column (name = "NAME", nullable = false)
    private String name;

    @Column (name = "TYPE1", nullable = false)
    @Enumerated (EnumType.STRING)
    private Type type1;

    @Column (name = "TYPE2")
    @Enumerated (EnumType.STRING)
    private Type type2;

    @Column (name = "PERCENT_MALE")
    private Double percentMale;

    @Column (name = "PERCENT_FEMALE")
    private Double percentFemale;

    @ElementCollection (targetClass = EggGroup.class)
    @CollectionTable (name = "EGG_GROUPS",
            joinColumns = @JoinColumn (name = "POKEMON_ID"))
    @Column (name = "EGG_GROUP_ID")
    private List<EggGroup> eggGroups;

    @Column (name = "EGGCYCLES")
    private Integer eggCycles;

    @OneToOne (mappedBy = "pokemon")
    private EvolutionEntity evolution;

}
