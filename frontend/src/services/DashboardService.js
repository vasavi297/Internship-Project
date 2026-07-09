import axios from "axios";

const API_URL = "http://localhost:8080/dashboard";

class DashboardService {

    getHeaders() {

        return {

            Authorization: `Bearer ${localStorage.getItem("accessToken")}`

        };

    }

    getDashboard() {

        return axios.get(

            API_URL,

            {

                headers: this.getHeaders()

            }

        );

    }

}

export default new DashboardService();