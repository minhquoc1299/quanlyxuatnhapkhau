package com.kltn.UIModule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportDTO {

    @JsonSubTypes.Type(UUID.class)
    private UUID idImport;
    private String countryName;
    private String warehouseName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateImport;
}
