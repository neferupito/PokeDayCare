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
@Table(name = "pokemons")
@NamedQueries({
        @NamedQuery(name = "PokemonEntity.findByName", query = "SELECT p FROM PokemonEntity p " +
                "WHERE p.name = :pokemon_name")
})
public class PokemonEntity extends PokeDayCareEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pokemon_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "national_id")
    @NotNull
    private String nationalId;

    @Column(name = "name")
    @NotNull
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type1_id")
    @NotNull
    private TypeEntity type1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type2_id")
    private TypeEntity type2;

    @ManyToMany
    @JoinTable(
            name = "pokemons_egg_groups",
            joinColumns = @JoinColumn(name = "pokemon_id", referencedColumnName = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "egg_group_id", referencedColumnName = "egg_group_id"))
    private List<EggGroupEntity> eggGroups;

    @Column(name = "eggcycles")
    private Integer eggCycles;

    @OneToOne(mappedBy = "pokemon")
    private EvolutionEntity evolutionEntity;

}
