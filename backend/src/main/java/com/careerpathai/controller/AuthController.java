package com.careerpathai.controller;
import com.careerpathai.auth.ConfirmRequest;
import com.careerpathai.auth.AuthResponse;
import com.careerpathai.auth.CognitoService;
import com.careerpathai.auth.LoginRequest;
import com.careerpathai.auth.RegisterRequest;
import com.careerpathai.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final CognitoService cognitoService;

    public AuthController(CognitoService cognitoService) {
        this.cognitoService = cognitoService;
    }

    @PostMapping("/register")
    public ApiResponse<String> register(@RequestBody RegisterRequest request) {

        String message = cognitoService.register(request);

        return new ApiResponse<>(
                true,
                message,
                null
        );
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@RequestBody LoginRequest request) {

        AuthResponse response = cognitoService.login(request);

        return new ApiResponse<>(
                true,
                "Login Successful",
                response
        );
    }
    @PostMapping("/confirm")
    public ApiResponse<String> confirm(@RequestBody ConfirmRequest request) {

        String message = cognitoService.confirmUser(request);

        return new ApiResponse<>(
                true,
                message,
                null
        );
    }
}