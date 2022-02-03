package joanpadilla.ocpp.engine.impl;

import eu.chargetime.ocpp.JSONServer;
import eu.chargetime.ocpp.ServerEvents;
import eu.chargetime.ocpp.feature.profile.ServerCoreEventHandler;
import eu.chargetime.ocpp.feature.profile.ServerCoreProfile;
import joanpadilla.ocpp.engine.EngineProviders;

public class EngineImpl {

    static public EngineImpl instantiate(EngineProviders providers) {
        return new EngineImpl(providers);
    }

    final private EngineProviders providers;

    final private ServerCoreEventHandler serverCoreEventHandler;
    final private ServerCoreProfile serverCoreProfile;
    final private JSONServer jsonServer;
    final private ServerEvents serverEvents;

    private EngineImpl(EngineProviders providers) {

        this.providers = providers;

        serverCoreEventHandler = new ServerCoreEventHandlerImpl();
        serverCoreProfile = new ServerCoreProfile(serverCoreEventHandler);
        jsonServer = new JSONServer(serverCoreProfile);
        serverEvents = new ServerEventsImpl();
        jsonServer.open("localhost", 8887, serverEvents);
    }

    public void stop() {
        jsonServer.close();
    }

}
