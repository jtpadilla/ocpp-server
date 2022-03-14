package padilla.ocpp.server.simple;

import com.moandjiezana.toml.Toml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import padilla.ocpp.engine.OcppConnector;
import padilla.ocpp.engine.OcppEngine;
import padilla.ocpp.engine.OcppParameters;

import java.io.FileInputStream;

public class SimpleServer {

    final static private String APP_NAME = "Simple OCPPServer";

    final static private Logger logger = LoggerFactory.getLogger(SimpleServer.class);

    static private boolean finishServer = false;

    public static void main(String[] args) {

        logger.info(String.format("Iniciando '%s' con JavaRuntime %s.", APP_NAME, System.getProperty("java.version")));

        try {
            // Obteniendo los parametros
            OcppParameters parameters = new Toml().read(new FileInputStream(args[0])).to(SimpleParameters.class);

            // Parametros y conector
//            OcppParameters parameters = new SimpleParameters();
            OcppConnector connector = new SimpleConnector();

            // Se lanzan el servidor
            OcppEngine.start(parameters, connector);

            // Se espera una senyal de parada para detener en servidor.
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {

                logger.info("Se ha solicitado la parada ...");

                // Se detiene el servidor
                OcppEngine.stop();

                // Se despierta el thread principal para que evalue si tiene que parar
                synchronized (SimpleServer.class) {
                    finishServer = true;
                    SimpleServer.class.notifyAll();
                }

            }));

            // El threda principal debe esperar
            logger.info("El servidor se ha iniciado correctamente y ahora el thread principal entra en espera...");

            while (!finishServer) {
                synchronized (SimpleServer.class) {
                    try {
                        SimpleServer.class.wait(5000);
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
