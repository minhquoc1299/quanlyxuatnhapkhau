package com.khoaluan.UserModule.service;

import com.khoaluan.UserModule.dto.InfoUserDTO;
import com.khoaluan.UserModule.mapper.IUserMapper;
import com.khoaluan.UserModule.repository.IUserRepository;
import com.mini_project.CoreModule.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.DataLine.Info;
import java.util.List;

@Service
public class UserService implements IUserService{

    private final IUserRepository iUserRepository;
    private final IUserMapper iUserMapper;

    public UserService(IUserRepository iUserRepository, IUserMapper iUserMapper) {
        this.iUserRepository = iUserRepository;
        this.iUserMapper = iUserMapper;
    }

    @Override
    public InfoUserDTO checkUser(UserEntity userEntity) {
        List<UserEntity> flag =
                iUserRepository.checkUser(userEntity.getUsername(),userEntity.getPassword());
        if(!flag.isEmpty()){
            return iUserMapper.toInfoUserDTO(flag.get(0));
        }
        return null;
    }

    @Override
    public InfoUserDTO checkByUser(String username) {
        UserEntity userEntity = iUserRepository.findByUsername(username);
        return iUserMapper.toInfoUserDTO(userEntity);
    }
}
