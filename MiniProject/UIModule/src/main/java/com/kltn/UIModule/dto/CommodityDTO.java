package com.kltn.UIModule.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityDTO {
    @JsonSubTypes.Type(UUID.class)
    private UUID idCommodity;
    private Double quantity;
}
