package padilla.ocpp.server.simple;

import padilla.ocpp.engine.OcppParameters;

public class SimpleParameters implements OcppParameters {

    @Override
    public int getHeartbeatSeconds() {
        return 60;
    }

    @Override
    public boolean isHeartbeatLogDisabled() {
        return false;
    }

}
