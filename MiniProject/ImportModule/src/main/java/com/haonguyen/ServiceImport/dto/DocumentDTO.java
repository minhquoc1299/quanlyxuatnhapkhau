package com.haonguyen.ServiceImport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDTO {
    @Type(type = "uuid-char")
    private UUID id;
    private String url;
}
