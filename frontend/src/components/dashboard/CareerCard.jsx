import "../../styles/components/CareerCard.css";

function CareerCard({ dashboard }) {

    return (

        <div className="career-card">

            <h3>Recommended Career</h3>

            <h2>

                {dashboard.recommendedCareer || "No Recommendation Yet"}

            </h2>

            <p className="career-message">

                {
                    dashboard.recommendedCareer
                        ? "Based on your latest resume analysis."
                        : "Upload and analyze a resume to receive an AI-powered career recommendation."
                }

            </p>

        </div>

    );

}

export default CareerCard;