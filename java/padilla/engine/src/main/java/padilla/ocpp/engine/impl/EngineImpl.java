package padilla.ocpp.engine.impl;

import eu.chargetime.ocpp.JSONServer;
import eu.chargetime.ocpp.ServerEvents;
import eu.chargetime.ocpp.feature.profile.ServerCoreEventHandler;
import eu.chargetime.ocpp.feature.profile.ServerCoreProfile;
import padilla.ocpp.engine.OcppConnector;
import padilla.ocpp.engine.OcppParameters;
import padilla.ocpp.engine.impl.session.Services;
import padilla.ocpp.engine.impl.session.SessionDirectory;

public class EngineImpl {

    static public EngineImpl instantiate(OcppParameters parameters, OcppConnector connector) {
        return new EngineImpl(parameters, connector);
    }

    final private ServerCoreEventHandler serverCoreEventHandler;
    final private ServerCoreProfile serverCoreProfile;
    final private JSONServer jsonServer;
    final private ServerEvents serverEvents;

    private EngineImpl(OcppParameters parameters, OcppConnector connector) {

        Services services = new Services(parameters, connector);
        SessionDirectory sessionDirectory = new SessionDirectory(services);

        serverCoreEventHandler = new SessionHandler(parameters, sessionDirectory);
        serverCoreProfile = new ServerCoreProfile(serverCoreEventHandler);
        jsonServer = new JSONServer(serverCoreProfile);
        serverEvents = new ServerHandler(sessionDirectory);
        jsonServer.open("localhost", 8887, serverEvents);
    }

    public void stop() {
        jsonServer.close();
    }

}
