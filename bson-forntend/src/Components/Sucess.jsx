import React from 'react'
import "bootstrap/dist/css/bootstrap.min.css";
import "./Sucess.css";
function Sucess() {
  return (
    <div className='container'>
        <div class="card">
        <div class="card-body">
          <h5 class="card-title">Thank you for submitting your claim</h5>
          <p class="card-text">
            Your claim has been submitted successfully
          </p>
          <p class="card-text">Click on the button below to get view the Document page.</p>
          <a href="/download" class="btn btn-primary">
            Click here
          </a>
        </div>
      </div>
    </div>
  )
}

export default Sucess