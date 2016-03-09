package com.nefee.pokedaycare.api.ws;

import com.nefee.pokedaycare.config.SpringApplicationContext;
import com.nefee.pokedaycare.data.dao.PokemonDao;
import com.nefee.pokedaycare.data.entity.EggGroup;
import com.nefee.pokedaycare.data.entity.PokemonEntity;
import com.nefee.pokedaycare.logging.utils.PerfomanceLog;
import com.nefee.pokedaycare.logic.manager.DatabaseManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/db")
public class DatabaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseService.class);

    private DatabaseManager databaseManager;
    private PokemonDao pokemonDao;

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

    @GET
    @Path("/test")
    public void testQuery() {

        load();

        List<PokemonEntity> result = pokemonDao.findAllPokemonsByEggGroup(EggGroup.FIELD);
        for (PokemonEntity pk : result) {
            System.out.println("===== * "+pk.getNationalId()+" * "+pk.getName());
        }


    }

    private void load() {
        databaseManager = (DatabaseManager) SpringApplicationContext.getBean("databaseManager");
        pokemonDao = (PokemonDao) SpringApplicationContext.getBean("pokemonDao");
    }

}
