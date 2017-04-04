package service;

import dao.PaymentDAO;
import entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Service("paymentService")
public class PaymentServiceImpl extends ServiceImpl<Payment, Long> implements PaymentService {
    @Autowired
    @Qualifier("paymentDAO")
    private PaymentDAO paymentDAO;

    private PaymentServiceImpl() {}
}
