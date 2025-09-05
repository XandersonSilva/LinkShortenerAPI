package edu.xanderson.linkShortener.model.DTOs;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ShortLinksEditDTO {
    
    @NotNull
    private long id;
    
    private LocalDate expirationDate;
    
    private boolean isPrivate;
    
    private String password;
}
