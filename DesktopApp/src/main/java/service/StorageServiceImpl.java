package service;

import dao.StorageDAO;
import entity.Product;
import entity.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

@Service("storageService")
public class StorageServiceImpl extends ServiceImpl<Storage, Product> implements StorageService {
    @Autowired
    @Qualifier("storageDAO")
    private StorageDAO storageDAO;

    private StorageServiceImpl() {}

    @Override
    @Transactional
    public Integer getAvailability(Product product) {
        return storageDAO.getAmount(product);
    }
}
