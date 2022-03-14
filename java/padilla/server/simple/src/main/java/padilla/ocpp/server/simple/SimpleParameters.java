package padilla.ocpp.server.simple;

import padilla.ocpp.engine.OcppParameters;

public class SimpleParameters implements OcppParameters {

    private String serverAddress;
    private int serverPort;
    private int heartBeatSeconds;
    private boolean heartbeatLogDisabled;

    public SimpleParameters(String serverAddress, int serverPort, int heartBeatSeconds, boolean heartbeatLogDisabled) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.heartBeatSeconds = heartBeatSeconds;
        this.heartbeatLogDisabled = heartbeatLogDisabled;
    }

    @Override
    public String serverAddressForListen() {
        return this.serverAddress;
    }

    @Override
    public int serverPorForListen() {
        return this.serverPort;
    }

    @Override
    public int getHeartbeatSeconds() {
        return this.heartBeatSeconds;
    }

    @Override
    public boolean isHeartbeatLogDisabled() {
        return this.heartbeatLogDisabled;
    }

}
