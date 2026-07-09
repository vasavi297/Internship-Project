package com.careerpathai.profile;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class UserProfileController {

    private final UserProfileService profileService;

    public UserProfileController(
            UserProfileService profileService) {

        this.profileService = profileService;

    }

    @GetMapping
    public UserProfileResponse getProfile(
            JwtAuthenticationToken authentication) {

        return profileService.getProfile(authentication);

    }

}