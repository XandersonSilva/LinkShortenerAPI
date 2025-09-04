package edu.xanderson.linkShortener.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import edu.xanderson.linkShortener.model.DTOs.ShortLinksCreateDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@Entity
public class ShortLinksEntity {

    public ShortLinksEntity(ShortLinksCreateDTO link){
        BeanUtils.copyProperties(link, this);
        this.owner.setId(link.getOwner_id());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 2048)
    private String originalUrl;

    @Column(nullable = false, unique = true)
    private String code;
    
    @Column(nullable = false)
    private LocalDate expirationDate = LocalDate.now().plusMonths(2);
    
    private boolean isPrivate = false;
    
    private String password;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private UserEntity owner = new UserEntity();

    @OneToMany(mappedBy = "shortLink",
                cascade = CascadeType.ALL,
                orphanRemoval = true
    )
    private List<AccessEntity> acess = new ArrayList<>();

}