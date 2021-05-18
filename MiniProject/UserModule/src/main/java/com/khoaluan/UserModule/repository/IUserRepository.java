package com.khoaluan.UserModule.repository;

import com.mini_project.CoreModule.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, UUID> {

    @Query(value = "select u from UserEntity u" +
            " where u.username = :username and" +
            " u.password = :password")
    List<UserEntity> checkUser(@Param(value = "username") String username,
                               @Param(value = "password") String password);

    UserEntity findByUsername(String username);
}
