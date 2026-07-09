import { useAnalysis } from "../../context/AnalysisContext";

import "../../styles/components/ScoreCard.css";

function ScoreCard() {
    const { analysis } = useAnalysis();

    const { overallScore, missingSections } = analysis.resumeScore;

    const getStatus = () => {
        if (overallScore >= 85) {
            return "Excellent";
        }

        if (overallScore >= 70) {
            return "Good";
        }

        if (overallScore >= 50) {
            return "Average";
        }

        return "Needs Improvement";
    };

    return (
        <div className="score-card">
            <div className="score-header">
                <h3>Resume Score</h3>
                <span>{getStatus()}</span>
            </div>

            <div className="score-circle">
                <svg viewBox="0 0 120 120">
                    <circle
                        className="score-bg"
                        cx="60"
                        cy="60"
                        r="52"
                    />

                    <circle
                        className="score-progress"
                        cx="60"
                        cy="60"
                        r="52"
                        strokeDasharray={327}
                        strokeDashoffset={
                            327 - (327 * overallScore) / 100
                        }
                    />
                </svg>

                <div className="score-value">
                    {overallScore}
                </div>
            </div>

            <div className="missing-section">
                <h4>Missing Sections</h4>

                {
                    missingSections.length === 0 ? (
                        <p>None</p>
                    ) : (
                        <ul>
                            {missingSections.map((section) => (
                                <li key={section}>
                                    {section}
                                </li>
                            ))}
                        </ul>
                    )
                }
            </div>
        </div>
    );
}

export default ScoreCard;