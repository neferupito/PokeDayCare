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
@Table (name = "EVOLVE_CONDITIONS")
public class EvolveConditionEntity extends PokeDayCareEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "EVOL_CONDITION_ID", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "EVOL_ID")
    private EvolutionEntity evolution;

    @Enumerated (EnumType.STRING)
    @Column (name = "EVOL_CONTEXT", nullable = false)
    private EvolutionContext evolutionContext;

    @Column (name = "LEVEL")
    private Integer level;

    @ManyToOne
    @JoinColumn (name = "OBJ_ID")
    private ObjectEntity object;

    @Enumerated (EnumType.STRING)
    @Column (name = "MOVE_TYPE")
    private Type moveType;

}
