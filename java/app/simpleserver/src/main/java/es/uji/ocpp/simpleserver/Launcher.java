package es.uji.ocpp.simpleserver;

import es.uji.ocpp.simpleserver.env.Enviroment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import padilla.ocpp.engine.OcppConnector;
import padilla.ocpp.engine.OcppEngine;
import padilla.ocpp.engine.OcppParameters;

import java.util.logging.LogManager;

public class Launcher {

    final static private String APP_NAME = "OCPP Engine";

    final static private Logger logger = LoggerFactory.getLogger(Launcher.class);

    static private boolean finishServer = false;

    public static void main(String[] args) {

        try {
            // Enviroment
            final IEnviromentFactory factory = Enviroment.getFactory(args);

            // Log
            LogManager.getLogManager().readConfiguration(factory.logConfiguration());
            final OcppParameters parameters = factory.parameters();
            final OcppConnector connector = factory.connector();

            // Info java
            logger.info(String.format("Iniciando '%s' con JavaRuntime %s.", APP_NAME, System.getProperty("java.version")));

            // Se lanzan el servidor
            OcppEngine.start(parameters, connector);

            // Se espera una senyal de parada para detener en servidor.
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {

                logger.info("Se ha solicitado la parada ...");

                // Se detiene el servidor
                OcppEngine.stop();

                // Se despierta el thread principal para que evalue si tiene que parar
                synchronized (Launcher.class) {
                    finishServer = true;
                    Launcher.class.notifyAll();
                }

            }));

            // El threda principal debe esperar
            logger.info("El servidor se ha iniciado correctamente y ahora el thread principal entra en espera...");

            while (!finishServer) {
                synchronized (Launcher.class) {
                    try {
                        Launcher.class.wait(5000);
                    } catch (InterruptedException e) {
                    }
                }
            }

            logger.info("Este thread principal se detendra ahora.");

            // La parada ha sido ordenada
            System.exit(0);

        } catch (Throwable ex) {
            logger.error("Se ha producido un error inesperado en el arranque del servidor !!!", ex);
            System.exit(1);
        }

    }

}
