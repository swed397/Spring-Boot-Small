package com.example.my.small.springboot.present.proj.repositories;

import com.example.my.small.springboot.present.proj.entities.UserPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserPrincipal, Long> {

    Optional<UserPrincipal> findByUserName(String userName);

    List<UserPrincipal> findAllByUserName(String userName);

}
