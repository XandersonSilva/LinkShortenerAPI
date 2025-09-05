package edu.xanderson.linkShortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.xanderson.linkShortener.model.UserEntity;
import edu.xanderson.linkShortener.model.DTOs.BlockIPCreateDTO;
import edu.xanderson.linkShortener.service.BlockIPService;

@RestController
public class BlockIPController {
    @Autowired
    private BlockIPService blockIPService;

    // Bloquear IP
    @PostMapping("/link/block_ip")
    public ResponseEntity<String> block_ip(@Validated @RequestBody BlockIPCreateDTO block, @AuthenticationPrincipal UserEntity user){
        if(blockIPService.blockIPAccessLink(block, user)){
            return ResponseEntity.ok().body("IP Bloqueado!");
        }
        return ResponseEntity.badRequest().body("ERRO");
    }

}
