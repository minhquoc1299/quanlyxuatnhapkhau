package com.kltn.UIModule.dto;




import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommodityCreateDto {
    @JsonSubTypes.Type(UUID.class)
    private UUID id;
    private String commodityName;
    private String description;
    private Float price;
    private String unit;

    @JsonSubTypes.Type(UUID.class)
    private UUID idTypeOfCommodity;


}
