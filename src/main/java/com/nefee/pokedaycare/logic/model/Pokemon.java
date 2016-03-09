package com.nefee.pokedaycare.logic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

@Getter
@Setter
@Builder
public class Pokemon {

    private String name;

    private Integer nationalId;

    private String type1;

    private String type2;

}
