package dao;

import entity.Employee;
import entity.User;

public interface EmployeeDAO extends DAO<Employee, Long> {
    User find(String email);
}
