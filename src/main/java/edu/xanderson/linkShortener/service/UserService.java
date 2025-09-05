package edu.xanderson.linkShortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.xanderson.linkShortener.model.UserEntity;
import edu.xanderson.linkShortener.model.DTOs.UserDeleteDTO;
import edu.xanderson.linkShortener.model.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    private boolean verifyPassword(UserEntity user, String password){
        if (passwordEncoder.matches(password, user.getPassword())) {
            return true;
        }
        return false;
    }


    public boolean deleteAccount(UserDeleteDTO user, UserEntity currentUser){
        if (! verifyPassword(currentUser, user.getPassword())) {
            return false;   
        }
        try {
            userRepository.delete(currentUser);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
}
