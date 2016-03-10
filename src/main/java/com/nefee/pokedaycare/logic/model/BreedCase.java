package com.nefee.pokedaycare.logic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class BreedCase {

    private Pokemon babyGoal;

    private List<PotentialPartners> potentialPartners;

}
