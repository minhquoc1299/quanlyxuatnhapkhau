package com.haonguyen.CommodityService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeList {

    @Type(type = "uuid-char")
    private UUID idType;
    private String nameType;
}
