package com.nefee.pokedaycare.api.ws;

import com.nefee.pokedaycare.config.SpringApplicationContext;
import com.nefee.pokedaycare.data.dao.EggGroupDao;
import com.nefee.pokedaycare.data.dao.PokemonDao;
import com.nefee.pokedaycare.data.dao.TypeDao;
import com.nefee.pokedaycare.data.entity.EggGroupEntity;
import com.nefee.pokedaycare.data.entity.PokemonEntity;
import com.nefee.pokedaycare.data.entity.TypeEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;

@Path("/status")
public class StatusService {

    private final String IS_ALIVE = "Poke Day Care is alive :)";

    @GET
    @Path("/isAlive")
    @Produces((MediaType.TEXT_PLAIN))
    public String isAlive() {
        return IS_ALIVE;
    }

}
