import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { FaEye, FaEyeSlash } from "react-icons/fa";

import { useAuth } from "../../context/AuthContext";
import AuthService from "../../services/AuthService";

import "../../styles/pages/Auth.css";

function Login() {

    const navigate = useNavigate();

    const { login } = useAuth();

    const [email, setEmail] = useState("");

    const [password, setPassword] = useState("");

    const [showPassword, setShowPassword] = useState(false);

    const [loading, setLoading] = useState(false);

    const [error, setError] = useState("");

    const loginUser = async (event) => {

        event.preventDefault();

        setLoading(true);

        setError("");

        try {

            const response = await AuthService.login({

                email,
                password

            });

            login(response.data.data);

            navigate("/");

        } catch (exception) {

            console.error(exception);

            setError(

                exception.response?.data?.message ||

                "Invalid Email or Password."

            );

        } finally {

            setLoading(false);

        }

    };

    return (

        <div className="auth-container">

            <form
                className="auth-card"
                onSubmit={loginUser}
            >

                <h1>Welcome Back</h1>

                <p>

                    Login to continue to CareerPath AI

                </p>

                {

                    error &&

                    <div className="auth-error">

                        {error}

                    </div>

                }

                <input
                    type="email"
                    placeholder="Email Address"
                    value={email}
                    onChange={(event) =>
                        setEmail(event.target.value)
                    }
                    required
                />

                <div
                    style={{
                        position: "relative",
                        marginBottom: "15px"
                    }}
                >

                    <input
                        type={
                            showPassword
                                ? "text"
                                : "password"
                        }
                        placeholder="Password"
                        value={password}
                        onChange={(event) =>
                            setPassword(event.target.value)
                        }
                        required
                        style={{
                            width: "100%",
                            paddingRight: "45px"
                        }}
                    />

                    <button
                        type="button"
                        onClick={() =>
                            setShowPassword(
                                !showPassword
                            )
                        }
                        style={{
                            position: "absolute",
                            right: "12px",
                            top: "50%",
                            transform: "translateY(-50%)",
                            background: "transparent",
                            border: "none",
                            cursor: "pointer",
                            color: "#6B7280"
                        }}
                    >

                        {

                            showPassword

                                ?

                                <FaEyeSlash />

                                :

                                <FaEye />

                        }

                    </button>

                </div>

                <div
                    style={{
                        textAlign: "right",
                        marginBottom: "20px"
                    }}
                >

                    <Link
                        to="/forgot-password"
                        style={{
                            textDecoration: "none",
                            color: "#2563EB",
                            fontWeight: "500"
                        }}
                    >

                        Forgot Password?

                    </Link>

                </div>

                <button
                    type="submit"
                    disabled={loading}
                >

                    {

                        loading

                            ?

                            "Signing In..."

                            :

                            "Login"

                    }

                </button>

                <p
                    style={{
                        marginTop: "20px",
                        textAlign: "center"
                    }}
                >

                    Don't have an account?{" "}

                    <Link
                        to="/register"
                        style={{
                            color: "#2563EB",
                            textDecoration: "none",
                            fontWeight: "600"
                        }}
                    >

                        Register

                    </Link>

                </p>

            </form>

        </div>

    );

}

export default Login;