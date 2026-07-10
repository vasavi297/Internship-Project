import { Navigate } from "react-router-dom";
import AuthService from "../services/AuthService";

function PublicRoute({ children }) {

    if (AuthService.isLoggedIn()) {

        return <Navigate to="/dashboard" replace />;

    }

    return children;

}

export default PublicRoute;