package edu.xanderson.linkShortener.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import edu.xanderson.linkShortener.model.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;


    @Override
    protected void doFilterInternal(
                                    @NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain
                                ) throws ServletException, IOException {
        String token = this.recoverToken(request);
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }
        String login = tokenService.verifyToken(token);
        UserDetails user = userRepository.findByEmail(login);

        UsernamePasswordAuthenticationToken authenticade = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticade);;
    }


    public String recoverToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        
        if (authHeader == null) {
            return null;
        }
        return authHeader.replace("Bearer", "");
    }
}
