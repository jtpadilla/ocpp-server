package padilla.ocpp.engine;

import padilla.ocpp.engine.impl.EngineImpl;

public class OcppEngine {

    static private EngineImpl impl = null;

    static public void start(EngineConnector providers) {
        synchronized (OcppEngine.class) {
            if (impl != null) {
                throw new IllegalStateException("El engine ya esta en marcha!");
            }
            impl = EngineImpl.instantiate(providers);
        }
    }

    static public void stop() {
        synchronized (OcppEngine.class) {
            if (impl == null) {
                throw new IllegalStateException("El engine ya esta parado!");
            }
            try {
                impl.stop();
            } catch (Throwable t) {
                System.err.println("No se ha podido detener el engine de forma ordenada!");
            }
            impl = null;
        }
    }

}
