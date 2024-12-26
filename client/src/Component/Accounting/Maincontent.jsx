import React from "react";
import "../../Assets/css/MainContent.css";
import Customer from "../Profile/Customer";

const MainContent = ({ selectedFunction }) => {
  const renderContent = () => {
    const contentMap = {
      "account-management": <Customer />,
      "financial-report": <p>Báo cáo tài chính</p>,
      "employee-management": <p>Quản lý nhân sự</p>,
      payroll: <p>Theo dõi lương</p>,
      "production-plan": <p>Kế hoạch sản xuất</p>,
      "production-tracking": <p>Theo dõi sản xuất</p>,
    };

    return (
      contentMap[selectedFunction] || <p>Chọn một chức năng để bắt đầu.</p>
    );
  };

  return <div className="main-content">{renderContent()}</div>;
};

export default MainContent;
