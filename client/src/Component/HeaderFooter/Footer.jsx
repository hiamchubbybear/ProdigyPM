import React from "react";
import "../../Assets/css/Footer.css";

const Footer = () => {
  return (
    <div className="footer">
      <p>&copy; 2024 ProdigyPM. Education Purpose.</p>
      <div className="footer-links">
        <a href="/privacy">Privacy Policy</a> |
        <a href="/term">Terms of Service</a> |<a href="/contact">Contact Us</a>
      </div>
    </div>
  );
};

export default Footer;
