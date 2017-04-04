package dao;

import entity.Product;
import entity.Storage;

public interface StorageDAO extends DAO<Storage, Product> {
    Integer getAmount(Product product);
}
