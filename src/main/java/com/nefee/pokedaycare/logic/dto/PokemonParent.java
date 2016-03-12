package com.nefee.pokedaycare.logic.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PokemonParent implements Comparable<PokemonParent> {

    private String name;

    private Integer nationalId;

    private Gender gender;

    @Override
    public int compareTo(PokemonParent o) {
        return getNationalId().compareTo(o.getNationalId());
    }

}
