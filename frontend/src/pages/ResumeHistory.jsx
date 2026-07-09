import { useEffect, useMemo, useState } from "react";

import ResumeService from "../services/ResumeService";

import HistoryTable from "../components/history/HistoryTable";

import "../styles/pages/ResumeHistory.css";

function ResumeHistory() {
    const [resumes, setResumes] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        loadResumes();
    }, []);

    const loadResumes = async () => {
        try {
            const response = await ResumeService.getAllResumes();
            setResumes(response.data);
        } catch (error) {
            console.error(error);
        } finally {
            setLoading(false);
        }
    };

    const statistics = useMemo(() => {
        const total = resumes.length;

        const analyzed = resumes.filter(
            (resume) => resume.status === "ANALYZED"
        ).length;

        const averageScore =
            total === 0
                ? 0
                : Math.round(
                      resumes.reduce(
                          (sum, resume) =>
                              sum + (resume.resumeScore || 0),
                          0
                      ) / total
                  );

        return {
            total,
            analyzed,
            averageScore
        };
    }, [resumes]);

    return (
        <div className="history-page">
            <div className="history-header">
                <h1>Resume History</h1>

                <p>
                    View, manage and track all analyzed resumes.
                </p>
            </div>

            <div className="history-stats">
                <div className="history-stat-card">
                    <h2>{statistics.total}</h2>
                    <span>Total Resumes</span>
                </div>

                <div className="history-stat-card">
                    <h2>{statistics.analyzed}</h2>
                    <span>Analyzed</span>
                </div>

                <div className="history-stat-card">
                    <h2>{statistics.averageScore}</h2>
                    <span>Average Score</span>
                </div>
            </div>

            <HistoryTable
                resumes={resumes}
                loading={loading}
                reload={loadResumes}
            />
        </div>
    );
}

export default ResumeHistory;