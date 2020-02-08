package com.example.supplychain.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationTokenFilter() {
        super("/supplychain/**");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
       try {
           String authHeader = request.getHeader("Authorization");

           if(authHeader == null || authHeader.startsWith("Bearer ")) {
               throw new IOException("Authentication Failed. JWT token is missing");
           }
           String authorizationToken = authHeader.substring(6);
           JWTAuthenticationToken token = new JWTAuthenticationToken(authorizationToken);
           return getAuthenticationManager().authenticate(token);
       } catch(Exception e) {
            e.printStackTrace();
       }
       return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
