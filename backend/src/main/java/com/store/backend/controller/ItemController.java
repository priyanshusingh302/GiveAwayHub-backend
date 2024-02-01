package com.store.backend.controller;

import com.store.backend.model.Item;
import com.store.backend.model.request.BuyRequest;
import com.store.backend.model.request.ItemAddRequest;
import com.store.backend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/item")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/add")
    public ResponseEntity<Item> createItem(@ModelAttribute ItemAddRequest request) throws IOException {
        return ResponseEntity.ok(itemService.addItem(request));
    }

    @PostMapping("/buy")
    public ResponseEntity<Boolean> buyItem(@RequestBody BuyRequest request){
        return ResponseEntity.ok(itemService.buyItem(request.getItemId(), request.getUserId()));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Item>> getItems(){
        return ResponseEntity.ok(itemService.findAllAvailableItems());
    }




}
