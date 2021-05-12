package com.haonguyen.CommodityService.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeAndTaxCommodityAPI {
    @Type(type = "uuid-char")
    private UUID idTypeOfCommodity;
    private String typeOfCommodityName;

    @Type(type = "uuid-char")
    private UUID commodityId;
    private String commodityName;
    private Float commodityPrice;
    private String commodityUnit;

    @Type(type = "uuid-char")
    private UUID idTaxBracket;
    private String taxBracketName;
    private Float coefficient;


}
