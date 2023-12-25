package com.store.backend.service;

import com.store.backend.model.Item;
import com.store.backend.model.Log;
import com.store.backend.model.dto.LogResponseDto;
import com.store.backend.repository.ItemRepository;
import com.store.backend.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private ItemRepository itemRepository;

    public Log createLog(String userId, String itemId, Log.LogType type){
        Log data = new Log();
        data.setId(UUID.randomUUID().toString());
        data.setUserId(userId);
        data.setItemId(itemId);
        data.setType(type);
        logRepository.save(data);
        return data;
    }

    public LogResponseDto findLogByUserId(String userId){
        List<Log> logs= logRepository.findAllByUserId(userId);
        List<Item> gave = new ArrayList<>();
        List<Item> taken = new ArrayList<>();
        for (Log log : logs) {
            if (log.getType() == Log.LogType.BUY) {
                taken.add(itemRepository.findById(log.getItemId()).get());
            }
            if(log.getType() == Log.LogType.SELL){
                gave.add(itemRepository.findById(log.getItemId()).get());
            }
        }
        return LogResponseDto.builder().gave(gave).taken(taken).build();
    }

    public List<Log> findLogByItemId(String itemId){
        return logRepository.findAllByItemId(itemId);
    }

    public Log findLogById(String id){
        Optional<Log> data = logRepository.findById(id);
        if(data.isPresent()){
            return data.get();
        }
        return new Log();
    }
}
