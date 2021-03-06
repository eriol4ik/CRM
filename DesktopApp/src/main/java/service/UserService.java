package service;

import entity.User;

public interface UserService extends Service<User, String> {
    User find(String login);
}
