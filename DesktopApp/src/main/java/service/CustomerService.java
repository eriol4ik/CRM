package service;

import entity.Customer;
import entity.Order;

import java.util.List;

public interface CustomerService extends Service<Customer, Long> {
    List<Order> findOrders(Customer customer);

    Customer find(String email);

    Customer readWithAccount(Long id);

    Customer readWithOrders(Long id);
}
