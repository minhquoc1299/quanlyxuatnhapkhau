package com.khoaluan.UserModule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoUserDTO {
    @Type(type = "uuid-char")
    private UUID id;
    private String username;
    private String password;
    private String fullName;
    private String numberIdentityCard;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
}
