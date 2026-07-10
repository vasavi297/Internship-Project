import api from "./api";

class DashboardService {

    getDashboard() {

        return api.get("/dashboard");

    }

}

const dashboardService = new DashboardService();

export default dashboardService;