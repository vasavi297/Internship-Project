import { useRef, useState } from "react";
import { FaCloudUploadAlt } from "react-icons/fa";
import "../../styles/components/FileUploader.css";

function FileUploader({ onFileSelect }) {
    const inputRef = useRef(null);

    const [dragging, setDragging] = useState(false);
    const [selectedFile, setSelectedFile] = useState(null);

    const supportedTypes = [
        "application/pdf",
        "application/msword",
        "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
    ];

    const handleFile = (file) => {
        if (!file) {
            return;
        }

        if (!supportedTypes.includes(file.type)) {
            alert("Only PDF, DOC and DOCX files are allowed.");
            return;
        }

        setSelectedFile(file);
        onFileSelect(file);
    };

    const handleDrop = (event) => {
        event.preventDefault();
        setDragging(false);

        const file = event.dataTransfer.files[0];
        handleFile(file);
    };

    return (
        <div
            className={dragging ? "upload-box dragging" : "upload-box"}
            onDragOver={(event) => {
                event.preventDefault();
                setDragging(true);
            }}
            onDragLeave={() => setDragging(false)}
            onDrop={handleDrop}
        >
            <FaCloudUploadAlt className="upload-cloud" />

            <h2>Upload Resume</h2>

            <p>
                Drag & Drop your resume here or browse from your computer.
            </p>

            <button
                className="browse-btn"
                onClick={() => inputRef.current.click()}
            >
                Browse File
            </button>

            <input
                ref={inputRef}
                type="file"
                hidden
                accept=".pdf,.doc,.docx"
                onChange={(event) =>
                    handleFile(event.target.files[0])
                }
            />

            {selectedFile && (
                <div className="selected-file">
                    {selectedFile.name}
                </div>
            )}
        </div>
    );
}

export default FileUploader;