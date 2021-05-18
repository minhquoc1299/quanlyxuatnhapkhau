package com.haonguyen.ServiceImport.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;


import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImportCreate {

    @Type(type = "uuid-char")
    private UUID id;

    @Type(type = "uuid-char")
    private UUID idCountry;

    @Type(type = "uuid-char")
    private UUID idWarehouse;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private int type;
    private List<ItemReceiptDTO> listCommodity;
    private List<String> listDocument;
}
