package service;

import entity.CustomerAccount;

public interface CustomerAccountService extends Service<CustomerAccount, String> {
    String findPass(String email);
}
