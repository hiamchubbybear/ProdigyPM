import React from "react";
import "../../Assets/css/HomePage.css";

const HomePage = () => {
  return (
    <div className="homepage">
      <section className="hero">
        <div className="hero-content">
          <h2>Your Business, Optimized</h2>
          <p>
            Transform the way you manage your business with our cutting-edge ERP
            solutions.
          </p>
          <button className="cta-btn">
            <a href="/login">Get Started</a>
          </button>
        </div>
      </section>

      <section className="features" id="features">
        <h2>Key Features</h2>
        <div className="feature-cards">
          <div className="card">
            <h3>Finance Management</h3>
            <p>Manage your finances effortlessly with our advanced tools.</p>
          </div>
          <div className="card">
            <h3>Inventory Tracking</h3>
            <p>
              Keep track of your inventory in real-time, no matter where you
              are.
            </p>
          </div>
          <div className="card">
            <h3>Human Resources</h3>
            <p>
              Streamline HR processes with comprehensive employee management
              features.
            </p>
          </div>
        </div>
      </section>
    </div>
  );
};

export default HomePage;
