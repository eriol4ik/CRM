package dao;

import entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDAO")
public class UserDAOImpl extends DAOImpl<User, String> implements UserDAO {
    @Autowired
    private SessionFactory factory;

    @Autowired
    protected UserDAOImpl() {}

    @Override

    public User find(String login) {
        List<User> result = factory.getCurrentSession()
                    .createQuery("FROM User u WHERE u.login = :login", User.class)
                    .setParameter("login", login)
                    .list();
        return result.isEmpty() ? null : result.get(0);
    }
}
