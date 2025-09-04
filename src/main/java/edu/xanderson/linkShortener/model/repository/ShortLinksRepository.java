package edu.xanderson.linkShortener.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.xanderson.linkShortener.model.ShortLinksEntity;

public interface ShortLinksRepository extends JpaRepository<ShortLinksEntity, Long> {
    List<ShortLinksEntity> findByCode(String code);
}
