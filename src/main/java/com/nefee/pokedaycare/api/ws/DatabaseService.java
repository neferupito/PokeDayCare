package com.nefee.pokedaycare.api.ws;

import com.nefee.pokedaycare.config.SpringApplicationContext;
import com.nefee.pokedaycare.logging.utils.PerfomanceLog;
import com.nefee.pokedaycare.logic.manager.DatabaseManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/db")
public class DatabaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseService.class);

    private DatabaseManager databaseManager;

    @GET
    @Path("/generate")
    @Produces((MediaType.TEXT_PLAIN))
    public String createDatabase() {
        Long startMillis = System.currentTimeMillis();

        load();
        String result = databaseManager.createDB();

        PerfomanceLog.logPerf(startMillis, LOGGER, "create Database");

        return result;
    }

    private void load() {
        databaseManager = (DatabaseManager) SpringApplicationContext.getBean("databaseManager");
    }

}
