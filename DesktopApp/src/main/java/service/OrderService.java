package service;

import entity.Employee;
import entity.Order;
import enum_types.OrderStatus;

import java.util.List;

public interface OrderService extends Service<Order, Long> {
    List<Order> findAllFor(Employee manager);
    Order readWithItems(Long id);
    List<Order> findWithStatus(OrderStatus status);
}
