package com.haonguyen.ServiceImport.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoDetailsImportDTO{
    @Type(type = "uuid-char")
    private UUID idImport;
    private String countryName;
    private String warehouseName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateImport;
    private List<String> urlImages;
    private List<InfoDetailsCommodityImport> commodityImportList;
}
