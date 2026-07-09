package com.careerpathai.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;


@Service
public class CognitoService {

    private final CognitoIdentityProviderClient cognitoClient;
    

    @Value("${aws.cognito.clientId}")
    private String clientId;

    public CognitoService(CognitoIdentityProviderClient cognitoClient) {
        this.cognitoClient = cognitoClient;
    }

    public String register(RegisterRequest request) {

       SignUpRequest signUpRequest = SignUpRequest.builder()
        .clientId(clientId)
        .username(request.getEmail())
        .password(request.getPassword())
        .userAttributes(
                AttributeType.builder()
                        .name("email")
                        .value(request.getEmail())
                        .build()
        )
        .build();

        cognitoClient.signUp(signUpRequest);

        return "User Registered Successfully";
    }

    public AuthResponse login(LoginRequest request) {

        InitiateAuthRequest authRequest =
                InitiateAuthRequest.builder()
                        .authFlow(AuthFlowType.USER_PASSWORD_AUTH)
                        .clientId(clientId)
                        .authParameters(
                                java.util.Map.of(
                                        "USERNAME", request.getEmail(),
                                        "PASSWORD", request.getPassword()
                                )
                        )
                        .build();

        InitiateAuthResponse response =
                cognitoClient.initiateAuth(authRequest);

        AuthenticationResultType result =
                response.authenticationResult();

        return new AuthResponse(
                "Login Successful",
                result.accessToken(),
                result.idToken(),
                result.refreshToken()
        );
    }
    public String confirmUser(ConfirmRequest request) {

        ConfirmSignUpRequest confirmRequest =
                ConfirmSignUpRequest.builder()
                        .clientId(clientId)
                        .username(request.getEmail())
                        .confirmationCode(request.getCode())
                        .build();

        cognitoClient.confirmSignUp(confirmRequest);

        return "User Confirmed Successfully";
    }
public String forgotPassword(ForgotPasswordRequest request) {

    software.amazon.awssdk.services.cognitoidentityprovider.model.ForgotPasswordRequest forgotRequest =
            software.amazon.awssdk.services.cognitoidentityprovider.model.ForgotPasswordRequest.builder()
                    .clientId(clientId)
                    .username(request.getEmail())
                    .build();

    cognitoClient.forgotPassword(forgotRequest);

    return "Verification code sent successfully.";
}

public String resetPassword(ResetPasswordRequest request) {

    ConfirmForgotPasswordRequest confirmRequest =
            ConfirmForgotPasswordRequest.builder()
                    .clientId(clientId)
                    .username(request.getEmail())
                    .confirmationCode(request.getCode())
                    .password(request.getNewPassword())
                    .build();

    cognitoClient.confirmForgotPassword(confirmRequest);

    return "Password reset successful.";

}
}