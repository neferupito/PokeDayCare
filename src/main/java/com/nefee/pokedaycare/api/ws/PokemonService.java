package com.nefee.pokedaycare.api.ws;

import com.google.gson.Gson;
import com.nefee.pokedaycare.config.SpringApplicationContext;
import com.nefee.pokedaycare.logging.utils.PerfomanceLog;
import com.nefee.pokedaycare.logic.exception.PokeDayCareException;
import com.nefee.pokedaycare.logic.manager.PokemonManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/pokemon")
public class PokemonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonService.class);

    private PokemonManager pokemonManager;
    private Gson gson;

    @GET
    @Path("/{name}")
    @Produces((MediaType.APPLICATION_JSON))
    public String getInfos(
            @PathParam("name") String name) {
        Long startMillis = System.currentTimeMillis();

        load();
        try {

            String json = gson.toJson(pokemonManager.findByName(name));
            PerfomanceLog.logPerf(startMillis, LOGGER, "find Pokemon named " + name);
            return json;

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
