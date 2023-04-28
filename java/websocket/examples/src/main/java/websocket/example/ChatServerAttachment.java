package websocket.example;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

/**
 * A simple WebSocketServer implementation. Keeps track of a "chatroom".
 * <p>
 * Shows how to use the attachment for a WebSocket. This example just uses a simple integer as ID.
 * Setting an attachment also works in the WebSocketClient
 */
public class ChatServerAttachment extends WebSocketServer {

    Integer index = 0;

    public ChatServerAttachment(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
    }

    public ChatServerAttachment(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {

        conn.setAttachment(index); //Set the attachment to the current index
        index++;

        // Get the attachment of this connection as Integer
        System.out.println(
                conn.getRemoteSocketAddress().getAddress().getHostAddress() +
                " entered the room! ID: " +
                conn.<Integer>getAttachment()
        );

    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        // Get the attachment of this connection as Integer
        System.out.println(conn + " has left the room! ID: " + conn.<Integer>getAttachment());
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println(conn + ": " + message);
    }

    @Override
    public void onMessage(WebSocket conn, ByteBuffer message) {
        System.out.println(conn + ": " + message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
        if (conn != null) {
            // some errors like port binding failed may not be assignable to a specific websocket
        }
    }

    @Override
    public void onStart() {
        System.out.println("Server started!");
    }

}
