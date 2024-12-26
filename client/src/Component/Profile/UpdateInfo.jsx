import cookie from "js-cookie";
import { useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "../../Api/axios";
import "../../Assets/css/UpdateInfo.css";

const UpdateInfo = () => {
  const navigate = useNavigate();
  const emailRef = useRef(null);
  const passwordRef = useRef(null);
  const nameRef = useRef(null);
  const addressRef = useRef(null);
  const customer = JSON.parse(cookie.get("customer"));
  const [formData, setFormData] = useState({
    username: customer?.username,
    email: customer?.email,
    name: customer?.name,
    address: customer?.address,
    gender: customer?.gender,
  });
  const focusOnElement = (elementRef) => {
    if (elementRef && elementRef.current) {
      elementRef.current.focus();
    }
  };
  const [editableFields, setEditableFields] = useState({
    email: false,
    password: false,
    name: false,
    address: false,
    gender: true,
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleEditClick = (field, event) => {
    setEditableFields({
      ...editableFields,
      [field]: !editableFields[field],
    });
  };

  const handleUpdate = async (event) => {
    event.preventDefault();
    try {
      console.log(formData);
      await axios
        .put("/api/customer/update", formData, {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        })
        .then((response) => {
          cookie.set("customer", JSON.stringify(response.data.data));
        })
        .catch((error) => {
          console.error("Failed to update user information:", error);
        });
    } catch (error) {
      console.error("Failed to update user information:", error);
    } finally {
      navigate("/user");
    }
  };
  return (
    <form className="update-content" autoComplete="off" onSubmit={handleUpdate}>
      <div className="field">
        <label>Username</label>
        <input type="text" name="username" value={formData.username} readOnly />
      </div>

      <div className="field">
        <label>Email :</label>
        <input
          type="email"
          name="email"
          ref={emailRef}
          value={formData.email}
          onChange={handleChange}
          readOnly={!editableFields.email}
        />
        <button
          type="button"
          onClick={() => {
            handleEditClick("email");
            focusOnElement(emailRef);
          }}
        >
          Edit
        </button>
      </div>

      <div className="field">
        <label>Password :</label>
        <input
          type="password"
          name="password"
          ref={passwordRef}
          onChange={handleChange}
          placeholder={"********"}
          readOnly={!editableFields.password}
        />
        <button
          type="button"
          onClick={() => {
            handleEditClick("password");
            focusOnElement(passwordRef);
          }}
        >
          Edit
        </button>
      </div>

      <div className="field">
        <label>Name :</label>
        <input
          type="text"
          name="name"
          ref={nameRef}
          value={formData.name}
          onChange={handleChange}
          readOnly={!editableFields.name}
        />
        <button
          type="button"
          onClick={() => {
            handleEditClick("name");
            focusOnElement(nameRef);
          }}
        >
          Edit
        </button>
      </div>

      <div className="field">
        <label>Address :</label>
        <input
          type="text"
          ref={addressRef}
          name="address"
          value={formData.address}
          onChange={handleChange}
          readOnly={!editableFields.address}
        />
        <button
          type="button"
          onClick={() => {
            handleEditClick("address");
            focusOnElement(addressRef);
          }}
        >
          Edit
        </button>
      </div>

      <div className="field">
        <label>Gender </label>
        <div className="gender-options">
          <label>
            <input
              type="radio"
              name="gender"
              value="true"
              checked={formData.gender === "true"}
              onChange={handleChange}
              disabled={!editableFields.gender}
            />
            Male
          </label>
          <label>
            <input
              type="radio"
              name="gender"
              value="false"
              checked={formData.gender === "false"}
              onChange={handleChange}
              disabled={!editableFields.gender}
            />
            Female
          </label>
          <label>
            <input
              type="radio"
              name="gender"
              value="other"
              checked={formData.gender === "other"}
              onChange={handleChange}
            />
            Other
          </label>
        </div>
      </div>
      <button type="submit">Update</button>
      <button type="button" onClick={() => navigate("/user")}>
        Cancel
      </button>
    </form>
  );
};

export default UpdateInfo;
