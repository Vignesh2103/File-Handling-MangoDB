import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./Components/Home";
import Form from "./Components/ClaimForm";
import Sucess from "./Components/Sucess";
import Error from "./Components/Error";
import { Link } from "react-router-dom";
import DownloadFiles from "./Components/DownloadFiles";

function App() {
  return (
    <Router>
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <Link className="navbar-brand">Health Insurance Claim</Link>
        </nav>
        <div>
          <div>
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/form" element={<Form />} />
              <Route path="/success" element={<Sucess />} />
              <Route path="/error" element={<Error />} />
              <Route path="/download" element={<DownloadFiles />} />
            </Routes>
          </div>
        </div>
      </div>
    </Router>
  );
}

export default App;
