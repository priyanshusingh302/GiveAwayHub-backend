package com.store.backend.repository;

import com.store.backend.model.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<Log,String> {

}
