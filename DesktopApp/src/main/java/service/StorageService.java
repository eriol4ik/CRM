package service;

import entity.Product;
import entity.Storage;

public interface StorageService extends Service<Storage, Product> {
    Integer getAvailability(Product product);
}
