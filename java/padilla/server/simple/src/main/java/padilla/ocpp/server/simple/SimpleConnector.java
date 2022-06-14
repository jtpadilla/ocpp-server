package padilla.ocpp.server.simple;

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

//    @Override
//    public List<String> getIdTagList() {
//        return List.of("4EF20EC9", "2EF20FC9");
//    }

}
