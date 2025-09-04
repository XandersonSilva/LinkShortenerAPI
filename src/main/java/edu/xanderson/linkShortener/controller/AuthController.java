package edu.xanderson.linkShortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.xanderson.linkShortener.service.AuthService;
import edu.xanderson.linkShortener.infra.security.TokenService;
import edu.xanderson.linkShortener.model.UserEntity;
import edu.xanderson.linkShortener.model.DTOs.AuthDTO;
import edu.xanderson.linkShortener.model.DTOs.AuthRegisterDTO;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Validated @RequestBody AuthRegisterDTO auth){
        if (! authService.register(auth)) {
            // Email requerido em uso
            return ResponseEntity.badRequest().body("ERRO");
        }
        return ResponseEntity.ok().body("Usuário registrado.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Validated @RequestBody AuthDTO auth){
        UsernamePasswordAuthenticationToken emailPassword;
        emailPassword = new UsernamePasswordAuthenticationToken(auth.getEmail(),
                                                                auth.getPassword());
        Authentication authentication = authenticationManager.authenticate(emailPassword);

        String token = tokenService.generateToken((UserEntity) authentication.getPrincipal());

        return ResponseEntity.ok().body(token);
    }
}
