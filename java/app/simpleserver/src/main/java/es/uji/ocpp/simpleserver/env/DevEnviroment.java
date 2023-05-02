package es.uji.ocpp.simpleserver.env;

import com.moandjiezana.toml.Toml;
import es.uji.ocpp.simpleserver.IEnviromentFactory;
import es.uji.ocpp.simpleserver.SimpleConnector;
import es.uji.ocpp.simpleserver.SimpleParameters;
import padilla.ocpp.engine.OcppConnector;
import padilla.ocpp.engine.OcppParameters;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DevEnviroment implements IEnviromentFactory {

    String LOG_CONFIGURATION_STR = """
handlers = java.util.logging.FileHandler, java.util.logging.ConsoleHandler
java.util.logging.SimpleFormatter.format=[%1$tF %1$tT] [%4$-7s] %5$s %6$s%n

java.util.logging.ConsoleHandler.level = ALL
java.util.logging.ConsoleHandler.formatter = java.util.logging.SimpleFormatter
    """;

    String PARAMETERS_STR = """
serverAddress = "10.6.0.15"
serverPort = 8887
heartBeatSeconds = 60
heartbeatLogDisabled = false
    """;

    String PARAMETERS_STR_JUAN = """
serverAddress = "150.128.120.133"
serverPort = 8887
heartBeatSeconds = 60
heartbeatLogDisabled = false
    """;

    String PARAMETERS_STR_SOTANO = """
serverAddress = "10.6.0.15"
serverPort = 8887
heartBeatSeconds = 60
heartbeatLogDisabled = false
    """;

    String PARAMETERS_STR_OLD = """
serverAddress = "150.128.50.29"
serverPort = 8887
heartBeatSeconds = 60
heartbeatLogDisabled = false
    """;

    String CONNECTOR_STR = """
            idTags = [
                "4EF20EC9",
                "2EF20FC9",
                "FEEF0EC9",
                "1E2C16C9",
                "CEEC0EC9",
                "7E3723C9",
                "4EF60FC9",
                "6EB601C9",
                "DEFFFDC8",
                "EE2D16C9",
                "0E970FC9",
                "7EA921C9",
                "4EB325C9",
                "8E0A18C9",
                "DEFAFDC8",
                "64D44563",
                "141F3463",
                "64AF2763",
                "24343463",
                "C4BD1B63",
                "849B0063",
                "D4485563",
                "74240163",
                "74D00663",
                "C4CD3863",
                "74B20663",
                "777B77B4", # Tarjeta Moises (Monrabal)
                "EA3E0D5D", # Reloj Antonio (Monrabal)
                "ACE4555F", # Tarjeta de rcapdevi (Monrabal)
                "FC89D215", # Tarjeta de edlopez (Monrabal)
                "FE903000", # Tadeo
                "BE6024B2", # Juan
                "6E4E18C9"  # Alejandro
            ]
                """;

    final private String[] args;

    public DevEnviroment(String[] args) {
        this.args = args;
    }

    @Override
    public InputStream logConfiguration() throws IOException {
        return new ByteArrayInputStream(LOG_CONFIGURATION_STR.getBytes());
    }

    @Override
    public OcppParameters parameters() throws IOException {
        return new Toml().read(new ByteArrayInputStream(PARAMETERS_STR.getBytes())).to(SimpleParameters.class);
    }

    @Override
    public OcppConnector connector() throws IOException {
        return new Toml().read(new ByteArrayInputStream(CONNECTOR_STR.getBytes())).to(SimpleConnector.class);
    }

}
