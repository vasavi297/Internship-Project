import { useAnalysis } from "../../context/AnalysisContext";

import "../../styles/components/SkillsCard.css";

function SkillsCard() {
    const { analysis } = useAnalysis();

    const { topMissingSkills } = analysis;

    return (
        <div className="skills-card">
            <h3>Skill Gap Analysis</h3>

            <p>
                The following skills are recommended to improve your career
                match.
            </p>

            <div className="missing-skills">
                {topMissingSkills.length === 0 ? (
                    <span className="empty-message">
                        No missing skills found.
                    </span>
                ) : (
                    topMissingSkills.map((skill) => (
                        <span
                            key={skill}
                            className="missing-skill-chip"
                        >
                            {skill}
                        </span>
                    ))
                )}
            </div>
        </div>
    );
}

export default SkillsCard;