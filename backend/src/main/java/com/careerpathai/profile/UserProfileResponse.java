package com.careerpathai.profile;

public class UserProfileResponse {

    private String name;
    private String email;
    private String initials;

    public UserProfileResponse() {
    }

    public UserProfileResponse(String name, String email, String initials) {
        this.name = name;
        this.email = email;
        this.initials = initials;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }
}