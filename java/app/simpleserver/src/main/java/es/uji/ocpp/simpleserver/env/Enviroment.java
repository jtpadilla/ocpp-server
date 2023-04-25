package es.uji.ocpp.simpleserver.env;

import es.uji.ocpp.simpleserver.IEnviromentFactory;

public class Enviroment {

    static public IEnviromentFactory getFactory(String[] args) {
        return new DevEnviroment(args);
    }

}
