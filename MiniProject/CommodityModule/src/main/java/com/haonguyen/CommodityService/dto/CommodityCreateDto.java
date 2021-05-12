package com.haonguyen.CommodityService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommodityCreateDto {

    @Type(type = "uuid-char")
    private UUID id;
    private String commodityName;
    private String description;
    private Float price;
    private String unit;


    @Type(type = "uuid-char")
    private UUID idTypeOfCommodity;


}
