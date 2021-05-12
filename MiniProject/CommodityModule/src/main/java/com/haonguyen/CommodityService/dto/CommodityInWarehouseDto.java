package com.haonguyen.CommodityService.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityInWarehouseDto {

    private String commodityName;
    private String warehouseName;
    private Double inventoryNumber;

}
