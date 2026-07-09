package com.careerpathai.dashboard;

import com.careerpathai.auth.JwtTokenService;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    private final JwtTokenService jwtTokenService;

    public DashboardController(
            DashboardService dashboardService,
            JwtTokenService jwtTokenService) {

        this.dashboardService = dashboardService;
        this.jwtTokenService = jwtTokenService;

    }

    @GetMapping
    public DashboardResponse getDashboard(
            JwtAuthenticationToken authentication) {

        String userId =
                jwtTokenService.getUserId(authentication);

        return dashboardService.getDashboard(userId);

    }

}