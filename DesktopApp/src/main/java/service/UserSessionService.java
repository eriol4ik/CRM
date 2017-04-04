package service;

import entity.UserSession;

public interface UserSessionService extends Service<UserSession, String> {
    void createOrUpdate(UserSession session);

    UserSession restoreSession();
    UserSession writeToResource(String userId);
}
