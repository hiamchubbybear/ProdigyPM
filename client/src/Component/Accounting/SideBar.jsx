import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../../Assets/css/SideBar.css"; // Import CSS riêng cho Sidebar
import { menuStructure } from "./MenuStructure.js";

function Sidebar() {
  const [expandedMenus, setExpandedMenus] = useState({});
  const [selectedItem, setSelectedItem] = useState(null);
  const navigate = useNavigate();

  // Toggle expand/collapse menu
  const toggleMenu = (id) => {
    setExpandedMenus((prev) => ({ ...prev, [id]: !prev[id] }));
  };

  // Handle selecting a menu item
  const handleSelectItem = (id, path) => {
    setSelectedItem(id);
    navigate(path);
  };

  // Recursive menu rendering
  const renderMenu = (menu) => (
    <li key={menu.id} className="menu-item-container">
      <div
        className={`menu-item ${expandedMenus[menu.id] ? "expanded" : ""} ${
          selectedItem === menu.id ? "selected" : ""
        }`}
        onClick={() => toggleMenu(menu.id)}
      >
        {menu.name}
      </div>

      {menu.children && expandedMenus[menu.id] && (
        <ul className="submenu">
          {menu.children.map((child) => (
            <li
              key={child.id}
              className={`submenu-item ${
                selectedItem === child.id ? "selected" : ""
              }`}
              onClick={() => handleSelectItem(child.id, child.path)}
            >
              {child.name}
            </li>
          ))}
        </ul>
      )}
    </li>
  );

  return (
    <div className="sidebar">
      <h3 className="sidebar-title">Chức năng</h3>
      <ul className="menu-list">{menuStructure.map(renderMenu)}</ul>
    </div>
  );
}

export default Sidebar;
