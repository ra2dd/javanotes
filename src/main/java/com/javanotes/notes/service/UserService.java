package com.javanotes.notes.service;

import com.javanotes.notes.dto.RegistrationDto;
import com.javanotes.notes.models.UserEntity;

public interface UserService
{
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByUsername(String username);
}
