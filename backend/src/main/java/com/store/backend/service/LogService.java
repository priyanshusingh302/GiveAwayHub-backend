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

//    public Log createLog(String userId,String itemId,String type){
//        return
//    }
    public Log findLogByUserId(String userId){
        return logRepository.findById(userId).get();
    }

    public Log findLogByItemId(String itemId){
        return logRepository.findById(itemId).get();
    }
}
