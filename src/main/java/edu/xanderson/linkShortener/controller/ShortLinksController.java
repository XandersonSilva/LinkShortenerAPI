package edu.xanderson.linkShortener.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties.ShowSummary;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.xanderson.linkShortener.model.UserEntity;
import edu.xanderson.linkShortener.model.DTOs.ShortLinksCreateDTO;
import edu.xanderson.linkShortener.model.DTOs.ShortLinksDeleteDTO;
import edu.xanderson.linkShortener.model.DTOs.ShortLinksSummaryDTO;
import edu.xanderson.linkShortener.service.ShortLinksService;
@RestController 
public class ShortLinksController {
    @Autowired
    private ShortLinksService shortLinksService;

    @PostMapping("/shrt")
    public ResponseEntity<String> shortL(@Validated @RequestBody ShortLinksCreateDTO link) throws IOException{
        String result = shortLinksService.createShortLink(link);

        switch (result) {
            case "1":
                return ResponseEntity.ok().body("O código informado não pode ser usado tente novamente");        
            case "2":
                return ResponseEntity.ok().body("ERRO");
            default:
                return ResponseEntity.ok().body(result);
        }
    }

    // Listar links
    @PostMapping("/links")
    public ResponseEntity<List<ShortLinksSummaryDTO>> getUserlLinks(@AuthenticationPrincipal UserEntity user){
        List<ShortLinksSummaryDTO> links = shortLinksService.getLinks(user);
        if(links.size() > 0){
            return ResponseEntity.ok().body(links);
        }
        return ResponseEntity.badRequest().body(null);
    }

    // Apagar link
    @PostMapping("/link/delete")
    public ResponseEntity<String> deleteLink(@Validated @RequestBody ShortLinksDeleteDTO link, @AuthenticationPrincipal UserEntity user){
        if(shortLinksService.deleteLink(link, user)){
            return ResponseEntity.ok().body("Link deletado!");
        }
        return ResponseEntity.badRequest().body("ERRO");
    }
    // Ver métricas do link
    // Adicionar senha ao link
    // Remover senha do link
    // Adicionar expiração
    // Bloquear IP
    // Buscar links
}

