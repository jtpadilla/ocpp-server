package joanpadilla.ocpp.engine.impl;

import eu.chargetime.ocpp.feature.profile.ServerCoreEventHandler;
import eu.chargetime.ocpp.model.core.*;
import joanpadilla.ocpp.engine.impl.session.SessionDirectory;
import joanpadilla.ocpp.engine.impl.session.SessionException;
import joanpadilla.ocpp.engine.impl.session.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.util.UUID;

public class SessionHandler implements ServerCoreEventHandler {

    final static private Logger logger = LoggerFactory.getLogger(SessionHandler.class);

    final private SessionDirectory sessionDirectory;

    public SessionHandler(SessionDirectory sessionDirectory) {
        this.sessionDirectory = sessionDirectory;
    }

    @Override
    public BootNotificationConfirmation handleBootNotificationRequest(UUID uuid, BootNotificationRequest bootNotificationRequest) {

        logger.info(String.format("BOOT-NOTIFICATION.REQ => %s, %s", StringUtil.toString(uuid), bootNotificationRequest.toString()));

        try {
            BootNotificationConfirmation confirmation = sessionDirectory.getSession(uuid).boot(bootNotificationRequest);
            logger.info(String.format("BOOT-NOTIFICATION.CONF => %s, %s", StringUtil.toString(uuid), confirmation.toString()));
            return confirmation;

        } catch (SessionException e) {
            logger.error(String.format("Imposible procesar AUTHORIZE.REQ: %s", e.getMessage()));
            return new BootNotificationConfirmation(
                    ZonedDateTime.now(),
                    60,
                    RegistrationStatus.Rejected
            );

        }

    }

    @Override
    public HeartbeatConfirmation handleHeartbeatRequest(UUID uuid, HeartbeatRequest heartbeatRequest) {

        logger.info(String.format("HEARTBEAT.REQ => %s, %s", StringUtil.toString(uuid), heartbeatRequest.toString()));

        try {
            HeartbeatConfirmation confirmation = sessionDirectory.getSession(uuid).heartbeat(heartbeatRequest);
            logger.info(String.format("HEARTBEAT.CONF => %s, %s", StringUtil.toString(uuid), confirmation.toString()));
            return confirmation;

        } catch (SessionException e) {
            logger.error(String.format("Imposible procesar HEARTBEAT.REQ: %s", e.getMessage()));
            return new HeartbeatConfirmation(ZonedDateTime.now());

        }

    }

    @Override
    public AuthorizeConfirmation handleAuthorizeRequest(UUID uuid, AuthorizeRequest authorizeRequest) {

        logger.info(String.format("AUTHORIZE.REQ => %s, %s", StringUtil.toString(uuid), authorizeRequest.toString()));

        try {
            AuthorizeConfirmation confirmation = sessionDirectory.getSession(uuid).authorize(authorizeRequest);
            logger.info(String.format("AUTHORIZE.CONF => %s, %s", StringUtil.toString(uuid), confirmation.toString()));
            return confirmation;

        } catch (SessionException e) {
            logger.error(String.format("Imposible procesar AUTHORIZE.REQ: %s", e.getMessage()));
            return new AuthorizeConfirmation(new IdTagInfo(AuthorizationStatus.Invalid));

        }

    }

    @Override
    public StartTransactionConfirmation handleStartTransactionRequest(UUID uuid, StartTransactionRequest startTransactionRequest) {

        logger.info(String.format("START-TRANSACTION.REQ => %s, %s", StringUtil.toString(uuid), startTransactionRequest.toString()));

        try {
            StartTransactionConfirmation confirmation = sessionDirectory.getSession(uuid).startTransaction(startTransactionRequest);
            logger.info(String.format("START-TRANSACTION.CONF => %s, %s", StringUtil.toString(uuid), confirmation.toString()));
            return confirmation;

        } catch (SessionException e) {
            logger.error(String.format("Imposible procesar START-TRANSACTION.REQ: %s", e.getMessage()));
            return new StartTransactionConfirmation(buildInvalidTagInfo(), -1);

        }

    }

    @Override
    public StopTransactionConfirmation handleStopTransactionRequest(UUID uuid, StopTransactionRequest stopTransactionRequest) {

        logger.info(String.format("STOP-TRANSACTION.REQ => %s, %s", StringUtil.toString(uuid), stopTransactionRequest.toString()));

        try {
            StopTransactionConfirmation confirmation = sessionDirectory.getSession(uuid).stopTransaction(stopTransactionRequest);
            logger.info(String.format("STOP-TRANSACTION.CONF => %s, %s", StringUtil.toString(uuid), confirmation.toString()));
            return confirmation;

        } catch (SessionException e) {
            logger.error(String.format("Imposible procesar STOP-TRANSACTION.REQ: %s", e.getMessage()));
            StopTransactionConfirmation confirmation = new StopTransactionConfirmation();
            confirmation.setIdTagInfo(buildInvalidTagInfo());
            return confirmation;

        }

    }

    @Override
    public DataTransferConfirmation handleDataTransferRequest(UUID uuid, DataTransferRequest dataTransferRequest) {
        System.out.println(dataTransferRequest);
        return null; // returning null means unsupported feature
    }

    @Override
    public MeterValuesConfirmation handleMeterValuesRequest(UUID uuid, MeterValuesRequest meterValuesRequest) {
        System.out.println(meterValuesRequest);
        return null; // returning null means unsupported feature
    }

    @Override
    public StatusNotificationConfirmation handleStatusNotificationRequest(UUID uuid, StatusNotificationRequest statusNotificationRequest) {
        System.out.println(statusNotificationRequest);
        return null; // returning null means unsupported feature
    }

    /////////////////////////////////////////
    // Utilidades privadas
    /////////////////////////////////////////

    private IdTagInfo buildInvalidTagInfo() {
        IdTagInfo idTagInfo = new IdTagInfo(AuthorizationStatus.Invalid);
        idTagInfo.setExpiryDate(ZonedDateTime.now());
        return idTagInfo;
    }

}
