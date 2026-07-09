import { BrowserRouter, Routes, Route } from "react-router-dom";

import MainLayout from "../layouts/MainLayout";

import Dashboard from "../pages/Dashboard";
import UploadResume from "../pages/UploadResume";
import ResumeHistory from "../pages/ResumeHistory";
import Profile from "../pages/Profile";
import ResumeAnalysis from "../pages/ResumeAnalysis";

import Register from "../pages/auth/Register";
import ConfirmAccount from "../pages/auth/ConfirmAccount";
import Login from "../pages/auth/Login";
import ForgotPassword from "../pages/auth/ForgotPassword";
import ResetPassword from "../pages/auth/ResetPassword";

function AppRoutes() {

    return (

        <BrowserRouter>

            <Routes>

                {/* Authentication Pages */}

                <Route
                    path="/register"
                    element={<Register />}
                />
                <Route
                    path="/confirm"
                    element={<ConfirmAccount />}
                />
                <Route
                    path="/login"
                    element={<Login />}
                />
                <Route
    path="/forgot-password"
    element={<ForgotPassword />}
/>

<Route
    path="/reset-password"
    element={<ResetPassword />}
/>

                {/* Dashboard Layout */}

                <Route element={<MainLayout />}>

                    <Route
                        path="/"
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

            </Routes>

        </BrowserRouter>

    );

}

export default AppRoutes;