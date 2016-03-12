package com.nefee.pokedaycare.logic.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class BreedCase {

    private Integer generation;

    private List<Baby> babyGoals;

    private List<PotentialPartners> potentialPartners;

}
