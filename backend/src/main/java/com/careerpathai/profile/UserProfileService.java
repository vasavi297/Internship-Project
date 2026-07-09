package com.careerpathai.profile;

import com.careerpathai.auth.JwtTokenService;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    private final JwtTokenService jwtTokenService;

    public UserProfileService(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    public UserProfileResponse getProfile(
            JwtAuthenticationToken authentication) {

        // Print all JWT claims to the console
        jwtTokenService.printClaims(authentication);

        String email =
                jwtTokenService.getEmail(authentication);

        String name =
                jwtTokenService.getName(authentication);

        if (name == null || name.isBlank()) {
            name = email;
        }

        String initials = "";

        if (name != null && !name.isBlank()) {

            String[] parts = name.trim().split("\\s+");

            if (parts.length == 1) {

                initials = parts[0]
                        .substring(0, 1)
                        .toUpperCase();

            } else {

                initials =
                        (parts[0].substring(0, 1)
                                + parts[1].substring(0, 1))
                                .toUpperCase();

            }

        }

        return new UserProfileResponse(
                name,
                email,
                initials);

    }

}