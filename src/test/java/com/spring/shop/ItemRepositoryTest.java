package com.spring.shop;

import com.spring.shop.item.Item;
import com.spring.shop.item.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemRepositoryTest {
    @Autowired
    private ItemRepository itemRepository;
    @Test
    public void testAdd() throws Exception {
        for (int i = 0; i < 100; i++) {
            Item item = new Item();
            item.setId((long) i);
            item.setTitle("바지" + i);
            item.setPrice(i);
            itemRepository.save(item);
        }
    }
}
