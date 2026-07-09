import { useAnalysis } from "../../context/AnalysisContext";

import "../../styles/components/CandidateCard.css";

function CandidateCard() {
    const { analysis } = useAnalysis();
    const { parsedResume } = analysis;

    return (
    <div className="candidate-card">
        <div className="candidate-profile">
            <div className="candidate-avatar">
                {parsedResume.name.charAt(0).toUpperCase()}
            </div>

            <div>
                <h3>{parsedResume.name}</h3>

                <p>Resume Owner</p>
            </div>
        </div>

        <div className="candidate-details">
            <div className="candidate-row">
                <span>Email</span>

                <strong>{parsedResume.email}</strong>
            </div>

            <div className="candidate-row">
                <span>Phone</span>

                <strong>{parsedResume.phone}</strong>
            </div>
        </div>
    </div>
);
}

export default CandidateCard;