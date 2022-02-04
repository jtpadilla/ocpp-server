package padilla.ocpp.engine.impl.session;

import padilla.ocpp.engine.EngineConnector;

import java.util.concurrent.atomic.AtomicInteger;

public class Services {

    final private EngineConnector connector;
    final private AtomicInteger transactionCounter;

    public Services(EngineConnector connector) {
        this.connector = connector;
        transactionCounter = new AtomicInteger(0);
    }

    public int nextTransaction() {
        return transactionCounter.incrementAndGet();
    }

    public boolean validIdTag(String idTag) {
        return connector.getIdTagList().stream().anyMatch(idTag::equals);
    }

}

