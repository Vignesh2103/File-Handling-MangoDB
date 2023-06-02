import React from "react";
import "./Home.css";
import "bootstrap/dist/css/bootstrap.min.css";
// import { Card, Container, Button } from "react-bootstrap";

function Home() {
  return (
    <div>
      <div class="card">
        <div class="card-body">
          <h5 class="card-title">Welcome to Health Insurance Claim</h5>
          <p class="card-text">
            Submit your health insurance claims hassle-free and get reimbursed
            quickly.
          </p>
          <p class="card-text">Click on the button below to get started.</p>
          <a href="/form" class="btn btn-primary">
            Go to Claim Form
          </a>
        </div>
      </div>
    </div>
  );
}

export default Home;
