package service;

import entity.Employee;
import entity.User;

public interface EmployeeService extends Service<Employee, Long> {
    User find(String email);
}
