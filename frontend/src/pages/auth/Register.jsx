import { useState } from "react";
import { useNavigate } from "react-router-dom";

import AuthService from "../../services/AuthService";

import "../../styles/pages/Auth.css";

function Register() {

    const navigate = useNavigate();

    const [email, setEmail] = useState("");

    const [password, setPassword] = useState("");

    const [loading, setLoading] = useState(false);

    const [error, setError] = useState("");

    const registerUser = async (event) => {

        event.preventDefault();

        setLoading(true);

        setError("");

        try {

            await AuthService.register({

                email,
                password

            });

            navigate("/confirm", {

                state: {

                    email

                }

            });

        } catch (exception) {

            console.error(exception);

            setError(

                exception.response?.data?.message ||

                "Registration Failed."

            );

        } finally {

            setLoading(false);

        }

    };

    return (

        <div className="auth-container">

            <form
                className="auth-card"
                onSubmit={registerUser}
            >

                <h1>Create Account</h1>

                <p>

                    Register to start using CareerPath AI

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
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(event) =>
                        setPassword(event.target.value)
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

                        "Creating Account..."

                        :

                        "Register"

                    }

                </button>

            </form>

        </div>

    );

}

export default Register;