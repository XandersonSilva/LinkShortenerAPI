package edu.xanderson.linkShortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.xanderson.linkShortener.model.UserEntity;
import edu.xanderson.linkShortener.model.DTOs.AuthRegisterDTO;
import edu.xanderson.linkShortener.model.repository.UserRepository;



@Service
public class AuthService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }


    public boolean register(AuthRegisterDTO auth){
        if (userRepository.findByEmail(auth.getEmail()) != null) {
            return false;
        }
        String password = passwordEncoder.encode(auth.getPassword());

        userRepository.save(new UserEntity(auth.getEmail(), auth.getName(), password));
        return true;
    }
}