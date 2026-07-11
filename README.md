# CareerPathAI

<div align="center">

### AI-Powered Career Recommendation & Resume Analysis Platform

Analyze resumes, identify skill gaps, recommend career paths, and generate personalized learning roadmaps using Artificial Intelligence and AWS Cloud.

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4-green)
![React](https://img.shields.io/badge/React-18-blue)
![AWS](https://img.shields.io/badge/AWS-Cloud-orange)
![Docker](https://img.shields.io/badge/Docker-Containerized-blue)
![License](https://img.shields.io/badge/License-Educational-lightgrey)

</div>

---

# Overview

CareerPathAI is an AI-powered web application that helps users discover suitable career paths based on their resumes.

The system analyzes uploaded resumes, extracts skills, identifies missing competencies, recommends career opportunities, and provides personalized learning roadmaps to bridge skill gaps.

The application follows a modern cloud-native architecture using Spring Boot, React, Docker, and Amazon Web Services (AWS).

---

# Key Features

## Authentication

- User Registration
- Email Verification
- User Login
- Forgot Password
- Reset Password
- AWS Cognito Authentication
- JWT Authentication

---

## Resume Management

- Upload Resume (PDF/DOCX)
- Resume Preview
- Resume History
- Resume Details
- Delete Resume

---

## Dashboard

- Candidate Information
- Resume Score
- Career Match Percentage
- Missing Skills Count
- Recommended Career
- Resume Statistics

---

## AI Resume Analysis

- Resume Parsing
- Skill Extraction
- Education Detection
- Experience Detection
- Career Matching
- Resume Scoring

---

## Learning Roadmap

- Personalized Learning Plan
- Recommended Skills
- Certifications
- Practice Projects
- Estimated Learning Duration

---

## Cloud Integration

- Amazon S3 for Resume Storage
- Amazon DynamoDB for Resume Metadata
- Amazon Cognito for Authentication
- Amazon EC2 Deployment

---

# System Architecture

```text
                        User
                          │
                          ▼
                 React Frontend
                          │
                          ▼
                Spring Boot Backend
                          │
        ┌─────────────────┼──────────────────┐
        ▼                 ▼                  ▼
   Amazon S3        Amazon DynamoDB    Amazon Cognito
        │
        ▼
 Resume Storage & Metadata
        │
        ▼
 AI Resume Processing
        │
        ▼
 Skill Extraction
        │
        ▼
 Career Recommendation
        │
        ▼
 Personalized Learning Roadmap
```

---

# Tech Stack

## Frontend

- React.js
- JavaScript
- HTML5
- CSS3
- Axios
- React Router

---

## Backend

- Java 21
- Spring Boot 3
- Spring Security
- JWT
- REST APIs
- Maven
- Swagger OpenAPI

---

## AI & Resume Processing

- Apache PDFBox
- Apache POI
- Stanford CoreNLP
- Apache OpenNLP

---

## Cloud Services

- Amazon EC2
- Amazon S3
- Amazon DynamoDB
- Amazon Cognito

---

## DevOps

- Docker
- Docker Compose
- Git
- GitHub

---

# Project Structure

```text
CareerPathAI
│
├── backend
│   ├── controller
│   ├── service
│   ├── repository
│   ├── config
│   ├── dto
│   ├── model
│   ├── response
│   ├── util
│   ├── auth
│   └── aws
│       ├── s3
│       ├── dynamodb
│       └── cognito
│
├── frontend
│   ├── components
│   ├── pages
│   ├── services
│   ├── assets
│   └── utils
│
├── docker-compose.yml
│
└── README.md
```

---

# AWS Services Used

| Service | Purpose |
|----------|----------|
| Amazon EC2 | Application Hosting |
| Amazon S3 | Resume Storage |
| Amazon DynamoDB | Resume Metadata Storage |
| Amazon Cognito | User Authentication |

---

# Docker Deployment

The entire application is containerized using Docker.

### Backend

- Spring Boot Container

### Frontend

- React Application
- Nginx Web Server

### Docker Compose

Docker Compose is used to orchestrate the complete application stack.

Run the application using:

```bash
docker compose up -d --build
```

---

# API Documentation

Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

or

```
http://<EC2-PUBLIC-IP>:8080/swagger-ui/index.html
```

---

# Installation

## Clone Repository

```bash
git clone https://github.com/vasavi297/Internship-Project.git
```

---

## Backend

```bash
cd backend

mvn clean install

mvn spring-boot:run
```

---

## Frontend

```bash
cd frontend

npm install

npm start
```

---

## Docker

```bash
docker compose up -d --build
```

---
# Live Demo

**Application**

[![Live Demo](https://img.shields.io/badge/Live-Demo-success?style=for-the-badge&logo=google-chrome)](http://13.205.249.127:3000)

---

# Future Enhancements

- ATS Resume Score
- AI Interview Question Generator
- Job Recommendation Engine
- Resume Ranking
- Email Notifications
- Learning Progress Tracking
- AI Career Chatbot
- Multi-language Resume Analysis

---

# Learning Outcomes

During the development of this project, the following technologies were explored:

- Spring Boot REST APIs
- React Frontend Development
- AWS Cloud Services
- Docker Containerization
- Docker Compose
- JWT Authentication
- Amazon Cognito
- Amazon S3 Integration
- Amazon DynamoDB Integration
- Cloud Deployment using Amazon EC2

# License

This project was developed for educational and internship purposes.
