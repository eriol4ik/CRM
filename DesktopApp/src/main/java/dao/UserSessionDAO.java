package dao;

import entity.UserSession;

public interface UserSessionDAO extends DAO<UserSession, String> {
    void createOrUpdate(UserSession session);
}
