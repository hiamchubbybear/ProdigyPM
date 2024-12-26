import Cookies from "js-cookie";
import React, { useEffect, useRef, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { signUpAccount } from "../../Api/apiRequest";
import "../../Assets/css/SignUp.css";
import gmailIcon from "../../Assets/image/gmail.png";
import cardId from "../../Assets/image/id-card.png";
import passwordIcon from "../../Assets/image/password.png";

const REGISTER_URL = "/api/customer/register";

const SignUp = () => {
  const navigate = useNavigate();
  const userRef = useRef(null);
  const errRef = useRef(null);

  const [user, setUser] = useState("");
  const [pwd, setPwd] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [email, setEmail] = useState("");
  const [errMsg, setErrMsg] = useState("");
  const [success, setSuccess] = useState(false);

  useEffect(() => {
    userRef.current?.focus();
  }, []);

  useEffect(() => {
    setErrMsg("");
  }, [user, pwd, confirmPassword, email]);

  useEffect(() => {
    if (success) {
      setTimeout(() => navigate("/activate"), 1000);
    }
  }, [success, navigate]);

  const handleOnClick = (event) => {
    event.preventDefault();
  };

  const submitHandle = async (event) => {
    event.preventDefault();
    if (pwd !== confirmPassword) {
      setErrMsg("Passwords do not match");
      return;
    }

    try {
      const response = await signUpAccount(user, pwd, email);
      Cookies.set("email", email);
      if (response.data?.data === null) {
        setErrMsg("Username or email already exists. Please try another.");
        setUser("");
        setPwd("");
        setConfirmPassword("");
        setEmail("");
        setSuccess(false);
        return;
      }
      setUser("");
      setPwd("");
      setConfirmPassword("");
      setEmail("");
      setSuccess(true);
    } catch (err) {
      if (!err?.response) {
        setErrMsg("No response from server");
        navigate("/error404");
      } else if (err.response?.status === 409) {
        setErrMsg("Username or email already exists. Please try another.");
      } else {
        setErrMsg("Sign up failed");
      }
      errRef.current?.focus();
    }
  };

  return (
    <section className="signup-container">
      <div className="text">Sign up</div>
      <form className="inputs" onSubmit={submitHandle}>
        <div className="username">
          <img src={cardId} alt="username" />
          <input
            type="text"
            placeholder="Username"
            ref={userRef}
            onChange={(event) => setUser(event.target.value)}
            value={user}
            autoComplete="off"
            required
          />
        </div>
        <div className="password">
          <img src={passwordIcon} alt="password" />
          <input
            type="password"
            placeholder="Password"
            onChange={(event) => setPwd(event.target.value)}
            value={pwd}
            required
          />
        </div>
        <div className="confirmpassword">
          <img src={passwordIcon} alt="confirm password" />
          <input
            type="password"
            placeholder="Retype password"
            onChange={(event) => setConfirmPassword(event.target.value)}
            value={confirmPassword}
            required
          />
        </div>
        <div className="email">
          <img src={gmailIcon} alt="email" />
          <input
            type="email"
            placeholder="Email"
            onChange={(e) => setEmail(e.target.value)}
            value={email}
            required
          />
        </div>
        <div className="signupbutton">
          <button type="submit">Sign Up</button>
        </div>
        <div className="forgetpass">
          <a href="/forgot">Forgot password?</a>
        </div>
        <div className="forgetpass">
          <Link to="/login">Already have an account?</Link>
        </div>
      </form>
      <p ref={errRef} className="errMsg" aria-live="assertive">
        {errMsg}
      </p>
      {success && (
        <p className="successMsg">
          Sign up successful! Redirecting to activate account...
        </p>
      )}
    </section>
  );
};

export default SignUp;
