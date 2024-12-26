import cookie from "js-cookie";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "../../Api/axios";
import "../../Assets/css/Customer.css";
import Loading from "../../Error/Loading/Loading";
import Error404 from "../../Error/Page/Error404";

const Customer = () => {
  const [customer, setCustomer] = useState(null);
  const [token, setToken] = useState("");
  const [err, setErr] = useState("");
  const [image, setImage] = useState("");
  const [loading, setLoading] = useState(false);
  const [isLogin, setIsLogin] = useState(false);
  const navigate = useNavigate();
  useEffect(() => {
    const tokenFromStorage = localStorage.getItem("token");
    setToken(tokenFromStorage);
    if (!tokenFromStorage) {
      setIsLogin(false);
      return;
    }

    const fetchCustomer = async () => {
      setLoading(true);
      setIsLogin(true);
      try {
        const response = await axios.get("/api/customer/getMyInfo", {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${tokenFromStorage}`,
          },
        });

        cookie.set("customer", JSON.stringify(response?.data?.data));
        setImage(localStorage.getItem("image"));
        setCustomer(response?.data?.data);
      } catch (error) {
        setErr(error.response?.data?.message || error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchCustomer();
  }, [token]);

  useEffect(() => {
    if (!loading && isLogin && !customer) {
      navigate("/nothing");
    }
  }, [loading, isLogin, customer, navigate]);

  if (loading || !isLogin) {
    return <Error404 />;
  }

  if (err) {
    return <div className="error-message">{err}</div>;
  }

  return customer ? (
    <div className="customer-details">
      <img src={`data:image/png;base64,${image}`} className="images"></img>
      <ul className="customer-item">
        <li className="username">
          <strong>Username:</strong> {customer?.username}
        </li>
        <li className="email">
          <strong>Email:</strong> {customer?.email}
        </li>
        <li className="name">
          <strong>Name:</strong> {customer?.name}
        </li>
        <li className="address">
          <strong>Address:</strong> {customer?.address}
        </li>
        <li className="gender">
          <strong>Gender:</strong> {customer?.gender ? "Male" : "Female"}{" "}
          {console.log(customer.gender)}
        </li>
        <li className="status">
          <strong>Status:</strong> {customer?.status ? "Activate" : "Inactive"}
        </li>
      </ul>
      <button className="btn-update">
        <a href="/user/update">Update</a>
      </button>
    </div>
  ) : (
    <Loading />
  );
};

export default Customer;
