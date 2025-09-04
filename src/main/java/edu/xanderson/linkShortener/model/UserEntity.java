package edu.xanderson.linkShortener.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor
@Entity
public class UserEntity implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false, unique = true)
    private String email;

    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "owner",
                cascade = CascadeType.ALL,
                orphanRemoval = true
    )
    private List<ShortLinksEntity> links  = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities ;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}