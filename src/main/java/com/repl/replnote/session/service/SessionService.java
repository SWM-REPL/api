package com.repl.replnote.session.service;

import com.repl.replnote.session.entity.Session;
import com.repl.replnote.session.repository.SessionRepository;
import com.repl.replnote.user.dto.UserReadDTO;
import com.repl.replnote.user.entity.User;
import com.repl.replnote.user.repository.SpringDataJpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private SpringDataJpaUserRepository userRepository;

    public void saveSession(String userId, String name) {
        Session session = new Session(userId, name);
        sessionRepository.save(session);
    }

    // 세션 조회 예시
    public Session getSession(String sessionId) {
        return sessionRepository.findById(sessionId).orElse(null);
    }

    public UserReadDTO readUserData(String sessionId) {
        Session session = sessionRepository.findById(sessionId).orElse(null);
        System.out.println(session.getSessionData());

        if (session != null) {
            User user = userRepository.findById(session.getSessionData()).orElse(null);

            if (user != null) {
                return new UserReadDTO(user.getUserId(), user.getName());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    // 세션 수정 예시
    public void updateSession(Session session) {
        sessionRepository.save(session);
    }

    // 세션 삭제 예시
    public void deleteSession(String sessionId) {
        sessionRepository.deleteById(sessionId);
    }
}
