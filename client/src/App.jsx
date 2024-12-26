import React from "react";
import { Outlet, useLocation } from "react-router-dom";
import "./App.css";
import Sidebar from "./Component/Accounting/SideBar.jsx";
import Footer from "./Component/HeaderFooter/Footer.jsx";
import Header from "./Component/HeaderFooter/Header.jsx";

const App = () => {
  const location = useLocation();

  // Define routes where Sidebar should not appear
  const hideSidebarRoutes = new Set([
    "/login",
    "/register",
    "/error404",
    "/home",
  ]);
  const showSidebar = !hideSidebarRoutes.has(location.pathname);

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
