package com.javanotes.notes.service.impl;

import com.javanotes.notes.dto.RegistrationDto;
import com.javanotes.notes.models.Role;
import com.javanotes.notes.models.UserEntity;
import com.javanotes.notes.repository.RoleRepository;
import com.javanotes.notes.repository.UserRepository;
import com.javanotes.notes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.javanotes.notes.mapper.UserMapper.mapToUserEntity;

@Service
public class UserServiceImpl implements UserService
{
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto)
    {
        UserEntity newUser = mapToUserEntity(registrationDto, passwordEncoder);

        List<Role> newRole = new ArrayList<>();
        newRole.add(roleRepository.findByName("LIMITED_USER"));
        newUser.setRoles(newRole);

        userRepository.save(newUser);
    }

    @Override
    public UserEntity findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }
}
