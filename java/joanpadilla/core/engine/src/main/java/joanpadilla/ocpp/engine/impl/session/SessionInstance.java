package joanpadilla.ocpp.engine.impl.session;

import eu.chargetime.ocpp.model.core.*;

import java.time.ZonedDateTime;
import java.util.UUID;

public class SessionInstance {

    final private Services services;
    final private ZonedDateTime creation;

    private ZonedDateTime touched;
    private String idTag;

    //////////////////////////////////////////////////
    // Ciclo de vida de la sesion
    //////////////////////////////////////////////////

    public SessionInstance(Services services) {
        this.services = services;
        this.creation = ZonedDateTime.now();
        this.touched = creation;
    }

    public void lostSession() {
        this.idTag = null;
    }

    //////////////////////////////////////////////////
    // Implementacion del protocolo
    //////////////////////////////////////////////////

    synchronized public BootNotificationConfirmation boot(BootNotificationRequest request) {
        touch();
        return new BootNotificationConfirmation(ZonedDateTime.now(), 10, RegistrationStatus.Accepted);
    }

    public HeartbeatConfirmation heartbeat(UUID uuid, HeartbeatRequest heartbeatRequest) {
        touch();
        return new HeartbeatConfirmation(ZonedDateTime.now());
    }

    synchronized public AuthorizeConfirmation authorize(AuthorizeRequest authorizeRequest) {
        touch();
        this.idTag = authorizeRequest.getIdTag();
        IdTagInfo idTagInfo = buildTagInfo();
        return new AuthorizeConfirmation(idTagInfo);
    }

    public StartTransactionConfirmation startTransaction(UUID uuid, StartTransactionRequest startTransactionRequest) {
        return null;
    }

    public StopTransactionConfirmation stopTransaction(UUID uuid, StopTransactionRequest stopTransactionRequest) {
        return null;
    }

    //////////////////////////////////////////////////
    // Utilidades privadas
    //////////////////////////////////////////////////

    private void touch() {
        this.touched = ZonedDateTime.now();
    }

    synchronized public IdTagInfo setIdTag(String idTag) {

        this.touched = ZonedDateTime.now();
        this.idTag = idTag;

        IdTagInfo idTagInfo = new IdTagInfo(AuthorizationStatus.Accepted);
        idTagInfo.setExpiryDate(touched);
        return idTagInfo;

    }

    private IdTagInfo buildTagInfo() {
        IdTagInfo idTagInfo = new IdTagInfo(AuthorizationStatus.Accepted);
        idTagInfo.setExpiryDate(touched);
        return idTagInfo;
    }

}
