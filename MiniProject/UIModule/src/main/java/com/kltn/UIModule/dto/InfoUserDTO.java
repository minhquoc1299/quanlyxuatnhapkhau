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
public class InfoUserDTO {
    @JsonSubTypes.Type(UUID.class)
    private UUID id;
    private String username;
    private String password;
    private String fullName;
    private String numberIdentityCard;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
}
