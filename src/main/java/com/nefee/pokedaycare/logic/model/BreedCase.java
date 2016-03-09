package com.nefee.pokedaycare.logic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BreedCase {

    private Pokemon babyGoal;

    private NormalCase normalCase;

    private DittoCase dittoCase;

}
