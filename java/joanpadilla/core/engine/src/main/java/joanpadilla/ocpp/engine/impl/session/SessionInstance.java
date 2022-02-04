package joanpadilla.ocpp.engine.impl.session;

import eu.chargetime.ocpp.model.core.*;

import java.time.ZonedDateTime;

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

    public HeartbeatConfirmation heartbeat(HeartbeatRequest heartbeatRequest) {
        touch();
        return new HeartbeatConfirmation(ZonedDateTime.now());
    }

    synchronized public AuthorizeConfirmation authorize(AuthorizeRequest authorizeRequest) {
        touch();
        this.idTag = authorizeRequest.getIdTag();
        return new AuthorizeConfirmation(buildTagInfo());
    }

    public StartTransactionConfirmation startTransaction(StartTransactionRequest startTransactionRequest) {
        touch();
        return new StartTransactionConfirmation(
                buildTagInfo(),
                services.nextTransaction()
        );
    }

    public StopTransactionConfirmation stopTransaction(StopTransactionRequest stopTransactionRequest) {
        touch();
        StopTransactionConfirmation confirmation = new StopTransactionConfirmation();
        confirmation.setIdTagInfo(buildTagInfo());
        return confirmation;
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
