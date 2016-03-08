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
public class EvolutionGroupEntity extends PokeDayCareEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "evolution_group_id", unique = true, nullable = false)
    private Long id;

    @OneToMany(mappedBy = "evolutionGroup")
    private List<EvolutionEntity> evolutions;

}
