package com.kltn.UIModule.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseList {
    @JsonSubTypes.Type(UUID.class)
    private UUID id;
    private String warehouseName;
    private Double capacity;
}
