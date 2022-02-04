package joanpadilla.ocpp.engine.impl.session;

import eu.chargetime.ocpp.model.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.util.UUID;

public class Services {

    final static private Logger logger = LoggerFactory.getLogger(Services.class);

    final private SessionDirectory sessionDirectory;

    public Services(SessionDirectory sessionDirectory) {
        this.sessionDirectory = sessionDirectory;
    }

    public BootNotificationConfirmation boot(UUID uuid, BootNotificationRequest bootNotificationRequest) {
        return new BootNotificationConfirmation(ZonedDateTime.now(), 10, RegistrationStatus.Accepted);
    }

    public AuthorizeConfirmation authorize(UUID uuid, AuthorizeRequest authorizeRequest) {
        try {
            SessionData sessionData = sessionDirectory.getSession(uuid);
            // Se le asigna el Tag a la sesion
            // TODO: De momento no se tiene en cuenta si ya estaba informado el tag..
            IdTagInfo idTagInfo = sessionData.setIdTag(authorizeRequest.getIdTag());
            return new AuthorizeConfirmation(idTagInfo);
        } catch (SessionException e) {
            logger.error(String.format("Imposible procesar AUTHORIZE.REQ porque no existe la sesion referenaciada: %s", e.getMessage()));
            IdTagInfo idTagInfo = new IdTagInfo(AuthorizationStatus.Invalid);
            idTagInfo.setExpiryDate(ZonedDateTime.now());
            return new AuthorizeConfirmation(idTagInfo);
        }
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

