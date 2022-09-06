package padilla.ocpp.engine.old.launcher;

import com.moandjiezana.toml.Toml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import padilla.ocpp.engine.old.OcppConnector;
import padilla.ocpp.engine.old.OcppEngine;
import padilla.ocpp.engine.old.OcppParameters;

import java.io.FileInputStream;
import java.util.logging.LogManager;

public class EngineLauncher {

    final static private String APP_NAME = "OCPP Engine";

    final static private Logger logger = LoggerFactory.getLogger(EngineLauncher.class);

    static private boolean finishServer = false;

    public static void main(String[] args) {

        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream(args[0]));

            logger.info(String.format("Iniciando '%s' con JavaRuntime %s.", APP_NAME, System.getProperty("java.version")));

            // Obteniendo los parametros
            OcppParameters parameters = new Toml().read(new FileInputStream(args[1])).to(SimpleParameters.class);

            // Obteniendo el conector
            OcppConnector connector = new Toml().read(new FileInputStream(args[2])).to(SimpleConnector.class);

            // Se lanzan el servidor
            OcppEngine.start(parameters, connector);

            // Se espera una senyal de parada para detener en servidor.
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {

                logger.info("Se ha solicitado la parada ...");

                // Se detiene el servidor
                OcppEngine.stop();

                // Se despierta el thread principal para que evalue si tiene que parar
                synchronized (EngineLauncher.class) {
                    finishServer = true;
                    EngineLauncher.class.notifyAll();
                }

            }));

            // El threda principal debe esperar
            logger.info("El servidor se ha iniciado correctamente y ahora el thread principal entra en espera...");

            while (!finishServer) {
                synchronized (EngineLauncher.class) {
                    try {
                        EngineLauncher.class.wait(5000);
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
