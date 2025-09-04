package edu.xanderson.linkShortener.model.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AuthDTO {
    @Email
    private String email;

    @NotBlank
    private String password;
    
}