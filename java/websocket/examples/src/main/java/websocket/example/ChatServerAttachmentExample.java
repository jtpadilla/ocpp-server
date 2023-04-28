package websocket.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A simple WebSocketServer implementation. Keeps track of a "chatroom".
 * <p>
 * Shows how to use the attachment for a WebSocket. This example just uses a simple integer as ID.
 * Setting an attachment also works in the WebSocketClient
 */
public class ChatServerAttachmentExample {

    Integer index = 0;

    public static void main(String[] args) throws InterruptedException, IOException {
        int port = 8887; // 843 flash policy port
        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception ex) {
        }
        ChatServerAttachment s = new ChatServerAttachment(port);
        s.start();
        System.out.println("websocket.example.ChatServer started on port: " + s.getPort());

        BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String in = sysin.readLine();
            s.broadcast(in);
            if (in.equals("exit")) {
                s.stop(1000);
                break;
            }
        }
    }

}
