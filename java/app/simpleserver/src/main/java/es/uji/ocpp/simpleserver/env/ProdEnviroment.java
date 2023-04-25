package es.uji.ocpp.simpleserver.env;

import com.moandjiezana.toml.Toml;
import es.uji.ocpp.simpleserver.IEnviromentFactory;
import es.uji.ocpp.simpleserver.SimpleConnector;
import es.uji.ocpp.simpleserver.SimpleParameters;
import padilla.ocpp.engine.OcppConnector;
import padilla.ocpp.engine.OcppParameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ProdEnviroment implements IEnviromentFactory {

    final private String[] args;

    public ProdEnviroment(String[] args) {
        this.args = args;
    }

    @Override
    public InputStream logConfiguration() throws IOException {
        return new FileInputStream(args[0]);
    }

    @Override
    public OcppParameters parameters() throws IOException {
        return new Toml().read(new FileInputStream(args[1])).to(SimpleParameters.class);
    }

    @Override
    public OcppConnector connector() throws IOException {
        return new Toml().read(new FileInputStream(args[2])).to(SimpleConnector.class);
    }

}
