package edu.xanderson.linkShortener.model;

import java.time.LocalDate;

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
public class AccessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String IP;

    @ManyToOne
    @JoinColumn(nullable = false, name = "shortLink_id")
    private ShortLinksEntity shortLink;

    @Column(nullable = false)
    private LocalDate accessDate;

    @Column(nullable = false)
    private Boolean wasBlocked;
}