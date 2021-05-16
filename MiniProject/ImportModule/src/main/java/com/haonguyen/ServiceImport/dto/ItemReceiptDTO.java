package com.haonguyen.ServiceImport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemReceiptDTO {
    @Type(type = "uuid-char")
    private UUID idCommodity;
    private Double quantity;
    private Double total;
}
