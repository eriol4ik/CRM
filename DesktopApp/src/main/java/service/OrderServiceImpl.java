package service;

import dao.OrderDAO;
import entity.Employee;
import entity.Order;
import enum_types.OrderStatus;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<Order, Long> implements OrderService {
    @Autowired
    @Qualifier("orderDAO")
    private OrderDAO orderDAO;

    private OrderServiceImpl() {}

    @Override
    @Transactional
    public List<Order> findAllFor(Employee manager) {
        return orderDAO.findOrdersFor(manager);
    }

    @Override
    @Transactional
    public Order readWithItems(Long id) {
        Order order = orderDAO.read(id);
        if (order != null) {
            Hibernate.initialize(order.getItems());
        }
        return order;
    }

    @Override
    @Transactional
    public List<Order> findWithStatus(OrderStatus status) {
        List<Order> orders = orderDAO.findWithStatus(status);
        for (Order order : orders) {
            Hibernate.initialize(order.getItems());
        }
        return orders;
    }
}
