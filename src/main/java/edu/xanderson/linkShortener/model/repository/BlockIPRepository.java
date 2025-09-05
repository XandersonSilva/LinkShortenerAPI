package edu.xanderson.linkShortener.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.xanderson.linkShortener.model.BlockIPEntity;

public interface BlockIPRepository extends JpaRepository<BlockIPEntity, Long>{
    BlockIPEntity findByIPAndCodeId(String ip, long linkId);
}   
