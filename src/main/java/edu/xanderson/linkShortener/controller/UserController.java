package edu.xanderson.linkShortener.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;

import edu.xanderson.linkShortener.model.UserEntity;
import edu.xanderson.linkShortener.model.DTOs.UserDeleteDTO;
import edu.xanderson.linkShortener.service.UserService;
@RestController 
public class UserController {
    @Autowired
    private UserService userService;

    //  Apagar conta
    @PostMapping("/user/delete")
    public ResponseEntity<String> deleteAccount(@Validated @RequestBody UserDeleteDTO user, @AuthenticationPrincipal UserEntity currentUser){
        if (userService.deleteAccount(user, currentUser)) {
            return ResponseEntity.ok().body("Usuário deletado!");
        }

        return ResponseEntity.badRequest().body("ERRO");
    }
}

