package com.careerpathai.parser;

import java.util.ArrayList;
import java.util.List;

public class ParsedResume {

    private String name;
    private String email;
    private String phone;
    private List<String> skills = new ArrayList<>();
    private List<String> education = new ArrayList<>();
    private List<String> experience = new ArrayList<>();
    private List<String> projects = new ArrayList<>();
    private List<String> certifications = new ArrayList<>();
    private String resumeText;

    public ParsedResume() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<String> getEducation() {
        return education;
    }

    public void setEducation(List<String> education) {
        this.education = education;
    }

    public List<String> getExperience() {
        return experience;
    }

    public void setExperience(List<String> experience) {
        this.experience = experience;
    }
    public List<String> getProjects() {
    return projects;
    }

    public void setProjects(List<String> projects) {
    this.projects = projects;
    }

    public List<String> getCertifications() {
    return certifications;
    }

    public void setCertifications(List<String> certifications) {
    this.certifications = certifications;
    }

    public String getResumeText() {
        return resumeText;
    }

    public void setResumeText(String resumeText) {
        this.resumeText = resumeText;
    }
}