package com.store.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "logs")
@AllArgsConstructor
@NoArgsConstructor

public class Log {

    @Id
    private String id;
    private LogType type;
    private String userId;
    private String itemId;

    public enum LogType{
        SELL,BUY
    }

}
