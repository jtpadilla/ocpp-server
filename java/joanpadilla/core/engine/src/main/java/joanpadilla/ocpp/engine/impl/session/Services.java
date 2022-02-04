package joanpadilla.ocpp.engine.impl.session;

import eu.chargetime.ocpp.model.core.*;

import java.time.ZonedDateTime;
import java.util.UUID;

public class Services {

    final private SessionDirectory sessionDirectory;

    public Services(SessionDirectory sessionDirectory) {
        this.sessionDirectory = sessionDirectory;
    }

    public AuthorizeConfirmation authorize(UUID uuid, AuthorizeRequest authorizeRequest) {
        IdTagInfo idTagInfo = new IdTagInfo(AuthorizationStatus.Accepted);
        idTagInfo.setExpiryDate(ZonedDateTime.now());
        return new AuthorizeConfirmation(idTagInfo);
    }

    public BootNotificationConfirmation boot(UUID uuid, BootNotificationRequest bootNotificationRequest) {
        return new BootNotificationConfirmation(ZonedDateTime.now(), 120, RegistrationStatus.Accepted);
    }

    public HeartbeatConfirmation heartbeat(UUID uuid, HeartbeatRequest heartbeatRequest) {
        return new HeartbeatConfirmation(ZonedDateTime.now());
    }

    public StartTransactionConfirmation startTransaction(UUID uuid, StartTransactionRequest startTransactionRequest) {
        return null; // returning null means unsupported feature
    }

    public StopTransactionConfirmation stopTransaction(UUID uuid, StopTransactionRequest stopTransactionRequest) {
        return null;
    }

}

