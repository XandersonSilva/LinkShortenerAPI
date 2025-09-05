package edu.xanderson.linkShortener.model;

import org.springframework.beans.BeanUtils;

import edu.xanderson.linkShortener.model.DTOs.BlockIPCreateDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@Entity
public class BlockIPEntity {
    public BlockIPEntity(BlockIPCreateDTO block){
        this.setIP(block.getIp());
        this.code.setId(block.getLink_id());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "link_id", nullable = false)
    private ShortLinksEntity code = new ShortLinksEntity();
    

    @Column(nullable = false)
    private String IP;
}