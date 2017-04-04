package dao;


import entity.Employee;
import entity.Item;
import entity.Order;
import enum_types.OrderStatus;

import java.util.List;

public interface OrderDAO extends DAO<Order, Long> {
    List<Order> findOrdersFor(Employee manager);
    List<Item> findItems(Order order);
    List<Order> findWithStatus(OrderStatus status);
}
