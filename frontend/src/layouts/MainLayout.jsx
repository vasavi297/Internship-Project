import { Outlet } from "react-router-dom";

import Navbar from "../components/common/Navbar";

import "../styles/layout/MainLayout.css";

function MainLayout() {

    return (

        <div className="layout">

            <Navbar />

            <main className="page-content">

                <Outlet />

            </main>

        </div>

    );

}

export default MainLayout;