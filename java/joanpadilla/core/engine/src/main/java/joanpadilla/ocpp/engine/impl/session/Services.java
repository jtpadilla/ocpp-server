package joanpadilla.ocpp.engine.impl.session;

import joanpadilla.ocpp.engine.EngineProviders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class Services {

    final static private Logger logger = LoggerFactory.getLogger(Services.class);

    final private EngineProviders providers;
    final private AtomicInteger transactionCounter;

    public Services(EngineProviders providers) {
        this.providers = providers;
        transactionCounter = new AtomicInteger(0);
    }

    public int nextTransaction() {
        return transactionCounter.incrementAndGet();
    }

}

