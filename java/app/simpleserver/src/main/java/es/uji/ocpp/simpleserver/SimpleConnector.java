package es.uji.ocpp.simpleserver;

import padilla.ocpp.engine.OcppConnector;

import java.util.List;

public class SimpleConnector implements OcppConnector {

    private List<String> idTags;

    public SimpleConnector(List<String> idTags) {
        this.idTags = idTags;
    }

    @Override
    public List<String> getIdTagList() {
        return this.idTags;
    }

}
