package com.javanotes.notes.controller;

import com.javanotes.notes.dto.RegistrationDto;
import com.javanotes.notes.models.UserEntity;
import com.javanotes.notes.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController
{
    private UserService userService;

    public AuthController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model)
    {
        RegistrationDto userDto = new RegistrationDto();
        model.addAttribute("userDto", userDto);
        return "register";
    }

    @PostMapping("/register/save")
    public String registerUser(@Valid @ModelAttribute("user") RegistrationDto userDto,
                               BindingResult result, Model model)
    {
        UserEntity existingUser = userService.findByUsername(userDto.getUsername());
        if(existingUser != null && existingUser.getUsername() != null && !existingUser.getUsername().isEmpty())
        {
            result.rejectValue("username", "There is already user with this username.");
        }

        if(result.hasErrors())
        {
            model.addAttribute("userDto", userDto);
            return "register";
        }

        userService.saveUser(userDto);
        return "redirect:/notes?success";
    }

    @GetMapping("/login")
    public String loginPage()
    {
        return "login";
    }
}
