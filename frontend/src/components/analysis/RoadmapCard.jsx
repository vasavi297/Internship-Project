import { useAnalysis } from "../../context/AnalysisContext";

import "../../styles/components/RoadmapCard.css";

function RoadmapCard() {
    const { analysis } = useAnalysis();

    const { roadmap } = analysis;

    return (
        <div className="roadmap-card">
            <div className="roadmap-header">
                <h2>Learning Roadmap</h2>

                <span>{roadmap.career}</span>
            </div>

            <div className="timeline">
                {roadmap.roadmap.map((week) => (
                    <div
                        key={week.week}
                        className="timeline-item"
                    >
                        <div className="timeline-circle">
                            {week.week}
                        </div>

                        <div className="timeline-content">
                            <h4>Week {week.week}</h4>

                            <div className="topic-list">
                                {week.topics.map((topic) => (
                                    <span
                                        key={topic}
                                        className="topic-chip"
                                    >
                                        {topic}
                                    </span>
                                ))}
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default RoadmapCard;