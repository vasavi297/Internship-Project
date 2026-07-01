package com.careerpathai.controller;

import com.careerpathai.response.ApiResponse;
import com.careerpathai.service.HomeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/")
    public ApiResponse<String> home() {
        return homeService.getHome();
    }

    @GetMapping("/health")
    public ApiResponse<String> health() {
        return homeService.getHealth();
    }
}