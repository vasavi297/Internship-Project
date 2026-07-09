import axios from "axios";

const API_BASE_URL = "http://localhost:8080/resume";

class ResumeService {

    getHeaders() {

        const accessToken =
            localStorage.getItem("accessToken");

        return {

            Authorization: `Bearer ${accessToken}`

        };

    }

    analyzeResume(file, onUploadProgress) {

        const formData = new FormData();

        formData.append("file", file);

        return axios.post(

            `${API_BASE_URL}/analyze`,

            formData,

            {

                headers: {

                    ...this.getHeaders(),

                    "Content-Type":
                        "multipart/form-data"

                },

                onUploadProgress: (event) => {

                    if (!event.total) {

                        return;

                    }

                    const progress = Math.round(

                        (event.loaded * 100) /
                        event.total

                    );

                    onUploadProgress(progress);

                }

            }

        );

    }

    getAllResumes() {

        return axios.get(

            `${API_BASE_URL}/all`,

            {

                headers: this.getHeaders()

            }

        );

    }

    getResume(resumeId) {

        return axios.get(

            `${API_BASE_URL}/${resumeId}`,

            {

                headers: this.getHeaders()

            }

        );

    }

    deleteResume(resumeId) {

        return axios.delete(

            `${API_BASE_URL}/${resumeId}`,

            {

                headers: this.getHeaders()

            }

        );

    }

}

export default new ResumeService();