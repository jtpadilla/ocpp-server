package joanpadilla.ocpp.engine.impl.session;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SessionDirectory {

    final private Services services;
    final private Map<UUID, SessionInstance> map = new ConcurrentHashMap<>();

    public SessionDirectory(Services services) {
        this.services = services;
    }

    synchronized public SessionInstance addSession(UUID session) throws SessionException {
        if (map.containsKey(session)) {
            throw new SessionException(String.format("No se puede crear una sesion porque ya existe una sesion con este ID: %s", session.toString()));
        }
        return map.put(session, new SessionInstance(services));
    }

    synchronized public SessionInstance getSession(UUID session) throws SessionException {
        SessionInstance sessionData = map.get(session);
        if (sessionData == null) {
            throw new SessionException(String.format("No existe una sesion para este ID: %s", session.toString()));
        }
        return sessionData;
    }

    synchronized public void removeSession(UUID session) throws SessionException {
        SessionInstance sessionData = map.get(session);
        if (sessionData != null) {
            sessionData.lostSession();
        } else {
            throw new SessionException(String.format("No se puede suprimir una sesion porque no existe una sesion con este ID: %s", session));
        }
    }

}
