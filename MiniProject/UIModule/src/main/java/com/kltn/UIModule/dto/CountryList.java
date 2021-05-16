package com.kltn.UIModule.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryList {
    @JsonSubTypes.Type(UUID.class)
    private UUID id;
    private String countryName;
    private Double tax;
    private Double transportFee;
}
