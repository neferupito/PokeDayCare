package com.nefee.pokedaycare.logic.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Baby {

    private Pokemon baby;
    private String requiredObject;

}
