# CareerPathAI

## AI-Powered Career Recommendation System

CareerPathAI is an intelligent web application designed to help users identify suitable career paths based on their resumes. The system analyzes resumes, extracts technical skills using Artificial Intelligence, identifies skill gaps, and generates personalized learning roadmaps to help users achieve their career goals.

The application combines modern web technologies with cloud computing and AI services to provide a scalable and efficient career guidance platform.

---

# Project Objectives

- Analyze uploaded resumes.
- Extract technical skills automatically.
- Identify missing skills for target careers.
- Recommend suitable career paths.
- Generate personalized learning roadmaps.
- Securely store and manage user resumes using AWS Cloud.

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
      ┌───────────────┼────────────────┐
      ▼               ▼                ▼
 Amazon S3      Amazon DynamoDB   Amazon Cognito
      │
      ▼
 Resume Storage
      │
      ▼
 Python FastAPI (AI Engine)
      │
      ▼
 Resume Parsing
      │
      ▼
 Skill Extraction
      │
      ▼
 Skill Gap Analysis
      │
      ▼
 Career Recommendation
      │
      ▼
 Learning Roadmap
```

---

# Technology Stack

## Backend

- Java 21
- Spring Boot
- Spring Security
- JWT Authentication
- Maven
- Swagger OpenAPI

## Frontend

- React
- HTML
- CSS
- JavaScript

## AI Services

- Python FastAPI
- Stanford CoreNLP
- Apache OpenNLP
- Apache PDFBox

## AWS Cloud Services

- Amazon S3
- Amazon DynamoDB
- Amazon Cognito
- Amazon API Gateway
- Amazon EC2

---

# Features

## Resume Management

- Upload Resume (PDF/DOCX)
- View Uploaded Resumes
- Search Resume
- Delete Resume

## AI Resume Analysis

- Resume Parsing
- Skill Extraction
- Education Detection
- Experience Detection

## Career Recommendation

- AI-Based Career Prediction
- Industry Skill Matching
- Career Compatibility Analysis

## Skill Gap Analysis

- Compare Resume Skills with Industry Requirements
- Identify Missing Skills
- Suggest Skill Improvements

## Learning Roadmap

Generate personalized learning plans including:

- Courses
- Certifications
- Practice Projects
- Learning Timeline

## Authentication

- User Registration
- User Login
- JWT Authentication
- Secure APIs
- AWS Cognito Integration

---

# AWS Services Used

| AWS Service | Purpose |
|-------------|---------|
| Amazon S3 | Store uploaded resumes |
| Amazon DynamoDB | Store resume metadata |
| Amazon Cognito | User authentication |
| Amazon API Gateway | API management |
| Amazon EC2 | Application deployment |

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
│   ├── model
│   ├── dto
│   ├── response
│   ├── exception
│   ├── util
│   └── aws
│       ├── s3
│       ├── dynamodb
│       └── cognito
│
├── frontend
│
├── ai-services
│
└── README.md
```

---

# Current Progress

## Completed

- Spring Boot Backend Setup
- REST API Development
- Swagger API Documentation
- Global Exception Handling
- Standard API Response
- Resume Upload API
- Amazon S3 Integration
- Amazon DynamoDB Integration
- Resume CRUD Operations

## In Progress

- Amazon Cognito Authentication
- Resume Parsing
- Skill Extraction

## Planned

- Career Recommendation Engine
- Skill Gap Analysis
- Learning Roadmap Generation
- React Dashboard
- EC2 Deployment
- API Gateway Integration

---

# Future Enhancements

- Resume Score Prediction
- Interview Question Generator
- AI Chatbot
- ATS Score Calculator
- Job Recommendation System
- LinkedIn Profile Analysis
- Email Notifications
- Multi-language Resume Analysis

---

# License

This project is developed for educational and internship purposes.