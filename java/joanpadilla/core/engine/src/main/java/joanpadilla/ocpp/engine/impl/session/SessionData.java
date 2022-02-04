package joanpadilla.ocpp.engine.impl.session;

import eu.chargetime.ocpp.model.core.AuthorizationStatus;
import eu.chargetime.ocpp.model.core.IdTagInfo;

import java.time.ZonedDateTime;
import java.util.Optional;

public class SessionData {

    final private ZonedDateTime creation;
    private ZonedDateTime touched;

    private String idTag;

    public SessionData() {
        this.creation = ZonedDateTime.now();
        this.touched = creation;
    }

    synchronized public void setIdTag(String idTag) {
        this.touched = ZonedDateTime.now();
        this.idTag = idTag;
    }

    public Optional<IdTagInfo> buildTagInfo() {
        if (idTag == null) {
            return Optional.empty();
        } else {
            IdTagInfo idTagInfo = new IdTagInfo(AuthorizationStatus.Accepted);
            idTagInfo.setExpiryDate(touched);
            return Optional.of(idTagInfo);
        }
    }

    public void clean() {

    }

}
