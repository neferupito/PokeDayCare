package com.nefee.pokedaycare.data.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "objects")
public class ObjectEntity extends PokeDayCareEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "object_id", unique = true, nullable = false)
    private Long id;

    @Column (name = "name")
    private String name;

}
