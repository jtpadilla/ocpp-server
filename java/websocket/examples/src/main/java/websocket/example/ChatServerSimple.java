package websocket.example;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

/**
 * A simple WebSocketServer implementation. Keeps track of a "chatroom".
 */
public class ChatServerSimple {

    public static void main(String[] args) throws InterruptedException, IOException {

        WebSocketServer webSocketServer = new ChatServer(8887);
        webSocketServer.start();
        System.out.println("websocket.example.ChatServer started on port: " + webSocketServer.getPort());

        BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String in = sysin.readLine();
            webSocketServer.broadcast(in);
            if (in.equals("exit")) {
                webSocketServer.stop(1000);
                break;
            }
        }
    }

}
