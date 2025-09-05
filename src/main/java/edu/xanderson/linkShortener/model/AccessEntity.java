package edu.xanderson.linkShortener.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor
@Entity
public class AccessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String IP;

    @NonNull
    @ManyToOne
    @JoinColumn(nullable = false, name = "shortLink_id")
    private ShortLinksEntity shortLink;

    @Column(nullable = false)
    private LocalDateTime accessDate = LocalDateTime.now();

    @NonNull
    @Column(nullable = false)
    private Boolean wasBlocked;
}