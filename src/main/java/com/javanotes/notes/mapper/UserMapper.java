package com.javanotes.notes.mapper;

import com.javanotes.notes.dto.RegistrationDto;
import com.javanotes.notes.models.UserEntity;

public class UserMapper
{
    public static UserEntity mapToUserEntity(RegistrationDto registrationDto)
    {
        UserEntity userMapped = UserEntity.builder()
                .username(registrationDto.getUsername())
                .password(registrationDto.getPassword())
                .name(registrationDto.getName())
                .surname(registrationDto.getSurname())
                .age(registrationDto.getAge())
                .build();

        return userMapped;
    }
}
