import { jwtDecode } from "jwt-decode";
import axios from "./axios";
export const loginRequest = (user, pwd) => {
  return axios.post(
    "/auth/login",
    JSON.stringify({
      username: user,
      password: pwd,
    }),
    {
      headers: {
        "Content-Type": "application/json",
      },
      withCredentials: true,
    }
  );
};
export const getImageRequest = (token, user) => {
  return axios.get("api/customer/image", {
    headers: {
      Authorization: `Bearer ${token}`,
    },
    params: {
      username: user,
    },
  });
};

export const forgotPasswordRequest = (email) => {
  return axios.post(
    "api/customer/resetpwd",
    {},
    {
      headers: {
        "Content-Type": "application/json",
      },
      params: {
        email: email,
      },
    }
  );
};

export const confirmForgotPassword = (token, email) => {
  return axios.post(`api/customer/cfpwd/${token}/${email}`, {
    headers: {
      "Content-Type": "application/json",
    },
  });
};
export const signUpAccount = (user, pwd, email) => {
  return axios.post(
    "/api/customer/register",
    JSON.stringify({
      username: user,
      password: pwd,
      email: email,
    }),
    {
      headers: {
        "Content-Type": "application/json",
      },
      withCredentials: true,
    }
  );
};
export const activateAccountRequest = (token, email) => {
  return axios.post(
    "/api/customer/activate",
    {
      token: token,
      email: email,
    },
    {
      headers: {
        "Content-Type": "application/json",
      },
      withCredentials: true,
    }
  );
};
export const getCustomerInfoRequests = (tokenFromStorage) => {
  return axios.get("/api/customer/getMyInfo", {
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${tokenFromStorage}`,
    },
  });
};
export const addJournalEntryRequest = (data, token) => {
  data.createBy = jwtDecode(localStorage.getItem("token")).sub;
  return axios.post("api/journal-entry-detail", data, {
    "Content-Type": "application/json",
    Authorization: `Bearer ${token}`,
  });
};
export const updateJournalEntryRequest = (data) => {
  const URL = `api/journal-entries`;
  console.log("Data post", data);
  const token = localStorage.getItem("token");
  return axios.put(URL, data, {
    "Content-Type": "application/json",
    Authorization: `Bearer ${token}`,
  });
};
