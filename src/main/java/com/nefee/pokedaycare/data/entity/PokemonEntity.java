package com.nefee.pokedaycare.data.entity;

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
@Table (name = "pokemons")
@NamedQueries ({
        @NamedQuery (name = "PokemonEntity.findByNationalId", query =
                "SELECT p FROM PokemonEntity p " +
                        "WHERE p.nationalId = :national_id"),
        @NamedQuery (name = "PokemonEntity.findByName", query =
                "SELECT p FROM PokemonEntity p " +
                        "WHERE p.name = :pokemon_name")
})
public class PokemonEntity extends PokeDayCareEntity {

    @Id
    @Column (name = "national_id", unique = true, nullable = false)
    private Integer nationalId;

    @Column (name = "name", nullable = false)
    private String name;

    @OneToMany (mappedBy = "pokemon")
    private List<GenerationProfileEntity> generationProfiles;

}
