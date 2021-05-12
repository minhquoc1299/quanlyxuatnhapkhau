package com.haonguyen.CommodityService.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommoditySearchDto {

    private String commodityName;
    private String description;
    private Float price;
    private String unit;
    @Type(type = "uuid-char")
    private UUID idTypeOfCommodity;

}
