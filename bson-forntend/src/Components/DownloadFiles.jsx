import React, { useState } from "react";
import axios from "axios";
import { ToastContainer, toast } from "react-toastify";
import "./ClaimForm.css";
import "bootstrap/dist/css/bootstrap.min.css";
import "react-toastify/dist/ReactToastify.css";
import { useNavigate } from "react-router-dom";

const DownloadFiles = () => {
  const [userId, setUserId] = useState("");
  const [downloadedFiles, setDownloadedFiles] = useState([]);
  let navigate = useNavigate();

  const handleDownload = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/bsonclaim/download/${userId}`,
        {
          responseType: "arraybuffer",
        }
      );

      const blob = new Blob([response.data], { type: "application/zip" });
      const url = window.URL.createObjectURL(blob);
      const link = document.createElement("a");
      link.href = url;
      link.setAttribute("download", "files.zip");
      document.body.appendChild(link);
      link.click();
      link.remove();
      toast.success("Files downloaded successfully!", {
        position: "top-right",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "colored",
      });
      setDownloadedFiles([...downloadedFiles, "files.zip"]);
      setTimeout(() => {
        navigate("/");
      }, 5000);
    } catch (error) {
      console.error("Error downloading files", error);
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
    }
  };

  return (
    <div>
      <div className="container">
        <div class="card">
        <h1 className="heading">Download Files</h1>
        <div>
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
          <button onClick={handleDownload} class="btn btn-primary">Download</button>
        </div>
        {downloadedFiles.length > 0 && (
          <div>
            <h2 className="heading">Downloaded Files:</h2>
            <ul>
              {downloadedFiles.map((file, index) => (
                <li key={index}>{file}</li>
              ))}
            </ul>
          </div>
        )}
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

export default DownloadFiles;
