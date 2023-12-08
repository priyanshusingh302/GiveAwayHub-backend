package com.store.backend.service;

import com.store.backend.model.Item;
import com.store.backend.model.Task;
import com.store.backend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item addItem(Item item){
        item.setId(UUID.randomUUID().toString());
        return itemRepository.save(item);
    }

    public List<Item> findAllItems(){
        return itemRepository.findAll();
    }


}
