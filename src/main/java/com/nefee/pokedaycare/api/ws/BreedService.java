package com.nefee.pokedaycare.api.ws;

import com.google.gson.Gson;
import com.nefee.pokedaycare.config.SpringApplicationContext;
import com.nefee.pokedaycare.logging.utils.PerfomanceLog;
import com.nefee.pokedaycare.logic.exception.BreedingNotPossibleException;
import com.nefee.pokedaycare.logic.exception.PokeNotFoundException;
import com.nefee.pokedaycare.logic.manager.BreedManager;
import com.nefee.pokedaycare.logic.model.BreedCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path ("/breed")
public class BreedService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BreedService.class);

    private BreedManager breedManager;
    private Gson gson;

    @GET
    @Path ("/{name}")
    @Produces ((MediaType.TEXT_PLAIN))
    public String requestBreedCase(
            @PathParam ("name")
            String babyName) {

        Long startMillis = System.currentTimeMillis();

        load();

        BreedCase bc;

        try {
            bc = breedManager.buildBreedCase(babyName);
        } catch (PokeNotFoundException e) {
            return "Nothing found";
        } catch (BreedingNotPossibleException e) {
            return "Breeding not possible";
        }

        PerfomanceLog.logPerf(startMillis, LOGGER, "build BreedCase for " + babyName);

        return gson.toJson(bc);
    }

    private void load() {
        breedManager = (BreedManager) SpringApplicationContext.getBean("breedManager");
        gson = new Gson();
    }

}
