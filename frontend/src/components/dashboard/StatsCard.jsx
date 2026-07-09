import {
    FaChartLine,
    FaBullseye,
    FaExclamationTriangle,
    FaFileAlt
} from "react-icons/fa";

import "../../styles/components/StatsCard.css";

function StatsCard({
    title,
    value,
    subtitle,
    color
}) {

    const getIcon = () => {

        switch (title) {

            case "Resume Score":
                return <FaChartLine />;

            case "Career Match":
                return <FaBullseye />;

            case "Missing Skills":
                return <FaExclamationTriangle />;

            case "Total Resumes":
                return <FaFileAlt />;

            default:
                return <FaChartLine />;

        }

    };

    return (

        <div className="stats-card">

            <div
                className="stats-icon"
                style={{
                    background: `${color}15`,
                    color
                }}
            >

                {getIcon()}

            </div>

            <div className="stats-content">

                <h4>

                    {title}

                </h4>

                <h2>

                    {value}

                </h2>

                <p>

                    {subtitle}

                </p>

            </div>

        </div>

    );

}

export default StatsCard;