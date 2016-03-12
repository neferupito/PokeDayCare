package com.nefee.pokedaycare.logic.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Pokemon implements Comparable<Pokemon> {

    private String name;

    private Integer nationalId;

    private String type1;

    private String type2;

    @Override
    public int compareTo(Pokemon o) {
        return getNationalId().compareTo(o.getNationalId());
    }
}
