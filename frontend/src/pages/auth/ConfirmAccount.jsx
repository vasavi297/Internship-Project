import { useLocation, useNavigate } from "react-router-dom";
import { useState } from "react";

import AuthService from "../../services/AuthService";

import "../../styles/pages/Auth.css";

function ConfirmAccount() {

    const navigate = useNavigate();

    const location = useLocation();

    const [email, setEmail] = useState(
        location.state?.email || ""
    );

    const [code, setCode] = useState("");

    const [loading, setLoading] = useState(false);

    const [error, setError] = useState("");

    const confirmAccount = async (event) => {

        event.preventDefault();

        setLoading(true);

        setError("");

        try {

            await AuthService.confirm({

                email,
                code

            });

            alert("Account verified successfully.");

            navigate("/login");

        } catch (exception) {

            console.error(exception);

            setError(

                exception.response?.data?.message ||

                "Verification failed."

            );

        } finally {

            setLoading(false);

        }

    };

    return (

        <div className="auth-container">

            <form
                className="auth-card"
                onSubmit={confirmAccount}
            >

                <h1>Verify Account</h1>

                <p>

                    Enter the verification code
                    sent to your email.

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

                <input
                    type="text"
                    placeholder="Verification Code"
                    value={code}
                    onChange={(event) =>
                        setCode(event.target.value)
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

                        "Verifying..."

                        :

                        "Verify Account"

                    }

                </button>

            </form>

        </div>

    );

}

export default ConfirmAccount;