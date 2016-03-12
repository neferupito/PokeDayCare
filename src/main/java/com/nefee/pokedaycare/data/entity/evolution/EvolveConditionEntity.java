package com.nefee.pokedaycare.data.entity.evolution;

import com.nefee.pokedaycare.data.entity.ObjectEntity;
import com.nefee.pokedaycare.data.entity.PokeDayCareEntity;
import com.nefee.pokedaycare.data.entity.Type;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "evolve_conditions")
public class EvolveConditionEntity extends PokeDayCareEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "evolve_condition_id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "evolution_id")
    private EvolutionEntity evolution;

    @Enumerated (EnumType.STRING)
    @Column (name = "evolution_context", nullable = false)
    private EvolutionContext evolutionContext;

    @Column (name = "level")
    private Integer level;

    @ManyToOne
    @JoinColumn (name = "object_id")
    private ObjectEntity object;

    @Enumerated (EnumType.STRING)
    @Column (name = "move_type")
    private Type moveType;

}
