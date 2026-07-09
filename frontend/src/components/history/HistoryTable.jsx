import { useMemo, useState } from "react";
import { useNavigate } from "react-router-dom";

import ResumeService from "../../services/ResumeService";

import "../../styles/components/HistoryTable.css";

function HistoryTable({ resumes, loading, reload }) {

    const navigate = useNavigate();

    const [search, setSearch] = useState("");

    const filteredResumes = useMemo(() => {

        const keyword = search.toLowerCase();

        return resumes.filter((resume) =>

            (resume.candidateName || "")
                .toLowerCase()
                .includes(keyword)

            ||

            (resume.email || "")
                .toLowerCase()
                .includes(keyword)

            ||

            (resume.recommendedCareer || "")
                .toLowerCase()
                .includes(keyword)

        );

    }, [resumes, search]);

    const deleteResume = async (resumeId) => {

        const confirmDelete = window.confirm(
            "Delete this resume permanently?"
        );

        if (!confirmDelete) {
            return;
        }

        try {

            await ResumeService.deleteResume(resumeId);

            await reload();

        } catch (error) {

            console.error(error);

            alert("Unable to delete resume.");

        }

    };

    const viewResume = (resumeId) => {

        navigate(`/analysis/${resumeId}`);

    };

    const getStatusClass = (status) => {

        switch (status) {

            case "ANALYZED":
                return "status-success";

            case "ANALYZING":
                return "status-warning";

            case "FAILED":
                return "status-danger";

            default:
                return "status-default";

        }

    };

    if (loading) {

        return (

            <div className="history-loading">

                <div className="loader"></div>

                <p>Loading Resume History...</p>

            </div>

        );

    }

    return (

        <>

            <div className="history-toolbar">

                <input
                    type="text"
                    placeholder="Search candidate, email or career..."
                    value={search}
                    onChange={(event) =>
                        setSearch(event.target.value)
                    }
                />

            </div>

            {

                filteredResumes.length === 0 ?

                    (

                        <div className="history-empty">

                            <h2>No Resume Found</h2>

                            <p>
                                Upload a resume to begin analysis.
                            </p>

                        </div>

                    )

                    :

                    (

                        <div className="history-table-card">

                            <table className="history-table">

                                <thead>

                                    <tr>

                                        <th>Candidate</th>

                                        <th>Email</th>

                                        <th>Career</th>

                                        <th>Score</th>

                                        <th>Status</th>

                                        <th>Uploaded</th>

                                        <th>Actions</th>

                                    </tr>

                                </thead>

                                <tbody>

                                    {

                                        filteredResumes.map((resume) => (

                                            <tr key={resume.resumeId}>

                                                <td>
                                                    {resume.candidateName || "Unknown"}
                                                </td>

                                                <td>
                                                    {resume.email || "-"}
                                                </td>

                                                <td>
                                                    {resume.recommendedCareer || "-"}
                                                </td>

                                                <td>
                                                    {resume.resumeScore ?? "-"}
                                                </td>

                                                <td>

                                                    <span
                                                        className={`status-badge ${getStatusClass(
                                                            resume.status
                                                        )}`}
                                                    >

                                                        {resume.status}

                                                    </span>

                                                </td>

                                                <td>

                                                    {

                                                        resume.uploadTime ?

                                                            new Date(
                                                                resume.uploadTime
                                                            ).toLocaleDateString()

                                                            :

                                                            "-"

                                                    }

                                                </td>

                                                <td className="action-buttons">

                                                    <button
                                                        className="view-btn"
                                                        onClick={() =>
                                                            viewResume(
                                                                resume.resumeId
                                                            )
                                                        }
                                                    >

                                                        View

                                                    </button>

                                                    <button
                                                        className="delete-btn"
                                                        onClick={() =>
                                                            deleteResume(
                                                                resume.resumeId
                                                            )
                                                        }
                                                    >

                                                        Delete

                                                    </button>

                                                </td>

                                            </tr>

                                        ))

                                    }

                                </tbody>

                            </table>

                        </div>

                    )

            }

        </>

    );

}

export default HistoryTable;