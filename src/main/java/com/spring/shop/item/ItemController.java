package com.spring.shop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class ItemController {
    private final ItemService itemService;
    private final ItemRepository itemRepository;
//    @GetMapping("/api/list")
//    public ResponseEntity<?> getItem(){
//        var result = itemService.getItemService();
//        return ResponseEntity.status(200).body(result);
//    }
    @GetMapping("/api/list/{id}")
    public ResponseEntity<?> getItems(@PathVariable("id") Long id){
        Page<Item> result = itemRepository.findPageBy(PageRequest.of( 0, 5));//1번째 페이지 페이지당 5개 0번째부터 5번째
        result.getTotalElements();
        System.out.println(result.getTotalPages()); //1 2 3 4 5 6 7 8 9 10
        System.out.println(result.getTotalElements());
        return ResponseEntity.status(200).body(result.get());
    }
    @GetMapping("/api/detail/{id}")
    public ResponseEntity<?> getItemDetail(@PathVariable("id") Long id){
        var result = itemService.getItemDetailService(id);
        return ResponseEntity.status(200).body(result);
    }
    @PostMapping("/api/add")
    public ResponseEntity<?> addItem(@RequestBody ItemDto itemDto){
        var result = itemService.addItemService(itemDto);
        return ResponseEntity.status(201).body(result);
    }
    @PostMapping("/api/edit/{id}")
    public ResponseEntity<?> editItem(@PathVariable("id") Long id,@RequestBody ItemDto itemDto) throws Exception {
        var result = itemService.editItemService(id, itemDto);
        return ResponseEntity.status(200).body(result);
    }
    @DeleteMapping("/api/delete")
    public ResponseEntity<?> deleteItem(@RequestParam(name="id") Long id){
        itemService.deleteItemService(id);
        return ResponseEntity.status(200).body("삭제 완료");
    }

}
