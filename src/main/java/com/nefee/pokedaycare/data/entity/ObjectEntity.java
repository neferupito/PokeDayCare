package com.nefee.pokedaycare.data.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "OBJECTS")
public class ObjectEntity extends PokeDayCareEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "OBJ_ID", unique = true, nullable = false)
    private Long id;

    @Column (name = "NAME")
    private String name;

}
