package padilla.ocpp.engine.old;

public interface OcppParameters {
    String serverAddressForListen();
    int serverPorForListen();
    int getHeartbeatSeconds();
    boolean isHeartbeatLogDisabled();
}
