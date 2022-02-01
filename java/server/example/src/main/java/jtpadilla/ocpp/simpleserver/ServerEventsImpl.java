package jtpadilla.ocpp.simpleserver;

import eu.chargetime.ocpp.ServerEvents;
import eu.chargetime.ocpp.model.SessionInformation;

import java.util.UUID;

public class ServerEventsImpl implements ServerEvents {

    @Override
    public void newSession(UUID sessionIndex, SessionInformation sessionInformation) {
        // sessionIndex is used to send messages.
        System.out.println("New session " + sessionIndex + ": " + sessionInformation.getIdentifier());
    }

    @Override
    public void lostSession(UUID sessionIndex) {
        System.out.println("Session " + sessionIndex + " lost connection");
    }

}
