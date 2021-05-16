package com.haonguyen.ServiceImport.dto;

import com.mini_project.CoreModule.entity.DetailsImportExportEntity;
import com.mini_project.CoreModule.entity.DocumentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExcelReceiptImportDTO {
    @Type(type = "uuid-char")
    private UUID id;
    private String countryName;
    private String warehouseName;
    private Date date;
    Collection<DetailsImportExportEntity> detailsImportExportEntities;
    Collection<DocumentEntity> documentEntities;

}
