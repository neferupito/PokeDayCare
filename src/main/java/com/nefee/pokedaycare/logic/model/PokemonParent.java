package com.nefee.pokedaycare.logic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PokemonParent {

    private String name;

    private Integer nationalId;

    private Gender gender;

}
