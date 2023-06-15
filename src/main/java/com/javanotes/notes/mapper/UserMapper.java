package com.javanotes.notes.mapper;

import com.javanotes.notes.dto.RegistrationDto;
import com.javanotes.notes.models.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserMapper
{
    public static UserEntity mapToUserEntity(RegistrationDto registrationDto, PasswordEncoder passwordEncoder)
    {
        UserEntity userMapped = UserEntity.builder()
                .username(registrationDto.getUsername())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .name(registrationDto.getName())
                .surname(registrationDto.getSurname())
                .age(registrationDto.getAge())
                .build();

        return userMapped;
    }
}
