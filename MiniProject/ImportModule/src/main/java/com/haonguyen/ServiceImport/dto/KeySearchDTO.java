package com.haonguyen.ServiceImport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class KeySearchDTO {
    private String key;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

}
