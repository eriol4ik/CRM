package dao;


import entity.CustomerAccount;

public interface CustomerAccountDAO extends DAO<CustomerAccount, String> {
    String findPass(String email);
}
