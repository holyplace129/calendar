package com.learn.calendar.common.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Log4j2
public class CustomWebSocketHandler implements WebSocketHandler  {

    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String deviceToken = getDeviceTokenFromSession(session);
        log.info("WebSocket connection established for deviceToken: {}", deviceToken);
        sessions.put(deviceToken, session);
        log.info("Current sessions: {}", sessions);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 세션 연결 종료되면 제거
        sessions.remove(session.getId());
        System.out.println("WebSocket 연결 종료: " + session.getId());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public String getDeviceTokenFromSession(WebSocketSession session) {
        String query = session.getUri().getQuery(); // deviceToken=abc
        log.info("Query String: {}", query);
        return query.split("=")[1];
    }

    // 특정 deviceToken에 해당하는 세션 반환
    public WebSocketSession getSessionByDeviceToken(String deviceToken) {
        return sessions.get(deviceToken);  // deviceToken으로 세션 조회
    }

    // 특정 세션에게 메시지 보내기
    public void sendMessageToSession(String sessionId, String message) throws IOException {
        WebSocketSession session = sessions.get(sessionId);
        if (session != null && session.isOpen()) {
            session.sendMessage(new TextMessage(message));
        }
    }

    // 모든 세션에게 메시지 보내기
    public void broadcastMessage(String message) throws IOException {
        for (WebSocketSession session : sessions.values()) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(message));
            }
        }
    }

    public Set<String> getSessionIds() {
        return sessions.keySet();
    }
}
