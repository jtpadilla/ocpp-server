package joanpadilla.ocpp.engine;

import eu.chargetime.ocpp.feature.profile.ServerCoreEventHandler;
import eu.chargetime.ocpp.model.core.*;

import java.time.ZonedDateTime;
import java.util.UUID;

public class ServerCoreEventHandlerImpl implements ServerCoreEventHandler {

    @Override
    public AuthorizeConfirmation handleAuthorizeRequest(UUID uuid, AuthorizeRequest authorizeRequest) {

        System.out.println(authorizeRequest);

        // ... handle event
        IdTagInfo idTagInfo = new IdTagInfo(AuthorizationStatus.Accepted);
        idTagInfo.setExpiryDate(ZonedDateTime.now());
        idTagInfo.setParentIdTag("test");

        return new AuthorizeConfirmation(idTagInfo);
    }

    @Override
    public BootNotificationConfirmation handleBootNotificationRequest(UUID uuid, BootNotificationRequest bootNotificationRequest) {

        System.out.println(bootNotificationRequest);
        // ... handle event

        return null; // returning null means unsupported feature
    }

    @Override
    public DataTransferConfirmation handleDataTransferRequest(UUID uuid, DataTransferRequest dataTransferRequest) {

        System.out.println(dataTransferRequest);
        // ... handle event

        return null; // returning null means unsupported feature
    }

    @Override
    public HeartbeatConfirmation handleHeartbeatRequest(UUID uuid, HeartbeatRequest heartbeatRequest) {

        System.out.println(heartbeatRequest);
        // ... handle event

        return null; // returning null means unsupported feature
    }

    @Override
    public MeterValuesConfirmation handleMeterValuesRequest(UUID uuid, MeterValuesRequest meterValuesRequest) {

        System.out.println(meterValuesRequest);
        // ... handle event

        return null; // returning null means unsupported feature
    }

    @Override
    public StartTransactionConfirmation handleStartTransactionRequest(UUID uuid, StartTransactionRequest startTransactionRequest) {

        System.out.println(startTransactionRequest);
        // ... handle event

        return null; // returning null means unsupported feature
    }

    @Override
    public StatusNotificationConfirmation handleStatusNotificationRequest(UUID uuid, StatusNotificationRequest statusNotificationRequest) {

        System.out.println(statusNotificationRequest);
        // ... handle event

        return null; // returning null means unsupported feature
    }

    @Override
    public StopTransactionConfirmation handleStopTransactionRequest(UUID uuid, StopTransactionRequest stopTransactionRequest) {

        System.out.println(stopTransactionRequest);
        // ... handle event

        return null; // returning null means unsupported feature
    }

}
