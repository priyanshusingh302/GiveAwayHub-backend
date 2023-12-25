package com.store.backend.model.dto;

import com.store.backend.model.Item;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LogResponseDto {

    List<Item> gave;
    List<Item> taken;
}
