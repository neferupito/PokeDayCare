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
@Table (name = "EVOLUTION_GROUPS")
public class EvolutionGroupEntity extends PokeDayCareEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "EVOL_GROUP_ID", unique = true, nullable = false)
    private Long id;

    @OneToMany (mappedBy = "evolutionGroup")
    private List<GenerationProfileEntity> pokemons;

}
