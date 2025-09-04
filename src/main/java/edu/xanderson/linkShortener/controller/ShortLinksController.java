package edu.xanderson.linkShortener.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import edu.xanderson.linkShortener.model.DTOs.ShortLinksCreateDTO;
import edu.xanderson.linkShortener.service.ShortLinksService;
import jakarta.servlet.http.HttpServletRequest;

@RestController 
public class ShortLinksController {
    @Autowired
    private ShortLinksService shortLinksService;


    @Autowired
    private HttpServletRequest request;

    @PostMapping("/shrt")
    public ResponseEntity<String> shortL(@Validated @RequestBody ShortLinksCreateDTO link){
        String result = shortLinksService.createShortLink(link);
        System.out.println(request.toString());
        switch (result) {
            case "1":
                return ResponseEntity.ok().body("O código informado não pode ser usado tente novamente");        
            case "2":
                return ResponseEntity.ok().body("ERRO");
            default:
                return ResponseEntity.ok().body(result);
        }
    }
}

