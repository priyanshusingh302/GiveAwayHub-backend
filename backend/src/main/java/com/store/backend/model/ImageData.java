package com.store.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "image")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageData {

    @Id
    String id;
    String referenceId;
    String name;
    String type;
    String filePath;
}
