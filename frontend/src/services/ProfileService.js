import axios from "axios";

const API_URL = "http://13.205.249.127:8080/profile";

class ProfileService {

    getHeaders() {

        return {

            Authorization:
                `Bearer ${localStorage.getItem("accessToken")}`

        };

    }

    getProfile() {

        return axios.get(

            API_URL,

            {

                headers: this.getHeaders()

            }

        );

    }

}

export default new ProfileService();
