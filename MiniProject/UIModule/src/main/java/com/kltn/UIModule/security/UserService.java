package com.kltn.UIModule.security;

import com.kltn.UIModule.dto.InfoUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.sound.sampled.DataLine.Info;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private RestTemplate restTemplate;
    private String urlUserService = "http://localhost:9000/v1/api/user/";
    private InfoUserDTO checkUser(String username){
        InfoUserDTO infoUserDTO = restTemplate.
                getForObject(urlUserService + username,InfoUserDTO.class);
        return infoUserDTO;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        InfoUserDTO infoUserDTO = checkUser(username);
        if (infoUserDTO == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUser(infoUserDTO);
    }
}
