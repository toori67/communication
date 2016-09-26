package org.iotlabs.webserver;

import org.apache.log4j.Logger;
import org.iotlabs.util.Configuration;

import java.io.IOException;

import static spark.Spark.port;
import static spark.Spark.threadPool;

class SparkInit {
    private static Logger logger = Logger.getLogger(SparkInit.class);

    SparkInit() {
        Configuration configuration;
        try {
            configuration = new Configuration("config/web_server.conf");
        } catch (IOException e) {
            logger.error("SparkInit", e);
            logger.info("Configuration not found. Run server with default settings.");
            return;
        }
        port(configuration.getInteger("port"));
        int minThread = configuration.getInteger("min_thread", 2);
        int maxThread = configuration.getInteger("max_thread", 8);
        int timeoutInMillis = configuration.getInteger("timeout", 30000);
        threadPool(maxThread, minThread, timeoutInMillis);
    }
}
