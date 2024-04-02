package com.spring.shop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class ItemController {
    private final ItemService itemService;
    private final S3Service s3Service;
    private final ItemRepository itemRepository;
//    @GetMapping("/api/list")
//    public ResponseEntity<?> getItem(){
//        var result = itemService.getItemService();
//        return ResponseEntity.status(200).body(result);

//    @GetMapping("/api/list/{id}")
//    public ResponseEntity<?> getItems(@PathVariable("id") Long id){
//        Page<Item> result = this.itemRepository.findPageBy(PageRequest.of( id.intValue() -1, 10));//1번째 페이지 페이지당 5개 0번째부터 5번째
//        result.getTotalElements();
//        System.out.println(result.getTotalPages()); //1 2 3 4 5 6 7 8 9 10
//        System.out.println(result.getTotalElements());
//        return ResponseEntity.status(200).body(result.get());
//    }


    @GetMapping("/api/list/{id}")
    public ResponseEntity<?> getItems(@PathVariable("id") Long id) {
        Page<Item> result = this.itemRepository.findPageBy(
                PageRequest.of(id.intValue()-1, 10, Sort.by(Sort.Direction.DESC, "id")));
        //총 게시물 개수
        var totalItems = result.getTotalElements();
        //총 페이지 개수 ex 게시물이 28개 있으면 페이지 3개 필요함
        int totalPages = result.getTotalPages();
        int startIndex = (int) Math.max(1, totalItems - id.intValue() * 10);
        // 페이지 컴포넌트의 마지막 숫자 ex 1페이지면 끝번호 10
        int end = (int)(Math.ceil(id / 10.0) * 10);
        int start = end - 9;
        //페이지 컴포넌트의 총 숫자 개수 ex 게시물이 80개가 있다면 페이지는
        var last = (int)(Math.ceil((double) totalItems /10));
        var a = new HashMap<>();
        a.put("start" , start);
        a.put("end", end);
        a.put("last", last);
        a.put("items", result);

        return ResponseEntity.status(200).body(a);
    }

//@GetMapping("/api/list/{id}")
//public ResponseEntity<?> getItems(@PathVariable("id") Long id){
//
//    var result = itemRepository.findAllByIdDesc().stream().map(Item::new).toList();
//
//    return ResponseEntity.status(200).body(result);
//}

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
    public ResponseEntity<?> editItem(@PathVariable("id") Long id,@RequestParam("writer") String writer ,@RequestBody ItemDto itemDto) throws Exception {
        var result = itemService.editItemService(id, writer ,itemDto);
//        var result = writer;
//        System.out.println(result);
        return ResponseEntity.status(200).body(result);
    }
    @DeleteMapping("/api/delete")
    public ResponseEntity<?> deleteItem(@RequestParam(name="id") Long id){
        itemService.deleteItemService(id);
        return ResponseEntity.status(200).body("삭제 완료");
    }
    @GetMapping("/api/presigned-url")
    public ResponseEntity<String> getURL(@RequestParam String filename){
        var result = s3Service.createPreSignedUrl("test/" + filename);
        return ResponseEntity.status(200).body(result);
    }
}
