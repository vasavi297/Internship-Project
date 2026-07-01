package com.careerpathai.service;

import com.careerpathai.response.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public class HomeService {

    public ApiResponse<String> getHome() {

        return new ApiResponse<>(
                true,
                "Application Started Successfully",
                "CareerPathAI Backend Running 🚀"
        );
    }

    public ApiResponse<String> getHealth() {

        return new ApiResponse<>(
                true,
                "Application Healthy",
                "Status : UP"
        );
    }
}