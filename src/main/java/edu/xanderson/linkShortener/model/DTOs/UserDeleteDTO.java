package edu.xanderson.linkShortener.model.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserDeleteDTO {
    @NotBlank
    private String password;
    
}