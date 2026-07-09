import { useEffect, useState } from "react";
import {
    FaBell,
    FaSignOutAlt
} from "react-icons/fa";
import {
    NavLink,
    useNavigate
} from "react-router-dom";

import { useAuth } from "../../context/AuthContext";
import AuthService from "../../services/AuthService";

import "../../styles/layout/Navbar.css";

function Navbar() {

    const navigate = useNavigate();

    const { logout } = useAuth();

    const [user, setUser] = useState({

        name: "User",
        email: "",
        initials: "U"

    });

    useEffect(() => {

        const data = AuthService.getUser();

        if (data) {

            const email = data.email || "";

            const name =
                data.name ||
                email ||
                "User";

            const initials =
                name.charAt(0).toUpperCase();

            setUser({

                name,
                email,
                initials

            });

        }

    }, []);

    const logoutUser = () => {

        logout();

        navigate("/login");

    };

    return (

        <header className="navbar">

            <div className="logo">

                CareerPathAI

            </div>

            <nav className="nav-links">

                <NavLink
                    to="/"
                    end
                    className={({ isActive }) =>
                        isActive ? "active-link" : ""
                    }
                >

                    Dashboard

                </NavLink>

                <NavLink
                    to="/upload"
                    className={({ isActive }) =>
                        isActive ? "active-link" : ""
                    }
                >

                    Upload Resume

                </NavLink>

                <NavLink
                    to="/history"
                    className={({ isActive }) =>
                        isActive ? "active-link" : ""
                    }
                >

                    Resume History

                </NavLink>

            </nav>

            <div className="navbar-right">

                

                <div className="profile-card">

                    <div className="profile-avatar">

                        {user.initials}

                    </div>

                    <div>

                        <h4>

                            {user.name}

                        </h4>

                        <p>

                            {user.email}

                        </p>

                    </div>

                </div>

                <button
                    className="logout-btn"
                    onClick={logoutUser}
                >

                    <FaSignOutAlt/>

                    Logout

                </button>

            </div>

        </header>

    );

}

export default Navbar;