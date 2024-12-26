import { jwtDecode } from "jwt-decode";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const navigate = new useNavigate("");
export const checkToken = (token) => {
  const decodeToken = jwtDecode(token);
  const currentTime = Date.now;
  if (token !== localStorage.getItem("token")) return false;
  else
    try {
      if (!token || decodeToken.exp < Date.now * 1000) return false;
      //   else if (decodeToken.exp - currentTime < 15 * 60);
    } catch (error) {}
};
export function isTokenExpired(token) {
  if (!token) {
    return true;
  } else {
    try {
      const decodedToken = jwtDecode(token);
      const currentTime = Date.now() / 1000;
      return decodedToken.exp < currentTime;
    } catch (error) {
      return false;
    }
  }
}

export const notify = () => toast("Wow so easy!");
