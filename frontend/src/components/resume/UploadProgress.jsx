import "../../styles/components/UploadProgress.css";

function UploadProgress({ progress, status }) {
    return (
        <div className="upload-progress">
            <div className="progress-header">
                <span>{status}</span>
                <span>{progress}%</span>
            </div>

            <div className="progress-bar">
                <div
                    className="progress-fill"
                    style={{
                        width: `${progress}%`
                    }}
                ></div>
            </div>
        </div>
    );
}

export default UploadProgress;