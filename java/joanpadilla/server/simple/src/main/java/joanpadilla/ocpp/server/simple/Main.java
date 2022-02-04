package joanpadilla.ocpp.server.simple;

import joanpadilla.ocpp.engine.EngineProviders;
import joanpadilla.ocpp.engine.OcppEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    final static private String APP_NAME = "Simple OCPPServer";

    final static private Logger logger = LoggerFactory.getLogger(Main.class);

    static private boolean finishServer = false;

    public static void main(String[] args) {

        logger.info("========================================================================================================");
        logger.info(String.format("Iniciando '%s' con JavaRuntime %s.", APP_NAME, System.getProperty("java.version")));
        logger.info("========================================================================================================");

        try {

            // Engine provider pare este servidor
            EngineProviders providers = new SimpleProvider();

            // Se lanzan el servidor
            OcppEngine.start(providers);

            // Se espera una senyal de parada para detener en servidor.
            Runtime.getRuntime().addShutdownHook(new Thread() {

                public void run() {

                    logger.info("Se ha solicitado la parada ...");

                    // Se detiene el servidor
                    OcppEngine.stop();

                    // Se despierta el thread principal para que evalue si tiene que parar
                    synchronized (Main.class) {
                        finishServer = true;
                        Main.class.notifyAll();
                    }

                }

            });

            // El threda principal debe esperar
            logger.info("========================================================================================================");
            logger.info("El servidor se ha iniciado correctamente y ahora el thread principal entra en espera...");
            logger.info("========================================================================================================");

            while (!finishServer) {
                synchronized (Main.class) {
                    try {
                        Main.class.wait(5000);
                    } catch (InterruptedException e) {
                    }
                }
            }

            logger.info("========================================================================================================");
            logger.info("Este thread principal se detendra ahora.");
            logger.info("========================================================================================================");

            // La parada ha sido ordenada
            System.exit(0);

        } catch (Throwable ex) {
            logger.error("========================================================================================================");
            logger.error("Se ha producido un error inesperado en el arranque del servidor !!!", ex);
            logger.error("========================================================================================================");
            System.exit(1);
        }

    }

}