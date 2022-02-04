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
    public BootNotificationConfirmation handleBootNotificationRequest(UUID uuid, BootNotificationRequest request) {

        logger.info(String.format("BOOT-NOTIFICATION.REQ => %s, %s", StringUtil.toString(uuid), request.toString()));

        try {
            BootNotificationConfirmation confirmation = sessionDirectory.getSession(uuid).boot(request);
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
    public HeartbeatConfirmation handleHeartbeatRequest(UUID uuid, HeartbeatRequest request) {

        logger.info(String.format("HEARTBEAT.REQ => %s, %s", StringUtil.toString(uuid), request.toString()));

        try {
            HeartbeatConfirmation confirmation = sessionDirectory.getSession(uuid).heartbeat(request);
            logger.info(String.format("HEARTBEAT.CONF => %s, %s", StringUtil.toString(uuid), confirmation.toString()));
            return confirmation;

        } catch (SessionException e) {
            logger.error(String.format("Imposible procesar HEARTBEAT.REQ: %s", e.getMessage()));
            return new HeartbeatConfirmation(ZonedDateTime.now());

        }

    }

    @Override
    public AuthorizeConfirmation handleAuthorizeRequest(UUID uuid, AuthorizeRequest request) {

        logger.info(String.format("AUTHORIZE.REQ => %s, %s", StringUtil.toString(uuid), request.toString()));

        try {
            AuthorizeConfirmation confirmation = sessionDirectory.getSession(uuid).authorize(request);
            logger.info(String.format("AUTHORIZE.CONF => %s, %s", StringUtil.toString(uuid), confirmation.toString()));
            return confirmation;

        } catch (SessionException e) {
            logger.error(String.format("Imposible procesar AUTHORIZE.REQ: %s", e.getMessage()));
            return new AuthorizeConfirmation(new IdTagInfo(AuthorizationStatus.Invalid));

        }

    }

    @Override
    public StartTransactionConfirmation handleStartTransactionRequest(UUID uuid, StartTransactionRequest request) {

        logger.info(String.format("START-TRANSACTION.REQ => %s, %s", StringUtil.toString(uuid), request.toString()));

        try {
            StartTransactionConfirmation confirmation = sessionDirectory.getSession(uuid).startTransaction(request);
            logger.info(String.format("START-TRANSACTION.CONF => %s, %s", StringUtil.toString(uuid), confirmation.toString()));
            return confirmation;

        } catch (SessionException e) {
            logger.error(String.format("Imposible procesar START-TRANSACTION.REQ: %s", e.getMessage()));
            return new StartTransactionConfirmation(buildInvalidTagInfo(), -1);

        }

    }

    @Override
    public StopTransactionConfirmation handleStopTransactionRequest(UUID uuid, StopTransactionRequest request) {

        logger.info(String.format("STOP-TRANSACTION.REQ => %s, %s", StringUtil.toString(uuid), request.toString()));

        try {
            StopTransactionConfirmation confirmation = sessionDirectory.getSession(uuid).stopTransaction(request);
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
    public DataTransferConfirmation handleDataTransferRequest(UUID uuid, DataTransferRequest request) {

        logger.info(String.format("DATA-TRANSFER.REQ => %s, %s", StringUtil.toString(uuid), request.toString()));

        try {
            DataTransferConfirmation confirmation = sessionDirectory.getSession(uuid).dataTransfer(request);
            logger.info(String.format("DATA-TRANSFER.CONF => %s, %s", StringUtil.toString(uuid), confirmation.toString()));
            return confirmation;

        } catch (SessionException e) {
            logger.error(String.format("Imposible procesar DATA-TRANSFER.REQ: %s", e.getMessage()));
            return new DataTransferConfirmation(DataTransferStatus.Rejected);

        }

    }

    @Override
    public MeterValuesConfirmation handleMeterValuesRequest(UUID uuid, MeterValuesRequest request) {

        logger.info(String.format("METER-VALUES.REQ => %s, %s", StringUtil.toString(uuid), request.toString()));

        try {
            MeterValuesConfirmation confirmation = sessionDirectory.getSession(uuid).meterValues(request);
            logger.info(String.format("METER-VALUES.CONF => %s, %s", StringUtil.toString(uuid), confirmation.toString()));
            return confirmation;

        } catch (SessionException e) {
            logger.error(String.format("Imposible procesar METER-VALUES.REQ: %s", e.getMessage()));
            return new MeterValuesConfirmation();

        }

    }

    @Override
    public StatusNotificationConfirmation handleStatusNotificationRequest(UUID uuid, StatusNotificationRequest request) {

        logger.info(String.format("STATUS-NOTIFICATION.REQ => %s, %s", StringUtil.toString(uuid), request.toString()));

        try {
            StatusNotificationConfirmation confirmation = sessionDirectory.getSession(uuid).statusNotification(request);
            logger.info(String.format("STATUS-NOTIFICATION.CONF => %s, %s", StringUtil.toString(uuid), confirmation.toString()));
            return confirmation;

        } catch (SessionException e) {
            logger.error(String.format("Imposible procesar STATUS-NOTIFICATION.REQ: %s", e.getMessage()));
            return new StatusNotificationConfirmation();

        }

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
