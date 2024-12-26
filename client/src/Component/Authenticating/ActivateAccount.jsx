import Cookies from "js-cookie";
import { useEffect, useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import { activateAccountRequest } from "../../Api/apiRequest.js";
import "../../Assets/css/ActivateAccount.css";

const ActivateAccount = () => {
  const navigate = new useNavigate("");
  const [loading, setLoading] = useState(false);
  const [token, setToken] = useState();
  const [respone, setRespone] = useState("");
  const tokenRef = useRef();
  useEffect(() => {
    tokenRef?.current?.focus();
  }, []);
  const onSubmitHandle = async (event) => {
    event.preventDefault();
    const email = Cookies.get("email");
    console.log(email);
    try {
      await activateAccountRequest(token, email).then((response) => {
        setRespone(response.data.data);
        if (response.data?.data?.activated) {
          setRespone("You activated successfully.");
        } else {
          setRespone("You activated failed.");
        }
        setLoading(true);
        navigate("/user");
      });
    } catch (error) {
      if (error?.response == 500) {
        setRespone("Server not found");
      } else if (error?.response == 401) {
        setRespone("You activated failed");
      }
    }
  };
  return !loading ? (
    <div className="container">
      <section>
        <form onSubmit={onSubmitHandle}>
          <input
            id="activate"
            type="text"
            ref={tokenRef}
            onChange={(event) => setToken(event.target.value)}
            maxLength={6}
            placeholder={"Please enter token"}
          />
          <button className="button" type="submit">
            Send
          </button>
          <div className="">
            <h2 className="annouce">{respone}</h2>
          </div>
        </form>
      </section>
    </div>
  ) : (
    navigate("/user")
  );
};
export default ActivateAccount;
