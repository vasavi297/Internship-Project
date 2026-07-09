package com.careerpathai.auth;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenService {

    public void printClaims(JwtAuthenticationToken authentication) {

        Jwt jwt = authentication.getToken();

        System.out.println("========== JWT CLAIMS ==========");
        System.out.println(jwt.getClaims());
        System.out.println("================================");
    }

    public String getUserId(JwtAuthenticationToken authentication) {

        Jwt jwt = authentication.getToken();

        return jwt.getClaimAsString("sub");
    }

    public String getEmail(JwtAuthenticationToken authentication) {

        Jwt jwt = authentication.getToken();

        return jwt.getClaimAsString("email");
    }

    public String getName(JwtAuthenticationToken authentication) {

        Jwt jwt = authentication.getToken();

        String name = jwt.getClaimAsString("name");

        if (name == null || name.isBlank()) {

            name = jwt.getClaimAsString("email");

        }

        return name;
    }
}