package com.careerpathai.career.controller;

import com.careerpathai.career.model.CareerRecommendation;
import com.careerpathai.career.service.CareerRecommendationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/career")
public class CareerController {

    private final CareerRecommendationService service;

    public CareerController(CareerRecommendationService service) {
        this.service = service;
    }

    @PostMapping("/recommend")
    public List<CareerRecommendation> recommend(
            @RequestBody List<String> skills) {

        return service.recommendCareers(skills);
    }
}