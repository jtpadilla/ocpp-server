package joanpadilla.ocpp.engine.impl;

import eu.chargetime.ocpp.JSONServer;
import eu.chargetime.ocpp.ServerEvents;
import eu.chargetime.ocpp.feature.profile.ServerCoreEventHandler;
import eu.chargetime.ocpp.feature.profile.ServerCoreProfile;
import joanpadilla.ocpp.engine.EngineProviders;
import joanpadilla.ocpp.engine.impl.session.Services;
import joanpadilla.ocpp.engine.impl.session.SessionDirectory;

public class EngineImpl {

    static public EngineImpl instantiate(EngineProviders providers) {
        return new EngineImpl(providers);
    }

    final private ServerCoreEventHandler serverCoreEventHandler;
    final private ServerCoreProfile serverCoreProfile;
    final private JSONServer jsonServer;
    final private ServerEvents serverEvents;

    private EngineImpl(EngineProviders providers) {

        Services services = new Services(providers);
        SessionDirectory sessionDirectory = new SessionDirectory(services);

        serverCoreEventHandler = new SessionHandler(sessionDirectory);
        serverCoreProfile = new ServerCoreProfile(serverCoreEventHandler);
        jsonServer = new JSONServer(serverCoreProfile);
        serverEvents = new ServerHandler(sessionDirectory);
        jsonServer.open("localhost", 8887, serverEvents);
    }

    public void stop() {
        jsonServer.close();
    }

}
