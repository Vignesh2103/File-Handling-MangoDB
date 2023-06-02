import React, { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "./ClaimForm.css";
import axios from "axios";
import { ToastContainer, toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import "react-toastify/dist/ReactToastify.css";

const ClaimForm = () => {
  const [files, setFiles] = useState([]);
  const [userId, setUserId] = useState("");
  const [username, setUsername] = useState("");
  const navigate = useNavigate();

  const handleFileChange = (event, index) => {
    const updatedFiles = [...files];
    updatedFiles[index] = event.target.files[0];
    setFiles(updatedFiles);
  };
  useEffect(() => {
    return clearTimeout();
  }, []);
  const handleAddFile = () => {
    setFiles([...files, null]);
  };

  const handleRemoveFile = (index) => {
    const updatedFiles = [...files];
    updatedFiles.splice(index, 1);
    setFiles(updatedFiles);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    const formData = new FormData();
    formData.append("userId", userId);
    formData.append("username", username);

    for (let i = 0; i < files.length; i++) {
      formData.append("files", files[i]);
    }

    try {
      await axios.post("http://localhost:8080/api/bsonclaim/upload", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });
      // Handle success case
      console.log("Files uploaded successfully!");
      toast.success("Files uploaded successfully!", {
        position: "top-right",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "colored",
      });
      // Navigate to a different route after successful form submission
      setTimeout(() => {
        navigate("/success");
      }, 5000); // Replace "/success" with your desired route
    } catch (error) {
      // Handle error case
      console.error("Error uploading files. Please try again.", error);
      toast.error("Error uploading files. Please try again", {
        position: "top-right",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "colored",
      });
      navigate("/error"); // Replace "/error" with your desired route in case of an error
    }
  };

  return (
    <div>
      <div className="container">
        <div className="card">
          <div className="card-body">
            <h1 className="heading">CLAIM FORM</h1>
            <div>
              <form onSubmit={handleSubmit}>
                <div className="form-group">
                  <input
                    type="text"
                    className="form-control"
                    id="userId"
                    placeholder="Enter userId"
                    value={userId}
                    onChange={(event) => setUserId(event.target.value)}
                  />
                </div>
                <div className="form-group">
                  <input
                    type="text"
                    className="form-control"
                    id="username"
                    placeholder="Enter username"
                    value={username}
                    onChange={(event) => setUsername(event.target.value)}
                  />
                </div>
                {files.map((file, index) => (
                  <div key={index}>
                    <div className="form-group">
                      <input
                        className="form-control"
                        type="file"
                        id={`file${index}`}
                        onChange={(event) => handleFileChange(event, index)}
                      />
                      {file !== null && (
                        <button
                          type="button"
                          className="btn btn-danger"
                          onClick={() => handleRemoveFile(index)}
                        >
                          Remove File
                        </button>
                      )}
                    </div>
                  </div>
                ))}

                <button
                  type="button"
                  className="btn btn-primary"
                  onClick={handleAddFile}
                >
                  Add File
                </button>

                <div className="button">
                  <button type="submit" className="btn btn-outline-dark">
                    Submit
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
      <ToastContainer
        position="top-right"
        autoClose={5000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        theme="colored"
      />
    </div>
  );
};

export default ClaimForm;
