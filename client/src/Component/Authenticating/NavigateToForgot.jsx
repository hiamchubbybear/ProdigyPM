import React, { useState } from "react";
import { Navigate } from "react-router-dom";
import {
  confirmForgotPassword,
  forgotPasswordRequest,
} from "../../Api/apiRequest";
import emailPic from "../../Assets/image/gmail.png";
const NavigateToForgot = () => {
  const [email, setEmail] = useState("");
  const [passcode, setPassCode] = useState("");
  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(false);
  const [step, setStep] = useState("email");
  const forgotPasswordHandle = async (event) => {
    event.preventDefault();
    setLoading(true);
    setMessage("");
    try {
      const response = await forgotPasswordRequest(email);
      setMessage("Email sent successfully.");
      setStep("passcode");
    } catch (error) {
      if (error.response?.status === 401) setMessage("Failed to send email.");
      else setMessage("An error occurred. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  const comfirmPasswordHandle = async (event) => {
    event.preventDefault();
    setLoading(true);
    setMessage("");
    try {
      const response = await confirmForgotPassword(passcode, email);
      setMessage("Passcode confirmed successfully.");
      setLoading(false);
      console.log(response?.data);
      if (response?.data) {
        Navigate("/password");
      }
    } catch (error) {
      setMessage("Invalid passcode. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <section className="container">
      {step === "email" ? (
        <div className="text">
          <h1>Forgot Password</h1>
          <form className="inputs" onSubmit={forgotPasswordHandle}>
            <div className="email">
              <img src={emailPic} alt="username" />
              <input
                type="email"
                value={email}
                id="email"
                placeholder="Email"
                onChange={(event) => setEmail(event.target.value)}
                required
              />
            </div>
            <div className="signupbutton">
              <button type="submit" disabled={loading}>
                {loading ? "Sending..." : "Send secret"}
              </button>
              {message && <p className="message">{message}</p>}
            </div>
          </form>
        </div>
      ) : (
        <div className="text">
          <p>Input your passcode</p>
          <form className="inputs" onSubmit={comfirmPasswordHandle}>
            <div className="email">
              <img src={emailPic} alt="username" />
              <input
                type="text"
                value={passcode}
                id="passcode"
                placeholder="PASSCODE"
                onChange={(event) => setPassCode(event.target.value)}
                required
              />
            </div>
            <div className="signupbutton">
              <button type="submit" disabled={loading}>
                {loading ? "Authenticating..." : "Confirm"}
              </button>
            </div>
            {message && <p className="message">{message}</p>}
          </form>
        </div>
      )}
    </section>
  );
};

export default NavigateToForgot;
