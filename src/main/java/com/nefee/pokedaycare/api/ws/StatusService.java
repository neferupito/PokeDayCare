package com.nefee.pokedaycare.api.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
