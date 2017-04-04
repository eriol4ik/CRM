package service;

import dao.ItemDAO;
import entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Service("itemService")
public class ItemServiceImpl extends ServiceImpl<Item, Long> implements ItemService {
    @Autowired
    @Qualifier("itemDAO")
    private ItemDAO itemDAO;

    private ItemServiceImpl() {}
}
