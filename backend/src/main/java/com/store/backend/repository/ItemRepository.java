package com.store.backend.repository;

import com.store.backend.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ItemRepository extends MongoRepository<Item,String> {

    List<Item> findByAvailable(boolean available);


}
