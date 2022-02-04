package joanpadilla.ocpp.engine.impl.session;

import joanpadilla.ocpp.engine.EngineProviders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Services {

    final static private Logger logger = LoggerFactory.getLogger(Services.class);

    final private EngineProviders providers;

    public Services(EngineProviders providers) {
        this.providers = providers;
    }

}

