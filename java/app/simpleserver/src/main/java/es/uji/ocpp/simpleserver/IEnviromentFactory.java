package es.uji.ocpp.simpleserver;

import padilla.ocpp.engine.OcppConnector;
import padilla.ocpp.engine.OcppParameters;

import java.io.IOException;

public interface IEnviromentFactory {
    OcppConnector connector() throws IOException;
    OcppParameters parameters() throws IOException;
}
