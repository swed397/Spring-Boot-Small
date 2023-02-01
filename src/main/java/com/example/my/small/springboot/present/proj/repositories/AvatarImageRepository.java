package com.example.my.small.springboot.present.proj.repositories;

import com.example.my.small.springboot.present.proj.entities.AvatarImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvatarImageRepository extends JpaRepository<AvatarImage, Long> {

    Optional<AvatarImage> findByUserPrincipal_UserName(String userName);
}
