package com.store.backend.controller;

import com.store.backend.model.Item;
import com.store.backend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item createItem(@RequestBody Item item){
        return itemService.addItem(item);
    }

    @GetMapping
    public List<Item> getItems(){
        return itemService.findAllItems();
    }


}
