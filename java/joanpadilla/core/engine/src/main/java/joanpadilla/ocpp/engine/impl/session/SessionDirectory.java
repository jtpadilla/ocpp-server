package joanpadilla.ocpp.engine.impl.session;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SessionDirectory {

    final private Map<UUID, SessionData> map = new ConcurrentHashMap<>();

    synchronized public SessionData addSession(UUID session) throws SessionException {
        if (map.containsKey(session)) {
            throw new SessionException("No se puede crear una sesion porque ya existe una sesion con el mismo ID");
        }
        return map.put(session, new SessionData());
    }

    synchronized public SessionData getSession(UUID session) throws SessionException {
        SessionData sessionData = map.get(session);
        if (sessionData == null) {
            throw new SessionException("No existe una sesion para este ID");
        }
        return sessionData;
    }

    synchronized public void removeSession(UUID uuid) throws SessionException {
        SessionData sessionData = map.get(uuid);
        if (sessionData != null) {
            sessionData.clean();
        } else {
            throw new SessionException("No se puede suprimir una sesion porque ya existe una sesion con el mismo ID");
        }
    }

}
