package service;

import dao.CustomerAccountDAO;
import entity.CustomerAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

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
