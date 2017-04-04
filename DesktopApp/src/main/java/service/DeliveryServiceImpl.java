package service;

import dao.DeliveryDAO;
import entity.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Service("deliveryService")
public class DeliveryServiceImpl extends ServiceImpl<Delivery, Long> implements DeliveryService {
    @Autowired
    @Qualifier("deliveryDAO")
    private DeliveryDAO deliveryDAO;

    private DeliveryServiceImpl() {}
}
