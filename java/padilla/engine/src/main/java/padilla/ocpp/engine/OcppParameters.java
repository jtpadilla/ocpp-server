package padilla.ocpp.engine;

public interface OcppParameters {
    String serverAddressForListen();
    int serverPorForListen();
    int getHeartbeatSeconds();
    boolean isHeartbeatLogDisabled();
}
