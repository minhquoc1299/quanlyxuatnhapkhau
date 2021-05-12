package com.haonguyen.CommodityService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TypeOfCommodityDto {
    private String commodityName;
    private String description;
    private Float price;
    private String unit;

}
