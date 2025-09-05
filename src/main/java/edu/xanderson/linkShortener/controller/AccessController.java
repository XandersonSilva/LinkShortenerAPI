package edu.xanderson.linkShortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import edu.xanderson.linkShortener.model.DTOs.AcessLinkPasswordDTO;
import edu.xanderson.linkShortener.service.AccessService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AccessController {
    @Autowired
    private AccessService accessService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/l/{link}")
    public String getOriginalLink(@PathVariable("link") String link, @RequestBody AcessLinkPasswordDTO password){
        System.out.println(link);
        
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
}
