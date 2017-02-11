package TestDAO;

import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import entity.Employee;
import entity.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UserDAOTest {
    @Test
    public void testRead(){
        UserDAO userDAO = new UserDAOImpl();
        User user = userDAO.read(new Long("1"));

        assertNotNull("Проверка чтения первого пользователя", user);
    }

    @Test
    public void testFailRead(){
        UserDAO userDAO = new UserDAOImpl();
        User user = userDAO.read(new Long("1000"));

        assertNull("Проверка чтения 1000 пользователя", user);
    }

    @Test
    public void CheckUserDAO(){
        UserDAO userDAO = new UserDAOImpl();
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        Employee employee = employeeDAO.read(new Long("1"));

        User user = new User("login", "password", employee);
        Long id = userDAO.create(user);
        User userReturned = userDAO.read(id);

        assertEquals("Проверка корректности записаного пользователя", userReturned, user);

        User user1 = new User("login", "password", employee);
        user1.setId(id);

        userDAO.update(user1);
        User userReturned1 = userDAO.read(id);

        assertEquals("Проверка корректности обновления информации о пользователе", userReturned1, user1);

        userDAO.delete(userReturned1);
        User userReturned2 = userDAO.read(id);

        assertNull("Проверка корректности удаления информации о пользователе", userReturned2);
    }
}
