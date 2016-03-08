package com.nefee.pokedaycare.logging.utils;

import org.slf4j.Logger;

public class PerfomanceLog {

    public static void logPerf(Long startMillis, Logger logger, String serviceName) {
        Long endMillis = System.currentTimeMillis();
        Long resultMillis = endMillis - startMillis;
        logger.info("Took " + resultMillis + "ms to " + serviceName);
    }

}
