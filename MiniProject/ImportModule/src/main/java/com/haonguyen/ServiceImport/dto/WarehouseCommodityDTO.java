package com.haonguyen.ServiceImport.dto;

import com.mini_project.CoreModule.entity.DetailsImportExportEntity;
import com.mini_project.CoreModule.entity.WarehouseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.Collection;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WarehouseCommodityDTO {
    @Type(type = "uuid-char")
    private UUID id;
    private WarehouseEntity idWarehouse;
    private Collection<DetailsImportExportEntity> commodityEntities;
    private Collection<ItemReceiptDTO> itemReceiptDTOS;

}
