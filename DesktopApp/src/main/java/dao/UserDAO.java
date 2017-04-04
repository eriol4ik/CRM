package dao;

import entity.User;

public interface UserDAO extends DAO<User, String> {
    User find(String login);
}
