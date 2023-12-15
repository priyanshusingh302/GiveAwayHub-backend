package com.store.backend.repository;

import com.store.backend.model.Log;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface LogRepository extends MongoRepository<Log,String> {

    List<Log> findAllByUserId(String userId);

    List<Log> findAllByItemId(String itemId);
}
