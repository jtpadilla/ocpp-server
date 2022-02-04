package padilla.ocpp.server.simple;

import padilla.ocpp.engine.EngineConnector;

import java.util.List;

public class SimpleProvider implements EngineConnector {

    @Override
    public List<String> getIdTagList() {
        return List.of("11223344", "6677889900");
    }

}
