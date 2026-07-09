import { useAnalysis } from "../../context/AnalysisContext";

import "../../styles/components/CareerCard.css";

function CareerCard() {
    const { analysis } = useAnalysis();

    const {
        careerName,
        matchedSkills,
        totalSkills,
        matchPercentage,
        matchedSkillNames
    } = analysis.recommendedCareer;

    return (
        <div className="career-card">
            <div className="career-header">
                <h3>Recommended Career</h3>

                <span>{matchPercentage.toFixed(1)}%</span>
            </div>

            <h2>{careerName}</h2>

            <p>
                Your resume matches{" "}
                <strong>
                    {matchedSkills} of {totalSkills}
                </strong>{" "}
                required skills.
            </p>

            <div className="career-progress">
                <div
                    className="career-progress-fill"
                    style={{
                        width: `${matchPercentage}%`
                    }}
                ></div>
            </div>

            <h4>Matched Skills</h4>

            <div className="skill-list">
                {matchedSkillNames.length === 0 ? (
                    <span className="empty-skill">
                        No matching skills found.
                    </span>
                ) : (
                    matchedSkillNames.map((skill) => (
                        <span
                            key={skill}
                            className="skill-chip"
                        >
                            {skill}
                        </span>
                    ))
                )}
            </div>
        </div>
    );
}

export default CareerCard;