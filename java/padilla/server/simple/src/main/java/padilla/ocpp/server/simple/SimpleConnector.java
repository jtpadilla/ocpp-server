package padilla.ocpp.server.simple;

import padilla.ocpp.engine.OcppConnector;

import java.util.List;

public class SimpleConnector implements OcppConnector {

    @Override
    public List<String> getIdTagList() {
        return List.of("11223344", "6677889900");
    }

}
