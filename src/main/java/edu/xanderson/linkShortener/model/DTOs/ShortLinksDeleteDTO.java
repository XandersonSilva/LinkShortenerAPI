package edu.xanderson.linkShortener.model.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter 
public class ShortLinksDeleteDTO {
    @NotNull
    private long id;
}
