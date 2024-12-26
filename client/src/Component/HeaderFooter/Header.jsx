import "bootstrap/dist/css/bootstrap.min.css";
import cookie from "js-cookie";
import { useCallback, useEffect, useState } from "react";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";
import "../../Assets/css/Header.css";

function Header() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [imageData, setImageData] = useState("");
  const [userName, setUserName] = useState("");

  // Fetch token, image data, and username
  useEffect(() => {
    const token = localStorage.getItem("token");
    setImageData(localStorage.getItem("image"));
    setUserName(localStorage.getItem("user")?.replaceAll('"', " ") || "");

    setIsLoggedIn(!!token);

    const handleStorageChange = () => {
      const updatedToken = localStorage.getItem("token");
      setIsLoggedIn(!!updatedToken);
    };
    window.addEventListener("storage", handleStorageChange);

    return () => {
      window.removeEventListener("storage", handleStorageChange);
    };
  }, []);

  // Optimized logout function using useCallback
  const onClickLogoutHandle = useCallback(() => {
    localStorage.clear();
    cookie.remove("customer");
    setIsLoggedIn(false);
  }, []);

  return (
    <Navbar
      expand="lg"
      style={{ backgroundColor: "#F2F2F2", padding: "5px 15px" }}
    >
      <Container fluid style={{ maxWidth: "1200px" }}>
        <Navbar.Brand href="/" style={{ color: "#0B698B", fontSize: "16px" }}>
          ProdigyPM
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="navbarScroll" />
        <Navbar.Collapse id="navbarScroll" style={{ color: "#0396A6" }}>
          <Nav className="me-auto my-2 my-lg-0" navbarScroll>
            <Nav.Link href="/home" style={{ fontSize: "16px" }}>
              Home
            </Nav.Link>
            <NavDropdown
              title="Feature"
              id="navbarScrollingDropdown"
              style={{ color: "#0B698B", fontSize: "16px" }}
            >
              <NavDropdown.Item href="/product" style={{ fontSize: "16px" }}>
                Management & Permissions
              </NavDropdown.Item>
              <NavDropdown.Item href="/accounting" style={{ fontSize: "16px" }}>
                Accounting
              </NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item href="/forgot" style={{ fontSize: "16px" }}>
                Sales
              </NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item href="/forgot" style={{ fontSize: "16px" }}>
                Inventory
              </NavDropdown.Item>
            </NavDropdown>
            <Nav.Link href="/solution" style={{ fontSize: "16px" }}>
              Solution
            </Nav.Link>
            <Nav.Link href="/docs" style={{ fontSize: "16px" }}>
              Documentation
            </Nav.Link>
            <Nav.Link href="/resources" style={{ fontSize: "16px" }}>
              Resource
            </Nav.Link>
            <Nav.Link href="/pricing" style={{ fontSize: "16px" }}>
              Pricing
            </Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Container>

      {/* Conditional rendering based on login status */}
      {isLoggedIn ? (
        <Nav style={{ marginRight: "150px" }}>
          <Nav.Link href="/user" style={{ fontSize: "16px" }}>
            <img
              src={
                imageData
                  ? `data:image/png;base64,${imageData}`
                  : "/path/to/default-avatar.png"
              } // Fallback image
              alt="Avatar"
              style={{ width: "30px", borderRadius: "50%" }}
            />
          </Nav.Link>
          <NavDropdown
            title={userName}
            id="navbarScrollingDropdown"
            style={{ color: "#0B698B", fontSize: "16px" }}
          >
            <NavDropdown.Item href="/user" style={{ fontSize: "16px" }}>
              Profile
            </NavDropdown.Item>
            <NavDropdown.Item href="/setting" style={{ fontSize: "16px" }}>
              Settings
            </NavDropdown.Item>
            <NavDropdown.Item
              href="/home"
              onClick={onClickLogoutHandle}
              style={{ fontSize: "16px" }}
            >
              Logout
            </NavDropdown.Item>
          </NavDropdown>
        </Nav>
      ) : (
        <Nav style={{ marginRight: "150px" }}>
          <Nav.Link href="/login" style={{ fontSize: "16px" }}>
            Login
          </Nav.Link>
          <Nav.Link href="/signup" style={{ fontSize: "16px" }}>
            Register
          </Nav.Link>
        </Nav>
      )}
    </Navbar>
  );
}

export default Header;
