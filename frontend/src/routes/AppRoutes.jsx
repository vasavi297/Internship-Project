import {
    BrowserRouter,
    Routes,
    Route,
    Navigate
} from "react-router-dom";

import MainLayout from "../layouts/MainLayout";

import Dashboard from "../pages/Dashboard";
import UploadResume from "../pages/UploadResume";
import ResumeHistory from "../pages/ResumeHistory";
import ResumeAnalysis from "../pages/ResumeAnalysis";
import Profile from "../pages/Profile";

import Login from "../pages/auth/Login";
import Register from "../pages/auth/Register";
import ConfirmAccount from "../pages/auth/ConfirmAccount";
import ForgotPassword from "../pages/auth/ForgotPassword";
import ResetPassword from "../pages/auth/ResetPassword";

import ProtectedRoute from "./ProtectedRoute";
import PublicRoute from "./PublicRoute";

function AppRoutes() {

    return (

        <BrowserRouter>

            <Routes>

                {/* Root */}

                <Route
                    path="/"
                    element={<Navigate to="/login" replace />}
                />

                {/* Authentication Pages */}

                <Route
                    path="/login"
                    element={
                        <PublicRoute>
                            <Login />
                        </PublicRoute>
                    }
                />

                <Route
                    path="/register"
                    element={
                        <PublicRoute>
                            <Register />
                        </PublicRoute>
                    }
                />

                <Route
                    path="/confirm"
                    element={
                        <PublicRoute>
                            <ConfirmAccount />
                        </PublicRoute>
                    }
                />

                <Route
                    path="/forgot-password"
                    element={
                        <PublicRoute>
                            <ForgotPassword />
                        </PublicRoute>
                    }
                />

                <Route
                    path="/reset-password"
                    element={
                        <PublicRoute>
                            <ResetPassword />
                        </PublicRoute>
                    }
                />

                {/* Protected Pages */}

                <Route
                    element={
                        <ProtectedRoute>
                            <MainLayout />
                        </ProtectedRoute>
                    }
                >

                    <Route
                        path="/dashboard"
                        element={<Dashboard />}
                    />

                    <Route
                        path="/upload"
                        element={<UploadResume />}
                    />

                    <Route
                        path="/analysis"
                        element={<ResumeAnalysis />}
                    />

                    <Route
                        path="/analysis/:resumeId"
                        element={<ResumeAnalysis />}
                    />

                    <Route
                        path="/history"
                        element={<ResumeHistory />}
                    />

                    <Route
                        path="/profile"
                        element={<Profile />}
                    />

                </Route>

                {/* Invalid URL */}

                <Route
                    path="*"
                    element={<Navigate to="/login" replace />}
                />

            </Routes>

        </BrowserRouter>

    );

}

export default AppRoutes;