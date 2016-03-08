package com.nefee.pokedaycare.api.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/status")
public class StatusService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusService.class);

    private final String IS_ALIVE = "Poke Day Care is alive :)";

    @GET
    @Path("/isAlive")
    @Produces((MediaType.TEXT_PLAIN))
    public String isAlive() {
        LOGGER.info(IS_ALIVE);
        return IS_ALIVE;
    }

}
