package com.kltn.SecurityModule.reponsitory;

import com.mini_project.CoreModule.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface SecurityRepository extends JpaRepository<UserEntity, UUID> {
}
