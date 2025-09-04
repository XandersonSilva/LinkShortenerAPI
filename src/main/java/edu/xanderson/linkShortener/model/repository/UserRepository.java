package edu.xanderson.linkShortener.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import edu.xanderson.linkShortener.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
    UserDetails findByEmail(String email);
}
