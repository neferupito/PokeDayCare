package com.nefee.pokedaycare.api.ws;

import com.google.gson.Gson;
import com.nefee.pokedaycare.config.SpringApplicationContext;
import com.nefee.pokedaycare.logic.exception.PokeDayCareException;
import com.nefee.pokedaycare.logic.manager.PokemonManager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/pokemon")
public class PokemonService {

    private PokemonManager pokemonManager;
    private Gson gson;

    @GET
    @Path("/{name}")
    @Produces((MediaType.APPLICATION_JSON))
    public String getInfos(
            @PathParam("name") String name) {
        load();
        try {
            return gson.toJson(pokemonManager.findByName(name));
        } catch (PokeDayCareException e) {
            e.printStackTrace();
            return "Nothing found";
        }

    }

    private void load() {
        pokemonManager = (PokemonManager) SpringApplicationContext.getBean("pokemonManager");
        gson = new Gson();
    }

}
