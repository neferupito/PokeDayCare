package com.nefee.pokedaycare.logic.exception;

public class PokeNotFoundException extends PokeDayCareException {

    public PokeNotFoundException(String message) {
        super(message);
    }

    public PokeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
