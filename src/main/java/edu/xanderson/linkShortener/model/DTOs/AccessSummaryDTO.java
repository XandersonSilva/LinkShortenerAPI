package edu.xanderson.linkShortener.model.DTOs;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import edu.xanderson.linkShortener.model.AccessEntity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AccessSummaryDTO {
    public AccessSummaryDTO(AccessEntity access){
        BeanUtils.copyProperties(access, this);
    }

    private String IP;

    private LocalDateTime accessDate;

    private Boolean wasBlocked;
}
