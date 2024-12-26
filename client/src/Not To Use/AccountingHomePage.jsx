import React, { useState } from "react";
import "../../Assets/css/AccountingHomePage.css";
import AddJournalEntry from "../Component/Accounting/AddJournalEntryDetail.jsx";
import DashBoard from "../Component/Accounting/Dashboard.js";
import JournalEntrySearch from "./JournalEntrySearch.jsx";
import ParentAccount from "./ParentAccountingProfile.jsx";

function AccountingPage() {
  const [selectedFunction, setSelectedFunction] = useState("");
  const [isSidebarExpanded, setIsSidebarExpanded] = useState(true);

  const functions = [
    { name: "Quản lý tài khoản", id: "account-management" },
    { name: "Danh sách tài khoản (DashingTable)", id: "dashboard" },
    { name: "Thêm giao dịch", id: "add-transaction" },
    { name: "Sửa giao dịch", id: "edit-transaction" },
    { name: "Xóa giao dịch", id: "delete-transaction" },
    { name: "Báo cáo tài chính", id: "financial-report" },
    { name: "Kiểm tra ngân sách", id: "budget-check" },
    { name: "Tìm kiếm giao dịch", id: "search-transaction" },
  ];

  const renderContent = () => {
    switch (selectedFunction) {
      case "account-management":
        return <ParentAccount />;
      case "dashboard":
        return <DashBoard />;
      case "add-transaction":
        return <AddJournalEntry />;
      case "edit-transaction":
        return (
          <p>
            Chức năng Sửa giao dịch giúp bạn chỉnh sửa các giao dịch hiện tại.
          </p>
        );
      case "delete-transaction":
        return (
          <p>
            Chức năng Xóa giao dịch hỗ trợ xóa các giao dịch không còn cần
            thiết.
          </p>
        );
      case "financial-report":
        return (
          <p>
            Chức năng Báo cáo tài chính cung cấp các báo cáo chi tiết về tài
            chính của bạn.
          </p>
        );
      case "budget-check":
        return (
          <p>
            Chức năng Kiểm tra ngân sách giúp theo dõi và kiểm tra chi tiêu theo
            ngân sách định trước.
          </p>
        );
      case "search-transaction":
        return <JournalEntrySearch />;
      default:
        return <p>Chọn một chức năng để bắt đầu.</p>;
    }
  };

  return (
    <div style={{ display: "flex" }}>
      {/* Sidebar */}
      <div
        className={`sidebar ${isSidebarExpanded ? "expanded" : "collapsed"}`}
      >
        {isSidebarExpanded && (
          <>
            <h3>Chức năng Accounting</h3>
            <ul className="link-style">
              {functions.map((func) => (
                <li
                  key={func.id}
                  onClick={() => setSelectedFunction(func.id)}
                  className={`link-style ${
                    selectedFunction === func.id ? "active" : ""
                  }`}
                >
                  {func.name}
                </li>
              ))}
            </ul>
          </>
        )}
        <button
          className="toggle-button-small"
          onClick={() => setIsSidebarExpanded(!isSidebarExpanded)}
        >
          {isSidebarExpanded ? "Thu nhỏ" : "Mở rộng"}
        </button>
      </div>

      {/* Main content */}
      <div className="main-content">{renderContent()}</div>
    </div>
  );
}

export default AccountingPage;
