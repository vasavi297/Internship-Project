package com.careerpathai.dashboard;

import com.careerpathai.aws.dynamodb.DynamoDbService;
import com.careerpathai.model.ResumeMetadata;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DashboardService {

    private final DynamoDbService dynamoDbService;

    public DashboardService(DynamoDbService dynamoDbService) {
        this.dynamoDbService = dynamoDbService;
    }

    public DashboardResponse getDashboard(String userId) {

        List<ResumeMetadata> resumes =
                dynamoDbService.getResumesByUser(userId);

        DashboardResponse response =
                new DashboardResponse();

        response.setTotalResumes(resumes.size());

        if (resumes.isEmpty()) {

            response.setCandidateName("User");
            response.setResumeScore(0);
            response.setCareerMatch(0);
            response.setMissingSkillCount(0);
            response.setRecommendedCareer("No Analysis Yet");
            response.setLatestResume("-");
            response.setLastAnalyzed("-");

            return response;

        }

        ResumeMetadata latest = null;

        for (ResumeMetadata resume : resumes) {

            if ("ANALYZED".equals(resume.getStatus())) {

                latest = resume;
                break;

            }

        }

        if (latest == null) {

            latest = resumes.get(0);

        }

        response.setCandidateName(
                latest.getCandidateName() == null
                        ? "User"
                        : latest.getCandidateName());

        response.setResumeScore(
                latest.getResumeScore() == null
                        ? 0
                        : latest.getResumeScore());

        response.setCareerMatch(
                latest.getCareerMatch() == null
                        ? 0
                        : latest.getCareerMatch());

        response.setMissingSkillCount(
                latest.getMissingSkillCount() == null
                        ? 0
                        : latest.getMissingSkillCount());

        response.setRecommendedCareer(
                latest.getRecommendedCareer() == null
                        ? "-"
                        : latest.getRecommendedCareer());
        response.setSalary("₹6-12 LPA");
        response.setDifficulty("Intermediate");
        response.setDuration("4-6 Months");
        

        response.setLatestResume(
                latest.getFileName());

        if (latest.getAnalyzedTime() != null) {

            response.setLastAnalyzed(

                    latest.getAnalyzedTime()

                            .format(

                                    DateTimeFormatter.ofPattern(

                                            "dd MMM yyyy HH:mm"

                                    )

                            )

            );

        } else {

            response.setLastAnalyzed("-");

        }

        return response;

    }

}