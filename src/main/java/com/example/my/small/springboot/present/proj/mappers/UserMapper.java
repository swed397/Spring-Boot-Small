package com.example.my.small.springboot.present.proj.mappers;

import com.example.my.small.springboot.present.proj.dtos.user.UserDto;
import com.example.my.small.springboot.present.proj.entities.UserPrincipal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(UserPrincipal source);
}
