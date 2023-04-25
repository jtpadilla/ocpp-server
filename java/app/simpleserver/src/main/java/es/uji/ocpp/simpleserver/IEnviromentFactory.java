package es.uji.ocpp.simpleserver;

import padilla.ocpp.engine.OcppConnector;
import padilla.ocpp.engine.OcppParameters;

import java.io.IOException;
import java.io.InputStream;

public interface IEnviromentFactory {
    InputStream logConfiguration() throws IOException;
    OcppConnector connector() throws IOException;
    OcppParameters parameters() throws IOException;
}
