package com.example.SpringSecuritynew.Repository;

import com.example.SpringSecuritynew.Entity.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepo extends JpaRepository<Userinfo, Long> {
    Optional<Userinfo> findByName(String name);
}
