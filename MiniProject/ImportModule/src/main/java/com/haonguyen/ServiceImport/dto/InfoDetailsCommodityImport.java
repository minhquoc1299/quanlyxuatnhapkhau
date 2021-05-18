package com.haonguyen.ServiceImport.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoDetailsCommodityImport {
    @Type(type = "uuid-char")
    private UUID idCommodity;
    private String commodityName;
    private Double quantity;
    private Float price;
    private Double total;

}
