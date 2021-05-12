package com.haonguyen.CommodityService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityListDTO {
    @Type(type = "uuid-char")
    private UUID commodityId;
    private String typeOfCommodityName;
    private String commodityName;
    private Float commodityPrice;
    private String commodityUnit;
    private Double inventoryNumber;

}
