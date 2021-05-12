package com.kltn.SecurityModule.dto;

import com.mini_project.CoreModule.entity.UserEntity;
import com.netflix.discovery.converters.Auto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDetails {
    UserEntity userEntity;

}
