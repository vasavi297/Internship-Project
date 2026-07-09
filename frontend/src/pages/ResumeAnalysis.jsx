import { useEffect } from "react";
import { useParams } from "react-router-dom";

import { useAnalysis } from "../context/AnalysisContext";

import ResumeService from "../services/ResumeService";

import CandidateCard from "../components/analysis/CandidateCard";
import ScoreCard from "../components/analysis/ScoreCard";
import CareerCard from "../components/analysis/CareerCard";
import SkillsCard from "../components/analysis/SkillsCard";
import RoadmapCard from "../components/analysis/RoadmapCard";

import "../styles/pages/Analysis.css";

function ResumeAnalysis() {
   const { resumeId } = useParams();

    const {
    analysis,
    setAnalysis,
    loading,
    setLoading
    } = useAnalysis();
    useEffect(() => {

    if (resumeId) {
        loadResume();
    }

}, [resumeId]);

    

const loadResume = async () => {
    try {
        setLoading(true);

        const response =
            await ResumeService.getResume(resumeId);

        setAnalysis(response.data);
    } catch (error) {
        console.error(error);
    } finally {
        setLoading(false);
    }
};
    if (loading) {
    return (
        <div className="analysis-empty">
            <h2>Loading Resume...</h2>
        </div>
    );
}


    if (!analysis) {
        return (
            <div className="analysis-empty">
                <h2>No Resume Analysis Available</h2>
                <p>Upload a resume to view AI analysis.</p>
            </div>
        );
    }

    return (
    <div className="analysis-page">
        <div className="analysis-header">
            <h1>Resume Analysis</h1>

            <p>
                AI-powered insights generated from your uploaded resume.
            </p>
        </div>

        <div className="analysis-top-grid">
            <CandidateCard />
            <ScoreCard />
        </div>

        <CareerCard />

        <SkillsCard />

        <RoadmapCard />
    </div>
);
}

export default ResumeAnalysis;