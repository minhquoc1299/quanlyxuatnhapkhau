package com.kltn.UIModule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImportCreatePOST {

    @JsonSubTypes.Type(UUID.class)
    private UUID id;

    @JsonSubTypes.Type(UUID.class)
    private UUID idCountry;

    @JsonSubTypes.Type(UUID.class)
    private UUID idWarehouse;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private int type;
    private List<CommodityImportPOST> listCommodity;

    private List<String> listDocument;
}
