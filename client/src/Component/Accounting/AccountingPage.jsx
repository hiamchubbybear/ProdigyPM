import React, { useState } from "react";
import "../../Assets/css/AccountingPage.css";

function AccountingPage() {
  const [selectedFunction, setSelectedFunction] = useState(null);

  const handleSelect = (id) => {
    console.log("Selected function ID:", id);
    setSelectedFunction(id);
  };

  return (
    <div className="accounting-page">
      {/* <Sidebar onSelect={handleSelect} /> */}

      <div className="main-content">
        {selectedFunction ? (
          <p>You selected: {selectedFunction}</p>
        ) : (
          <p>Please select a function.</p>
        )}
      </div>
    </div>
  );
}

export default AccountingPage;
