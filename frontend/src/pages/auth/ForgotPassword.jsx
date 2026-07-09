import { useState } from "react";
import { Link } from "react-router-dom";

import AuthService from "../../services/AuthService";

import "../../styles/pages/Auth.css";

function ForgotPassword() {

    const [email, setEmail] = useState("");

    const [loading, setLoading] = useState(false);

    const [message, setMessage] = useState("");

    const [error, setError] = useState("");

    const sendCode = async (event) => {

        event.preventDefault();

        setLoading(true);
        setError("");
        setMessage("");

        try {

            const response = await AuthService.forgotPassword({

                email

            });

            setMessage(response.data.message);

        } catch (exception) {

            console.error(exception);

            setError(

                exception.response?.data?.message ||

                "Unable to send verification code."

            );

        } finally {

            setLoading(false);

        }

    };

    return (

        <div className="auth-container">

            <form
                className="auth-card"
                onSubmit={sendCode}
            >

                <h1>Forgot Password</h1>

                <p>

                    Enter your registered email address.

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

                <button
                    type="submit"
                    disabled={loading}
                >

                    {

                        loading

                            ?

                            "Sending..."

                            :

                            "Send Verification Code"

                    }

                </button>

                <p
                    style={{
                        marginTop: "20px",
                        textAlign: "center"
                    }}
                >

                    Already have the code?

                    {" "}

                    <Link
                        to="/reset-password"
                    >

                        Reset Password

                    </Link>

                </p>

                <p
                    style={{
                        textAlign: "center"
                    }}
                >

                    <Link
                        to="/login"
                    >

                        Back to Login

                    </Link>

                </p>

            </form>

        </div>

    );

}

export default ForgotPassword;