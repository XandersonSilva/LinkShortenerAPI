package edu.xanderson.linkShortener.model.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AuthRegisterDTO {
    @Email
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String password;
    
}