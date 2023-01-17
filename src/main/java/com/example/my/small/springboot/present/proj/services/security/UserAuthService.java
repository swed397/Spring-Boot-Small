package com.example.my.small.springboot.present.proj.services.security;

import com.example.my.small.springboot.present.proj.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class UserAuthService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUserName(username)
                .map(user -> new User(
                        user.getUserName(),
                        user.getPassword(),
                        //ToDo temporary solution
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))))
                //ToDo Fix circular and make from bd
//                        user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
//                                .collect(Collectors.toList())))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
