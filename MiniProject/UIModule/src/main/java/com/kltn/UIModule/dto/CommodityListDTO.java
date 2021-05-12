package com.kltn.UIModule.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityListDTO {
    @JsonSubTypes.Type(UUID.class)
    private UUID commodityId;
    private String typeOfCommodityName;
    private String commodityName;
    private Float commodityPrice;
    private String commodityUnit;
    private Double inventoryNumber;

}
