package websocket.example;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class shows how to add additional http header like "Origin" or "Cookie".
 * <p>
 * To see it working, start websocket.example.ServerRejectHandshakeExample and then start this example.
 */
public class CustomHeaderClientExample {

    public static void main(String[] args) throws URISyntaxException, InterruptedException {

        Map<String, String> httpHeaders = new HashMap<>();
        httpHeaders.put("Cookie", "test");
        ExampleClient c = new ExampleClient(new URI("ws://localhost:8887"), httpHeaders);

        // We expect no successful connection
        c.connectBlocking();
        httpHeaders.put("Cookie", "username=nemo");
        c = new ExampleClient(new URI("ws://localhost:8887"), httpHeaders);

        // We expect a successful connection
        c.connectBlocking();
        c.closeBlocking();
        httpHeaders.put("Access-Control-Allow-Origin", "*");
        c = new ExampleClient(new URI("ws://localhost:8887"), httpHeaders);

        // We expect no successful connection
        c.connectBlocking();
        c.closeBlocking();
        httpHeaders.clear();
        httpHeaders.put("Origin", "localhost:8887");
        httpHeaders.put("Cookie", "username=nemo");
        c = new ExampleClient(new URI("ws://localhost:8887"), httpHeaders);

        // We expect a successful connection
        c.connectBlocking();
        c.closeBlocking();
        httpHeaders.clear();
        httpHeaders.put("Origin", "localhost");
        httpHeaders.put("cookie", "username=nemo");
        c = new ExampleClient(new URI("ws://localhost:8887"), httpHeaders);

        // We expect no successful connection
        c.connectBlocking();

    }

}
