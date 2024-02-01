package com.store.backend.service;

import com.store.backend.model.Item;
import com.store.backend.model.Log;
import com.store.backend.model.request.ItemAddRequest;
import com.store.backend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ImageDataService imageDataService;
    @Autowired
    private LogService logService;

    public Item addItem(ItemAddRequest request) throws IOException {
        Item item = new Item(UUID.randomUUID().toString(),
                request.getName(),
                request.getDescription(),
                request.getCategory(),
                request.getYearOfUse(),
                request.getCondition(),
                true);
        logService.createLog(request.getUserId(), item.getId(), Log.LogType.SELL);
        if(request.getFile() != null) {
            imageDataService.uploadImage(request.getFile(), item.getId());
        }
        return itemRepository.save(item);
    }

    public boolean buyItem(String itemId,String userId){
        Optional<Item> data = itemRepository.findById(itemId);
        if(data.isPresent()) {
            Item item = data.get();
            item.setAvailable(false);
            logService.createLog(userId, item.getId(), Log.LogType.BUY);
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

