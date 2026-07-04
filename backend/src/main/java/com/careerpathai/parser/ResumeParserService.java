package com.careerpathai.parser;

import com.careerpathai.parser.extractor.CertificationExtractor;
import com.careerpathai.parser.extractor.EducationExtractor;
import com.careerpathai.parser.extractor.EmailExtractor;
import com.careerpathai.parser.extractor.ExperienceExtractor;
import com.careerpathai.parser.extractor.NameExtractor;
import com.careerpathai.parser.extractor.PhoneExtractor;
import com.careerpathai.parser.extractor.ProjectExtractor;
import com.careerpathai.parser.extractor.SkillExtractor;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class ResumeParserService {

    private final EmailExtractor emailExtractor;
    private final PhoneExtractor phoneExtractor;
    private final NameExtractor nameExtractor;
    private final SkillExtractor skillExtractor;
    private final EducationExtractor educationExtractor;
    private final ExperienceExtractor experienceExtractor;
    private final ProjectExtractor projectExtractor;
    private final CertificationExtractor certificationExtractor;

    public ResumeParserService(
            EmailExtractor emailExtractor,
            PhoneExtractor phoneExtractor,
            NameExtractor nameExtractor,
            SkillExtractor skillExtractor,
            EducationExtractor educationExtractor,
            ExperienceExtractor experienceExtractor,
            ProjectExtractor projectExtractor,
            CertificationExtractor certificationExtractor) {

        this.emailExtractor = emailExtractor;
        this.phoneExtractor = phoneExtractor;
        this.nameExtractor = nameExtractor;
        this.skillExtractor = skillExtractor;
        this.educationExtractor = educationExtractor;
        this.experienceExtractor = experienceExtractor;
        this.projectExtractor = projectExtractor;
        this.certificationExtractor = certificationExtractor;
    }

    public ParsedResume parseResume(File file) throws IOException {

        PDDocument document = Loader.loadPDF(file);

        PDFTextStripper stripper = new PDFTextStripper();

        String text = stripper.getText(document);

        document.close();

        ParsedResume resume = new ParsedResume();

        resume.setResumeText(text);
        resume.setName(nameExtractor.extract(text));
        resume.setEmail(emailExtractor.extract(text));
        resume.setPhone(phoneExtractor.extract(text));
        resume.setSkills(skillExtractor.extract(text));
        resume.setEducation(educationExtractor.extract(text));
        resume.setExperience(experienceExtractor.extract(text));
        resume.setProjects(projectExtractor.extract(text));
        resume.setCertifications(certificationExtractor.extract(text));

        return resume;
    }
}