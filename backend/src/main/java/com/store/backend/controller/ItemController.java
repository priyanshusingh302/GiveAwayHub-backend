package com.store.backend.controller;

import com.store.backend.model.Item;
import com.store.backend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/add")
    public ResponseEntity<Item> createItem(@RequestBody Item item){
        return ResponseEntity.ok(itemService.addItem(item));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Item>> getItems(){
        return ResponseEntity.ok(itemService.findAllAvailableItems());
    }

}
