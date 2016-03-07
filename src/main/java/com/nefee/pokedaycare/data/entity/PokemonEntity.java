package com.nefee.pokedaycare.data.entity;

import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @Column(name = "name")
    private String name;

    @Column(name = "birth")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime birth;

}
