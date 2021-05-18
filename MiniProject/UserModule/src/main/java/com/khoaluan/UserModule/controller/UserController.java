package com.khoaluan.UserModule.controller;

import com.khoaluan.UserModule.dto.InfoUserDTO;
import com.khoaluan.UserModule.service.IUserService;
import com.mini_project.CoreModule.entity.UserEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/user/")
public class UserController {
    private final IUserService iUserService;

    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @RequestMapping(value = "/check",method = RequestMethod.POST)
    public InfoUserDTO checkUser(@RequestBody UserEntity userEntity){
        InfoUserDTO infoUserDTO = iUserService.checkUser(userEntity);
        return infoUserDTO;
    }
    @RequestMapping(value = "/{username}",method = RequestMethod.GET)
    public InfoUserDTO checkUser(@PathVariable("username") String username){
        InfoUserDTO infoUserDTO = iUserService.checkByUser(username);
        return infoUserDTO;
    }

    //@RequestMapping(value = "/{id}",method = RequestMethod.GET )
}
