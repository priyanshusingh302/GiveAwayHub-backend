package com.store.backend.service;

import com.store.backend.model.Log;
import com.store.backend.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public Log createLog(String userId, String itemId, Log.LogType type){
        Log data = new Log();
        data.setId(UUID.randomUUID().toString());
        data.setUserId(userId);
        data.setItemId(itemId);
        data.setType(type);
        logRepository.save(data);
        return data;
    }

    public List<Log> findLogByUserId(String userId){
        return logRepository.findAllByUserId(userId);
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
