package com.spring.shop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ItemService {
    private final ItemRepository itemRepository;
    public List<Item> getItemService(){
        var result = itemRepository.findAll();
        return result;
    }
    public Optional<Item> getItemDetailService(@RequestParam("id") Long id){
        var result = itemRepository.findById(id);
        return result;
    }
    public Item addItemService(ItemDto itemDto){
        var result = itemRepository.save(itemDto.toEntity());
        return result;
    }
    public Item editItemService(@RequestParam("id") Long id, ItemDto itemDto) throws Exception {
        Optional<Item> result = itemRepository.findById(id);
        Item item = new Item();
        item.setId(id);
        item.setTitle(itemDto.getTitle());
        item.setPrice(itemDto.getPrice());
        itemRepository.save(item);
        return item;
    }
    public void deleteItemService(@RequestParam(name="id") Long id){
        itemRepository.deleteById(id);
    }
}
