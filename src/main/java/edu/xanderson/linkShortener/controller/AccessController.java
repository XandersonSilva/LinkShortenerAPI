package edu.xanderson.linkShortener.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import edu.xanderson.linkShortener.model.UserEntity;
import edu.xanderson.linkShortener.model.DTOs.AccessSummaryDTO;
import edu.xanderson.linkShortener.model.DTOs.AcessLinkPasswordDTO;
import edu.xanderson.linkShortener.model.DTOs.ShortLinksEditDTO;
import edu.xanderson.linkShortener.service.AccessService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AccessController {
    @Autowired
    private AccessService accessService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/l/{link}")
    public String getOriginalLink(@PathVariable("link") String link, @RequestBody(required = false) AcessLinkPasswordDTO password){
        if (password == null) {
            password = new AcessLinkPasswordDTO();
            password.setPassword("");
        }
        
        String originalLink = accessService.registerAccess(link, password.getPassword(), request.getRemoteAddr());
        //ERRO: 
        //NF:  Abreviação de not finded
        if (originalLink == "NF") {
            return null;
        }
        
        if (originalLink == "WP") {
            //ERRO: 
            //WP:  Abreviação de wrong password
            return null;
        }

         return "redirect:" + originalLink;
    }

    // Ver métricas do link
    @PostMapping("/link/getAccesses")
    public ResponseEntity<List<AccessSummaryDTO>> getAccesses(@Validated @RequestBody ShortLinksEditDTO link, @AuthenticationPrincipal UserEntity user){
        List<AccessSummaryDTO> accesses = accessService.getLinkAccess(link, user);
        
        return ResponseEntity.ok().body(accesses);
    }
}
