import { jwtDecode } from "jwt-decode";
import React, { useEffect, useRef, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { getImageRequest, loginRequest } from "../../Api/apiRequest";
import "../../Assets/css/Login.css";
import cardId from "../../Assets/image/id-card.png";
import passwordIcon from "../../Assets/image/password.png";
import ToastService from "../../Toast/ToastService";

function isTokenExpired(token) {
  if (!token) return true;
  try {
const decodedToken = jwtDecode(token);
    const currentTime = Date.now() / 1000;
    return decodedToken.exp < currentTime;
  } catch (error) {
    return true;
  }
}

const Login = () => {
  const navigate = useNavigate();
  const userRef = useRef();
  const errRef = useRef();
  const [user, setUser] = useState("");
  const [pwd, setPwd] = useState("");
  const [errMsg, setErrMsg] = useState("");

  useEffect(() => {
    userRef.current?.focus();
    const jwtToken = localStorage.getItem("token");
    if (jwtToken && !isTokenExpired(jwtToken)) {
      navigate("/home");
    }
  }, [navigate]);

  useEffect(() => setErrMsg(""), [user, pwd]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    let response;
    const jwtToken = localStorage.getItem("token");

    if (isTokenExpired(jwtToken)) {
      try {
        response = await loginRequest(user, pwd);
        const token = response?.data?.data?.token;
        const getImage = await getImageRequest(token, user);

        if (getImage?.data?.data?.base64string) {
          localStorage.setItem("image", getImage.data.data.base64string);
        }

        localStorage.setItem("token", token);
        localStorage.setItem("user", JSON.stringify(user));

        if (response?.data?.code === 101) {
          ToastService.success(
            "Login success but account still doesn't active navigating to active",
            navigate,
            "/activate"
          );
        } else {
          ToastService.success(
            "Login success navigate to home",
            navigate,
            "/home"
          );
        }
      } catch (err) {
        if (!err?.response) setErrMsg("404: Server not found");
        else if (err.response.status === 401)
          setErrMsg("Wrong username or password");
        else if (err.response.status === 400) setErrMsg("Unauthorized");
        else setErrMsg("An unknown error occurred");
        errRef.current?.focus();
      }
    }
  };

  return (
    <section className="login-container">
      <div className="text">Login</div>
      <form className="inputs" onSubmit={handleSubmit}>
        <div className="username">
          <img src={cardId} alt="username" />
          <input
            type="text"
            id="username"
            ref={userRef}
            autoComplete="off"
            onChange={(e) => setUser(e.target.value)}
            value={user}
            placeholder="Username"
            required
          />
        </div>
        <div className="password">
          <img src={passwordIcon} alt="password" />
          <input
            type="password"
            id="password"
            onChange={(e) => setPwd(e.target.value)}
            value={pwd}
            placeholder="Password"
            required
          />
        </div>
        <div className="signupbutton">
          <button type="submit">Log In</button>
        </div>
        <div className="forgetpass">
          <Link to="/forgot">Forgot password?</Link>
        </div>
        <div className="forgetpass">
          <Link to="/signup">Don't have an account? Sign Up</Link>
        </div>
      </form>
      {errMsg && (
        <p ref={errRef} className="errMsg" aria-live="assertive">
          {errMsg}
        </p>
      )}
      {/* Toast container for displaying notifications */}
      <ToastContainer
        position="top-right"
        autoClose={3000}
        hideProgressBar={false}
        closeOnClick
        pauseOnHover
        draggable
        theme="light"
      />
    </section>
  );
};

export default Login;
