package com.store.backend.repository;

import com.store.backend.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item,String> {

}
