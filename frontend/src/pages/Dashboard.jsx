import { useEffect, useState } from "react";

import StatsCard from "../components/dashboard/StatsCard";
import CareerCard from "../components/dashboard/CareerCard";
import UploadCard from "../components/resume/UploadCard";

import AuthService from "../services/AuthService";
import DashboardService from "../services/DashboardService";
import LoadingSpinner from "../components/common/LoadingSpinner";

import "../styles/pages/Dashboard.css";

function Dashboard() {

    const [dashboard, setDashboard] = useState(null);

    // Decode ID Token
    const user = AuthService.getUser();

    const displayName =
        user?.name ||
        user?.email ||
        "User";

    useEffect(() => {

        loadDashboard();

    }, []);

    const loadDashboard = async () => {

        try {

            const response =
                await DashboardService.getDashboard();

            setDashboard(response.data);

        } catch (error) {

            console.error(error);

        }

    };

    if (!dashboard) {

        return (

            <LoadingSpinner
                text="Loading Dashboard..."
            />

        );

    }

    return (

        <div>

            <div className="dashboard-header">

                <h1>

                    Welcome back,

                    {" "}

                    {

                        dashboard.candidateName &&
                        dashboard.candidateName !== "User"

                            ? dashboard.candidateName

                            : displayName

                    }

                </h1>

                <p>

                    Monitor your resume performance and career growth.

                </p>

            </div>

            <div className="stats-grid">

                <StatsCard
                    title="Resume Score"
                    value={`${dashboard.resumeScore}%`}
                    subtitle="Latest Analysis"
                    color="#2563EB"
                />

                <StatsCard
                    title="Career Match"
                    value={`${dashboard.careerMatch}%`}
                    subtitle={dashboard.recommendedCareer}
                    color="#10B981"
                />

                <StatsCard
                    title="Missing Skills"
                    value={dashboard.missingSkillCount}
                    subtitle="Need Attention"
                    color="#F59E0B"
                />

                <StatsCard
                    title="Total Resumes"
                    value={dashboard.totalResumes}
                    subtitle={dashboard.latestResume}
                    color="#8B5CF6"
                />

            </div>

            <div className="dashboard-grid">

                <UploadCard />

                <CareerCard dashboard={dashboard} />

            </div>

        </div>

    );

}

export default Dashboard;