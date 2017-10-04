package com.vetallWebapp.chat;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint(value = "/websocket/chat")
public class ChatAnnotation {
    private static final String GUEST_PREFIX = "Guest";
    private static final AtomicInteger connectionIds  = new AtomicInteger(0);
    private static final Set<ChatAnnotation> connections = new CopyOnWriteArraySet<>();

    private final String nickname;
    private Session session;

    public ChatAnnotation() {
        nickname = GUEST_PREFIX + connectionIds.getAndIncrement();
    }

    @OnOpen
    public void start (Session session, EndpointConfig endpointConfig) {
        this.session = session;
        connections.add(this);
        String message = String.format("* %s %s", nickname, "had joined.");
        broadcast(message);
    }

    @OnClose
    public void end() {
        connections.remove(this);
        String message = String.format("* %s %s", nickname, "had disconnected.");
        broadcast(message);
    }

    public static void broadcast(String msg) {
        for (ChatAnnotation client: connections) {
            try {
                synchronized (client) {
                    client.session.getBasicRemote().sendText(msg);
                }
            } catch (IOException e) {
                connections.remove(client);
                try {
                    client.session.close();
                } catch (IOException e1) {/*IGNORE*/}
                String message = String.format("* %s %s", client.nickname, "had disconnected.");
                broadcast(message);
            }

        }
    }
}
