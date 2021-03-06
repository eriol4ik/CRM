package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.CustomerAccountDAO;
import web.entity.Customer;
import web.entity.CustomerAccount;

@Service("customerAccountService")
public class CustomerAccountServiceImpl extends ServiceImpl<CustomerAccount, String> implements CustomerAccountService {
    @Autowired
    @Qualifier("customerAccountDAO")
    private CustomerAccountDAO customerAccountDAO;

    private CustomerAccountServiceImpl() {}

    @Override
    @Transactional
    public String findPass(String email) {
        return customerAccountDAO.findPass(email);
    }
}
