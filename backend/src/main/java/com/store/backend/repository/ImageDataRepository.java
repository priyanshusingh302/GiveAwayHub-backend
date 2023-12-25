package com.store.backend.repository;

import com.store.backend.model.ImageData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ImageDataRepository extends MongoRepository<ImageData,String> {

    Optional<ImageData> findByReferenceId(String referenceId);
}
