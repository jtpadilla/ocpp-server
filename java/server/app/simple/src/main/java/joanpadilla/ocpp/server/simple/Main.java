package joanpadilla.ocpp.server.simple;

import eu.chargetime.ocpp.JSONServer;
import eu.chargetime.ocpp.ServerEvents;
import eu.chargetime.ocpp.feature.profile.ServerCoreEventHandler;
import eu.chargetime.ocpp.feature.profile.ServerCoreProfile;
import joanpadilla.ocpp.engine.ServerCoreEventHandlerImpl;
import joanpadilla.ocpp.engine.ServerEventsImpl;

public class Main {

    public static void main(String[] args) {

        System.out.println("Server!");

        ServerCoreEventHandler serverCoreEventHandler = new ServerCoreEventHandlerImpl();

        ServerCoreProfile serverCoreProfile = new ServerCoreProfile(serverCoreEventHandler);

        JSONServer jsonServer = new JSONServer(serverCoreProfile);

        ServerEvents serverEvents = new ServerEventsImpl();

        jsonServer.open("localhost", 8887, serverEvents);

    }

}
