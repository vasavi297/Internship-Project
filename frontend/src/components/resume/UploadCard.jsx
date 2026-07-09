import { FaCloudUploadAlt } from "react-icons/fa";
import { useNavigate } from "react-router-dom";
import "../../styles/components/UploadCard.css";

function UploadCard() {
    const navigate = useNavigate();

    return (
        <div className="upload-card">
            <div className="upload-icon">
                <FaCloudUploadAlt />
            </div>

            <h2>Upload Resume</h2>

            <p>
                Upload your latest resume to receive an AI-powered analysis,
                career recommendations, resume score and a personalized learning roadmap.
            </p>

            <button
                className="upload-btn"
                onClick={() => navigate("/upload")}
            >
                Upload Resume
            </button>
        </div>
    );
}

export default UploadCard;