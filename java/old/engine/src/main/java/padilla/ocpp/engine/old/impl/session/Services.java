package padilla.ocpp.engine.old.impl.session;

import padilla.ocpp.engine.old.OcppConnector;
import padilla.ocpp.engine.old.OcppParameters;

import java.util.concurrent.atomic.AtomicInteger;

public class Services {

    final private OcppParameters parameters;
    final private OcppConnector connector;
    final private AtomicInteger transactionCounter;


    public Services(OcppParameters parameters, OcppConnector connector) {
        this.parameters = parameters;
        this.connector = connector;
        transactionCounter = new AtomicInteger(0);
    }

    public int nextTransaction() {
        return transactionCounter.incrementAndGet();
    }

    public boolean validIdTag(String idTag) {
        return connector.getIdTagList().stream().anyMatch(idTag::equals);
    }

    public int getHeartbeatSeconds() {
        return parameters.getHeartbeatSeconds();
    }

}

