package com.nefee.pokedaycare.api.ws;

import com.google.gson.Gson;
import com.nefee.pokedaycare.config.SpringApplicationContext;
import com.nefee.pokedaycare.logging.utils.PerfomanceLog;
import com.nefee.pokedaycare.logic.manager.PokedexManager;
import com.nefee.pokedaycare.logic.dto.Pokemon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path ("/pokedex")
public class PokedexService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PokedexService.class);

    private PokedexManager pokedexManager;
    private Gson gson;

    @GET
    @Path ("/gen/{gen}")
    @Produces ((MediaType.APPLICATION_JSON))
    public String getPokedex(
            @PathParam ("gen")
            Integer gen) {

        Long startMillis = System.currentTimeMillis();

        load();

        List<Pokemon> list = pokedexManager.findAllByGeneration(gen);

        if (!list.isEmpty()) {
            String json = gson.toJson(list);
            PerfomanceLog.logPerf(startMillis, LOGGER, "find Pokedex gen " + gen);
            return json;
        } else {
            return "No Pokemon found";
        }

    }

    private void load() {
        pokedexManager = (PokedexManager) SpringApplicationContext.getBean("pokedexManager");
        gson = new Gson();
    }


}
