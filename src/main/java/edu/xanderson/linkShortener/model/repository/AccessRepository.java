package edu.xanderson.linkShortener.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.xanderson.linkShortener.model.AccessEntity;

public interface AccessRepository extends JpaRepository<AccessEntity, Long>{
    List<AccessEntity> findByShortLinkId(long id);
    
}
