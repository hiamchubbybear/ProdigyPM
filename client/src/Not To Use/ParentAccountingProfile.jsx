import Cookies from "js-cookie";
import { useEffect, useState } from "react";
import { getCustomerInfoRequests } from "../Api/apiRequest";
const ParentAccount = () => {
  const [image, setImage] = useState("");
  var customer = Cookies.get("customer")
    ? JSON.parse(Cookies.get("customer"))
    : getCustomerInfoRequests(localStorage.getItem("token"));

  useEffect(() => {
    var image = localStorage.getItem("image");
    console.log(image);
  });
  return (
    <div className="customer-details">
      <img
        src={`data:image/png;base64,${image}`}
        alt="avatar"
        className="images"
      ></img>
      <ul className="customer-item">
        <li className="username">
          <p>Username:</p> {customer?.username}
        </li>
        <li className="email">
          <p>Email:</p> {customer?.email}
        </li>
        <li className="name">
          <p>Name:</p> {customer?.name}
        </li>
        <li className="address">
          <p>Address:</p> {customer?.address}
        </li>
      </ul>
    </div>
  );
};

export default ParentAccount;
