import api from "./api";

class ResumeService {

    analyzeResume(file, onUploadProgress) {

        const formData = new FormData();

        formData.append("file", file);

        return api.post(

            "/resume/analyze",

            formData,

            {

                headers: {

                    "Content-Type": "multipart/form-data"

                },

                onUploadProgress: (event) => {

                    if (!event.total) {

                        return;

                    }

                    const progress = Math.round(

                        (event.loaded * 100) / event.total

                    );

                    onUploadProgress(progress);

                }

            }

        );

    }

    getAllResumes() {

        return api.get("/resume/all");

    }

    getResume(resumeId) {

        return api.get(`/resume/${resumeId}`);

    }

    deleteResume(resumeId) {

        return api.delete(`/resume/${resumeId}`);

    }

}

const resumeService = new ResumeService();

export default resumeService;