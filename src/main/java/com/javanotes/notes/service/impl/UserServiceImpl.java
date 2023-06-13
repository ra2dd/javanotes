package com.javanotes.notes.service.impl;

import com.javanotes.notes.dto.RegistrationDto;
import com.javanotes.notes.repository.RoleRepository;
import com.javanotes.notes.repository.UserRepository;
import com.javanotes.notes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService
{
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository)
    {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto)
    {

    }
}
