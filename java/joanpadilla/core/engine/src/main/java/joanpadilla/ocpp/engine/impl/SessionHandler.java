package joanpadilla.ocpp.engine.impl;

import eu.chargetime.ocpp.feature.profile.ServerCoreEventHandler;
import eu.chargetime.ocpp.model.core.*;
import joanpadilla.ocpp.engine.impl.session.Services;
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
        // Log
        logger.info(String.format("BOOT-NOTIFICATION.REQ => %s, %s", StringUtil.toString(uuid), bootNotificationRequest.toString()));

        // Se ejecuta el boot
        BootNotificationConfirmation confirmation = null;
        try {
            confirmation = sessionDirectory.getSession(uuid).boot(bootNotificationRequest);
            logger.info(String.format("BOOT-NOTIFICATION.CONF => %s, %s", StringUtil.toString(uuid), confirmation.toString()));
            return confirmation;

        } catch (SessionException e) {
            logger.error(String.format("Imposible procesar AUTHORIZE.REQ porque no existe la sesion referenaciada: %s", e.getMessage()));
            IdTagInfo idTagInfo = new IdTagInfo(AuthorizationStatus.Invalid);
            idTagInfo.setExpiryDate(ZonedDateTime.now());
            return new AuthorizeConfirmation(idTagInfo);

        }

    }

    @Override
    public AuthorizeConfirmation handleAuthorizeRequest(UUID uuid, AuthorizeRequest authorizeRequest) {
        logger.info(String.format("AUTHORIZE.REQ => %s, %s", StringUtil.toString(uuid), authorizeRequest.toString()));

        // Se ejecuta la autenticacion/autorizacion
        AuthorizeConfirmation confirmation = services.authorize(uuid, authorizeRequest);

        logger.info(String.format("AUTHORIZE.CONF => %s, %s", StringUtil.toString(uuid), confirmation.toString()));
        return confirmation;
    }

    @Override
    public HeartbeatConfirmation handleHeartbeatRequest(UUID uuid, HeartbeatRequest heartbeatRequest) {
        logger.info(String.format("HEARTBEAT.REQ => %s, %s", StringUtil.toString(uuid), heartbeatRequest.toString()));

        // Se ejecuta el boot
        HeartbeatConfirmation confirmation = services.heartbeat(uuid, heartbeatRequest);

        logger.info(String.format("HEARTBEAT.CONF => %s, %s", StringUtil.toString(uuid), confirmation.toString()));
        return confirmation;
    }

    @Override
    public StartTransactionConfirmation handleStartTransactionRequest(UUID uuid, StartTransactionRequest startTransactionRequest) {
        logger.info(String.format("START-TRANSACTION.REQ => %s, %s", StringUtil.toString(uuid), startTransactionRequest.toString()));

        // Se ejecuta el boot
        StartTransactionConfirmation confirmation = services.startTransaction(uuid, startTransactionRequest);

        logger.info(String.format("START-TRANSACTION.CONF => %s, %s", StringUtil.toString(uuid), confirmation.toString()));
        return confirmation;
    }

    @Override
    public StopTransactionConfirmation handleStopTransactionRequest(UUID uuid, StopTransactionRequest stopTransactionRequest) {
        logger.info(String.format("STOP-TRANSACTION.REQ => %s, %s", StringUtil.toString(uuid), stopTransactionRequest.toString()));

        // Se ejecuta el boot
        StopTransactionConfirmation confirmation = services.stopTransaction(uuid, stopTransactionRequest);

        logger.info(String.format("STOP-TRANSACTION.CONF => %s, %s", StringUtil.toString(uuid), confirmation.toString()));
        return confirmation;
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

}
