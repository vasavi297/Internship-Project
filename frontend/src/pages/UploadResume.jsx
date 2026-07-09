import { useState } from "react";
import { useNavigate } from "react-router-dom";

import FileUploader from "../components/resume/FileUploader";
import UploadProgress from "../components/resume/UploadProgress";

import ResumeService from "../services/ResumeService";
import { useAnalysis } from "../context/AnalysisContext";

import "../styles/pages/UploadResume.css";

function UploadResume() {

    const navigate = useNavigate();

    const {
        setAnalysis,
        setLoading
    } = useAnalysis();

    const [selectedFile, setSelectedFile] = useState(null);

    const [progress, setProgress] = useState(0);

    const [uploading, setUploading] = useState(false);

    const [error, setError] = useState("");

    const analyzeResume = async () => {

        if (!selectedFile) {

            setError("Please choose a resume.");

            return;

        }

        try {

            setUploading(true);

            setLoading(true);

            setError("");

            const response =
                await ResumeService.analyzeResume(

                    selectedFile,

                    setProgress

                );

            setAnalysis(response.data);

            navigate(`/analysis/${response.data.resumeId}`);

        }

        catch (exception) {

            console.error(exception);

            setError(

                "Resume analysis failed. Please try again."

            );

        }

        finally {

            setUploading(false);

            setLoading(false);

        }

    };

    const previewResume = () => {

        if (!selectedFile) {

            return;

        }

        const fileURL =
            URL.createObjectURL(selectedFile);

        window.open(fileURL, "_blank");

    };

    return (

        <div className="upload-page">

            <div className="upload-header">

                <h1>

                    Resume Analysis

                </h1>

                <p>

                    Upload your resume and receive an AI-powered
                    resume score, career recommendation,
                    skill gap analysis and personalized roadmap.

                </p>

            </div>

            <FileUploader

                onFileSelect={(file) => {

                    setSelectedFile(file);

                    setError("");

                }}

            />

            {

                selectedFile && (

                    <div className="selected-resume">

                        <h3>

                            Selected Resume

                        </h3>

                        <span
                            className="file-name"
                            onClick={previewResume}
                        >

                            📄 {selectedFile.name}

                        </span>

                        <p className="preview-text">

                            Click the file name to preview your
                            resume before analysis.

                        </p>

                    </div>

                )

            }

            {

                uploading && (

                    <UploadProgress

                        progress={progress}

                        status="Uploading Resume..."

                    />

                )

            }

            {

                error && (

                    <div className="upload-error">

                        {error}

                    </div>

                )

            }

            <button

                className="analyze-btn"

                disabled={uploading}

                onClick={analyzeResume}

            >

                {

                    uploading

                        ?

                        "Analyzing..."

                        :

                        "Analyze Resume"

                }

            </button>

        </div>

    );

}

export default UploadResume;