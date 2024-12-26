import React, { useEffect, useState } from "react";
import { Outlet, useLocation } from "react-router-dom";
import "./App.css";
import Sidebar from "./Component/Accounting/SideBar.jsx";
import Footer from "./Component/HeaderFooter/Footer.jsx";
import Header from "./Component/HeaderFooter/Header.jsx";

const App = () => {
  const location = useLocation();

  const [isDesktop, setIsDesktop] = useState(true);

  useEffect(() => {
    const handleResize = () => {
      setIsDesktop(window.innerWidth >= 1024);
    };

    handleResize();

    window.addEventListener("resize", handleResize);

    return () => {
      window.removeEventListener("resize", handleResize);
    };
  }, []);

  const hideSidebarRoutes = new Set([
    "/login",
    "/register",
    "/error404",
    "/home",
  ]);

  const showSidebar = !hideSidebarRoutes.has(location.pathname);

  if (!isDesktop) {
    return <h2>Ứng dụng này yêu cầu màn hình có độ rộng ít nhất 1024px.</h2>;
  }

  return (
    <>
      <div className="header">
        <Header />
      </div>

      <div
        className={`content-container ${
          showSidebar ? "with-sidebar" : "no-sidebar"
        }`}
      >
        {showSidebar && <Sidebar />}
        <div className="main-content">
          <Outlet />
        </div>
      </div>

      <Footer />
    </>
  );
};

export default App;
