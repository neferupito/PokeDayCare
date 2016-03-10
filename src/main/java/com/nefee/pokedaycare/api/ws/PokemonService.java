package com.nefee.pokedaycare.api.ws;

import com.google.gson.Gson;
import com.nefee.pokedaycare.config.SpringApplicationContext;
import com.nefee.pokedaycare.logging.utils.PerfomanceLog;
import com.nefee.pokedaycare.logic.manager.PokemonManager;
import com.nefee.pokedaycare.logic.model.Pokemon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/pokemon")
public class PokemonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonService.class);

    private PokemonManager pokemonManager;
    private Gson gson;

    @GET
    @Path("/{nationalId}")
    @Produces((MediaType.APPLICATION_JSON))
    public String getInfos(
            @PathParam("nationalId")
            Integer nationalId) {

        Long startMillis = System.currentTimeMillis();

        load();

        Optional<Pokemon> optional = pokemonManager.findByNationalId(nationalId);

        if (optional.isPresent()) {
            String json = gson.toJson(optional.get());
            PerfomanceLog.logPerf(startMillis, LOGGER, "find Pokemon with id " + nationalId);
            return json;
        } else {
            return "No Pokemon found";
        }

    }

    private void load() {
        pokemonManager = (PokemonManager) SpringApplicationContext.getBean("pokemonManager");
        gson = new Gson();
    }

}
