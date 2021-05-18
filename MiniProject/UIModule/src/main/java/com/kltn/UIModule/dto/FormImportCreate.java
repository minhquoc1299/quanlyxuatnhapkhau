package com.kltn.UIModule.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormImportCreate {
    @JsonSubTypes.Type(UUID.class)
    private UUID idImport;

    @JsonSubTypes.Type(UUID.class)
    private UUID idCountry;

    @JsonSubTypes.Type(UUID.class)
    private UUID idWarehouse;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateImport;

    private List<FormCommodityImport> listCommodity;

    private MultipartFile[] listDocument;

}
