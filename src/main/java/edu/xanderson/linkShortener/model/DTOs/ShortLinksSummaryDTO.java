package edu.xanderson.linkShortener.model.DTOs;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import edu.xanderson.linkShortener.model.ShortLinksEntity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ShortLinksSummaryDTO {
    public ShortLinksSummaryDTO(ShortLinksEntity link){
        BeanUtils.copyProperties(link, this);
    }

    private long id;
    
    private String originalUrl;

    private String code;
    
    private LocalDate expirationDate;
    
    private boolean isPrivate;
    
    private String password;
}
