package dao;

import entity.Employee;
import entity.Item;
import entity.Order;
import enum_types.OrderStatus;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderDAO")
public class OrderDAOImpl extends DAOImpl<Order, Long> implements OrderDAO {
    @Autowired
    private SessionFactory factory;

    @Autowired
    protected OrderDAOImpl() {}

    @Override
    public List<Order> findOrdersFor(Employee manager) {
        return factory.getCurrentSession()
                .createQuery("FROM Order o WHERE o.manager = :manager", Order.class)
                .setParameter("manager", manager)
                .list();
    }

    @Override
    public List<Item> findItems(Order order) {
        return factory.getCurrentSession()
                .createQuery("SELECT i FROM Item i WHERE i.order = :order", Item.class)
                .setParameter("order", order)
                .list();
    }

    @Override
    public List<Order> findWithStatus(OrderStatus status) {
        return factory.getCurrentSession()
                .createQuery("SELECT o FROM Order o WHERE o.status = :status", Order.class)
                .setParameter("status", status)
                .list();
    }
}
