package padilla.ocpp.engine.impl;

import eu.chargetime.ocpp.model.SessionInformation;
import eu.chargetime.ocpp.utilities.MoreObjects;

import java.util.UUID;

public class StringUtil {

    static public String toString(SessionInformation sessionInformation) {
        return MoreObjects.toStringHelper(sessionInformation)
                .add("identifier", sessionInformation.getIdentifier())
                .add("address", sessionInformation.getAddress())
                .add("soapToURL", sessionInformation.getSOAPtoURL())
                .toString();
    }

    static public String toString(UUID uuid) {
        return MoreObjects.toStringHelper(uuid)
            .add("uuid", uuid.toString())
            .toString();
    }

}
