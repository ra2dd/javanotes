package com.javanotes.notes.dto;

import com.javanotes.notes.models.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class RegistrationDto
{
    private Long id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private String name;
    private String surname;
    private int age;

    //private List<Role> roles = new ArrayList<>();
}
