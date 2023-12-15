package com.store.backend.service;

import com.store.backend.model.Item;
import com.store.backend.model.Log;
import com.store.backend.model.Task;
import com.store.backend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private LogService logService;

    public Item addItem(Item item){
        item.setId(UUID.randomUUID().toString());
        item.setAvailable(true);
        logService.createLog("user",item.getId(), Log.LogType.SELL);
        return itemRepository.save(item);
    }

    public boolean buyItem(String itemId){
        Optional<Item> data = itemRepository.findById(itemId);
        if(data.isPresent()) {
            Item item = data.get();
            item.setAvailable(false);
            logService.createLog("user", item.getId(), Log.LogType.BUY);
            itemRepository.save(item);
            return true;
        }
        return false;
    }

    public List<Item> findAllItems(){
        return itemRepository.findAll();
    }

    public List<Item> findAllAvailableItems(){
        return itemRepository.findByAvailable(true);
    }


}

