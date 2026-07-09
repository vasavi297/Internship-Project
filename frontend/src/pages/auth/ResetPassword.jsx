import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { FaEye, FaEyeSlash } from "react-icons/fa";

import AuthService from "../../services/AuthService";

import "../../styles/pages/Auth.css";

function ResetPassword() {

    const navigate = useNavigate();

    const [email, setEmail] = useState("");

    const [code, setCode] = useState("");

    const [newPassword, setNewPassword] = useState("");

    const [confirmPassword, setConfirmPassword] = useState("");

    const [showNewPassword, setShowNewPassword] = useState(false);

    const [showConfirmPassword, setShowConfirmPassword] = useState(false);

    const [loading, setLoading] = useState(false);

    const [error, setError] = useState("");

    const [message, setMessage] = useState("");

    const resetPassword = async (event) => {

        event.preventDefault();

        setError("");
        setMessage("");

        if (newPassword !== confirmPassword) {

            setError("Passwords do not match.");

            return;

        }

        setLoading(true);

        try {

            const response = await AuthService.resetPassword({

                email,
                code,
                newPassword

            });

            setMessage(response.data.message);

            setTimeout(() => {

                navigate("/login");

            }, 2000);

        } catch (exception) {

            console.error(exception);

            setError(

                exception.response?.data?.message ||

                "Unable to reset password."

            );

        } finally {

            setLoading(false);

        }

    };

    return (

        <div className="auth-container">

            <form
                className="auth-card"
                onSubmit={resetPassword}
            >

                <h1>Reset Password</h1>

                <p>

                    Enter the verification code and your new password.

                </p>

                {

                    message &&

                    <div className="auth-success">

                        {message}

                    </div>

                }

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

                <input
                    type="text"
                    placeholder="Verification Code"
                    value={code}
                    onChange={(event) =>
                        setCode(event.target.value)
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
                            showNewPassword
                                ? "text"
                                : "password"
                        }
                        placeholder="New Password"
                        value={newPassword}
                        onChange={(event) =>
                            setNewPassword(event.target.value)
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
                            setShowNewPassword(!showNewPassword)
                        }
                        style={{
                            position: "absolute",
                            right: "12px",
                            top: "50%",
                            transform: "translateY(-50%)",
                            border: "none",
                            background: "transparent",
                            cursor: "pointer"
                        }}
                    >

                        {

                            showNewPassword

                                ?

                                <FaEyeSlash/>

                                :

                                <FaEye/>

                        }

                    </button>

                </div>

                <div
                    style={{
                        position: "relative",
                        marginBottom: "20px"
                    }}
                >

                    <input
                        type={
                            showConfirmPassword
                                ? "text"
                                : "password"
                        }
                        placeholder="Confirm Password"
                        value={confirmPassword}
                        onChange={(event) =>
                            setConfirmPassword(event.target.value)
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
                            setShowConfirmPassword(!showConfirmPassword)
                        }
                        style={{
                            position: "absolute",
                            right: "12px",
                            top: "50%",
                            transform: "translateY(-50%)",
                            border: "none",
                            background: "transparent",
                            cursor: "pointer"
                        }}
                    >

                        {

                            showConfirmPassword

                                ?

                                <FaEyeSlash/>

                                :

                                <FaEye/>

                        }

                    </button>

                </div>

                <button
                    type="submit"
                    disabled={loading}
                >

                    {

                        loading

                            ?

                            "Resetting..."

                            :

                            "Reset Password"

                    }

                </button>

                <p
                    style={{
                        marginTop: "20px",
                        textAlign: "center"
                    }}
                >

                    <Link to="/login">

                        Back to Login

                    </Link>

                </p>

            </form>

        </div>

    );

}

export default ResetPassword;