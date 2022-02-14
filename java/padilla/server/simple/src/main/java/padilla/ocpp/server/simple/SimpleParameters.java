package padilla.ocpp.server.simple;

import padilla.ocpp.engine.OcppParameters;

public class SimpleParameters implements OcppParameters {

    @Override
    public String serverAddressForListen() {
        return "150.128.120.133";
    }

    @Override
    public int serverPorForListen() {
        return 8887;
    }

    @Override
    public int getHeartbeatSeconds() {
        return 60;
    }

    @Override
    public boolean isHeartbeatLogDisabled() {
        return false;
    }

}
