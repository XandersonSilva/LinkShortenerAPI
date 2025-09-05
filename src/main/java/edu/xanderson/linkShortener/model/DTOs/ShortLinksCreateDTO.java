package edu.xanderson.linkShortener.model.DTOs;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter 
public class ShortLinksCreateDTO {
    
    @NotBlank
    private String originalUrl;

    @Setter
    private String code;
    
    private LocalDate expirationDate = LocalDate.now().plusMonths(2);
    
    private boolean isPrivate;
    
    private String password;

    @NotNull
    private long owner_id;

    public boolean getIsPrivate(){
        return this.isPrivate;
    }
}
