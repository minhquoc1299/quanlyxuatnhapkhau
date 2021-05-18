package com.khoaluan.UserModule.mapper;

import com.khoaluan.UserModule.dto.InfoUserDTO;
import com.mini_project.CoreModule.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    InfoUserDTO toInfoUserDTO(UserEntity userEntity);
}
