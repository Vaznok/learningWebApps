package com.vetallWebapp.custom_view_framework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CustmoHttpSessionOnServerRepository {
    private static final Map<String, CustomHttpSession> sessions = new ConcurrentHashMap<>();

    public static CustomHttpSession getSession(String sessionId) {
        return  getSession(sessionId, true);
    }

    public static CustomHttpSession getSession(String sessionId, boolean canCreate) {
        CustomHttpSession session = sessions.get(sessionId);
        if(session == null && canCreate) {
            session = new CustomHttpSession();
            sessions.put(sessionId, session);
        }
        return session;
    }
}
