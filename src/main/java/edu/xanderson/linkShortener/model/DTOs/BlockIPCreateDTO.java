package edu.xanderson.linkShortener.model.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BlockIPCreateDTO {
    @NotNull
    private long link_id;

    @NotBlank
    private String ip;
}
