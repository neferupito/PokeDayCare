package com.nefee.pokedaycare.logic.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PotentialPartners {

    private PokemonParent partner;

    private List<PokemonParent> potentialPartners;

}
