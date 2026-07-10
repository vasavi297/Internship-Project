import AppRoutes from "./routes/AppRoutes";

import { AuthProvider } from "./context/AuthContext";
import { AnalysisProvider } from "./context/AnalysisContext";

import { Toaster } from "react-hot-toast";

function App() {

    return (

        <AuthProvider>

            <AnalysisProvider>

                <AppRoutes />

                <Toaster
                    position="top-right"
                    reverseOrder={false}
                />

            </AnalysisProvider>

        </AuthProvider>

    );

}

export default App;