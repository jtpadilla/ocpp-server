package padilla.ocpp.engine.impl.session;

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
        return new BootNotificationConfirmation(
                ZonedDateTime.now(),
                services.getHeartbeatSeconds(),
                RegistrationStatus.Accepted
        );
    }

    public HeartbeatConfirmation heartbeat(HeartbeatRequest heartbeatRequest) {
        touch();
        return new HeartbeatConfirmation(ZonedDateTime.now());
    }

    synchronized public AuthorizeConfirmation authorize(AuthorizeRequest authorizeRequest) {
        touch();
        idTag = authorizeRequest.getIdTag();
        if (services.validIdTag(idTag)) {
            return new AuthorizeConfirmation(buildAcceptedTagInfo());
        } else {
            return new AuthorizeConfirmation(buildInvalidTagInfo());
        }
    }

    public StartTransactionConfirmation startTransaction(StartTransactionRequest request) {
        touch();
        return new StartTransactionConfirmation(
                buildAcceptedTagInfo(),
                services.nextTransaction()
        );
    }

    public StopTransactionConfirmation stopTransaction(StopTransactionRequest request) {
        touch();
        StopTransactionConfirmation confirmation = new StopTransactionConfirmation();
        confirmation.setIdTagInfo(buildAcceptedTagInfo());
        return confirmation;
    }

    public DataTransferConfirmation dataTransfer(DataTransferRequest request) {
        touch();
        return new DataTransferConfirmation(DataTransferStatus.Accepted);
    }

    public MeterValuesConfirmation meterValues(MeterValuesRequest meterValuesRequest) {
        touch();
        return new MeterValuesConfirmation();
    }

    public StatusNotificationConfirmation statusNotification(StatusNotificationRequest request) {
        touch();
        return new StatusNotificationConfirmation();
    }

    //////////////////////////////////////////////////
    // Utilidades privadas
    //////////////////////////////////////////////////

    private void touch() {
        this.touched = ZonedDateTime.now();
    }

    private IdTagInfo buildAcceptedTagInfo() {
        IdTagInfo idTagInfo = new IdTagInfo(AuthorizationStatus.Accepted);
        idTagInfo.setExpiryDate(touched);
        return idTagInfo;
    }

    private IdTagInfo buildInvalidTagInfo() {
        IdTagInfo idTagInfo = new IdTagInfo(AuthorizationStatus.Invalid);
        return idTagInfo;
    }

}
