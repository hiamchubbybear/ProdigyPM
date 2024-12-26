import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "../Api/axios";
import "../Assets/css/JournalEntry.css";
function JournalEntrySearch() {
  const navigate = useNavigate();
  const [selectedCriteria, setSelectedCriteria] = useState("");
  const [inputValue, setInputValue] = useState("");

  const handleCriteriaChange = (e) => {
    setSelectedCriteria(e.target.value);
    setInputValue("");
  };

  const handleInputChange = (e) => {
    setInputValue(e.target.value);
  };
  const handleSearch = (e) => {
    e.preventDefault();
    const token = localStorage.getItem("token");
    const REQUEST_URI = `/api/journal-entries/${selectedCriteria}?${selectedCriteria}=${inputValue}`;
    axios
      .get(REQUEST_URI, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        if (res?.data?.data != null) {
          localStorage.setItem(
            "journal-entries",
            JSON.stringify(res?.data?.data)
          );
          navigate("/dashboard");
        }
      })
      .catch((err) => {
        console.error("Error:", err);
      });
  };

  return (
    <div style={{ display: "flex" }} className="container">
      <div
        className="content-journal"
        style={{
          padding: "20px",
          fontFamily: "Cascadia Code, sans-serif",
          justifyContent: "center",
          alignItems: "center",
          flex: 1,
        }}
      >
        <h3>Find the Journal Entry</h3>
        <form onSubmit={handleSearch}>
          <label>
            Choose the search criteria
            <select
              value={selectedCriteria}
              onChange={handleCriteriaChange}
              style={inputStyle}
            >
              <option value="">Criteria</option>
              <option value="entryId">Entry ID</option>
              <option value="entryDate">Entry Date</option>
              <option value="description">Description</option>
              <option value="status">Status</option>
              <option value="createBy">Create By</option>
              <option value="createdDate">Create Date</option>
            </select>
          </label>
          <br />
          {selectedCriteria && (
            <div>
              <label>
                Input {selectedCriteria}:
                <input
                  type="text"
                  value={inputValue}
                  onChange={handleInputChange}
                  placeholder={`Input ${selectedCriteria}...`}
                  style={inputStyle}
                />
              </label>
            </div>
          )}
          <button type="submit" style={buttonStyle}>
            Tìm Kiếm
          </button>
        </form>
      </div>
    </div>
  );
}

const sidebarStyle = {
  width: "200px",
  backgroundColor: "#f8f9fa",
  padding: "10px",
  boxShadow: "2px 0 5px rgba(0,0,0,0.1)",
  height: "100vh",
};

const sidebarListStyle = {
  listStyleType: "none",
  padding: "0",
};

const linkStyle = {
  margin: "10px 0",
  cursor: "pointer",
  color: "#007bff",
  textDecoration: "none",
};

const inputStyle = {
  padding: "8px",
  margin: "10px 0",
  width: "300px",
  border: "1px solid #ccc",
  borderRadius: "4px",
};

const buttonStyle = {
  padding: "10px 20px",
  backgroundColor: "#007bff",
  color: "white",
  border: "none",
  borderRadius: "4px",
  cursor: "pointer",
};

export default JournalEntrySearch;
