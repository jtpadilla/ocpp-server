package joanpadilla.ocpp.engine.impl.session;

import eu.chargetime.ocpp.model.core.AuthorizationStatus;
import eu.chargetime.ocpp.model.core.IdTagInfo;

import java.time.ZonedDateTime;

public class SessionData {

    final private ZonedDateTime creation;
    private ZonedDateTime touched;

    private String idTag;

    public SessionData() {
        this.creation = ZonedDateTime.now();
        this.touched = creation;
    }

    synchronized public IdTagInfo setIdTag(String idTag) {

        this.touched = ZonedDateTime.now();
        this.idTag = idTag;

        IdTagInfo idTagInfo = new IdTagInfo(AuthorizationStatus.Accepted);
        idTagInfo.setExpiryDate(touched);
        return idTagInfo;

    }

    public IdTagInfo buildTagInfo() {
        IdTagInfo idTagInfo = new IdTagInfo(AuthorizationStatus.Accepted);
        idTagInfo.setExpiryDate(touched);
        return idTagInfo;
    }

    public void clean() {
        this.idTag = null;
    }

}
