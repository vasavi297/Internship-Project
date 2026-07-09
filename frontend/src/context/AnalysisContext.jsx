import { createContext, useContext, useState } from "react";

const AnalysisContext = createContext();

export function AnalysisProvider({ children }) {
    const [analysis, setAnalysis] = useState(null);
    const [loading, setLoading] = useState(false);

    const clearAnalysis = () => {
        setAnalysis(null);
    };

    return (
        <AnalysisContext.Provider
            value={{
                analysis,
                setAnalysis,
                loading,
                setLoading,
                clearAnalysis
            }}
        >
            {children}
        </AnalysisContext.Provider>
    );
}

export function useAnalysis() {
    return useContext(AnalysisContext);
}