package com.haonguyen.CommodityService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseList {
    @Type(type = "uuid-char")
    private UUID id;
    private String warehouseName;
    private Double capacity;
}
