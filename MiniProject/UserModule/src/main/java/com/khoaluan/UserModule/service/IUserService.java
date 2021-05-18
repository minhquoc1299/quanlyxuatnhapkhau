package com.khoaluan.UserModule.service;

import com.khoaluan.UserModule.dto.InfoUserDTO;
import com.mini_project.CoreModule.entity.UserEntity;

public interface IUserService {
    InfoUserDTO checkUser(UserEntity userEntity);

    InfoUserDTO checkByUser(String username);
}
