package padilla.ocpp.engine.old.impl;

import eu.chargetime.ocpp.ServerEvents;
import eu.chargetime.ocpp.model.SessionInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import padilla.ocpp.engine.old.impl.session.SessionDirectory;
import padilla.ocpp.engine.old.impl.session.SessionException;

import java.util.UUID;

public class ServerHandler implements ServerEvents {

    final static private Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    final private SessionDirectory sessionDirectory;

    public ServerHandler(SessionDirectory sessionDirectory) {
        this.sessionDirectory = sessionDirectory;
    }

    @Override
    public void newSession(UUID sessionIndex, SessionInformation sessionInformation) {
        try {
            sessionDirectory.addSession(sessionIndex);
            logger.info(
                    String.format("SESSION-NEW => %s, %s",
                            StringUtil.toString(sessionIndex),
                            StringUtil.toString(sessionInformation)
                    )
            );
        } catch (SessionException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void lostSession(UUID sessionIndex) {
        try {
            sessionDirectory.removeSession(sessionIndex);
            logger.info(String.format("SESSION-LOST => %s", StringUtil.toString(sessionIndex)));
        } catch (SessionException e) {
            logger.error(e.getMessage());
        }
    }

}
