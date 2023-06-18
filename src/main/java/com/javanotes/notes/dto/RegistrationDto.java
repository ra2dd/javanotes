package com.javanotes.notes.dto;

import com.javanotes.notes.models.Role;
import com.javanotes.notes.validation.NameUpperCaseConstraint;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto
{
    private Long id;

    @Size(min = 3, max = 20, message = "Username should be in range 3 - 20 characters.")
    private String username;

    @Size(min = 5, message = "Password should have minimum of 5 characters.")
    private String password;

    @Size(min = 3, max = 20, message = "First Name should be in range 3 - 20 characters.")
    //@Pattern(regexp = "^[A-Z]*(?: [A-Z][a-z]*)*$", message = "The first letter of the First Name should be uppercase.")
    @NameUpperCaseConstraint(message = "Name should start with uppercase letter.")
    private String name;

    @Size(min = 3, max = 50, message = "Last Name should be in range 3 - 20 characters.")
    @NameUpperCaseConstraint(message = "Last Name should start with uppercase letter.")
    private String surname;

    @Min(value = 12, message = "You should be 12 years old or older to register.")
    private int age;

    private List<Role> roles = new ArrayList<>();
}
