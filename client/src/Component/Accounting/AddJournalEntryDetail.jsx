import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { addJournalEntryRequest } from "../../Api/apiRequest";
import "../../Assets/css/AddJournalEntry.css";
import ToastService from "../../Toast/ToastService";
import { ChartOfAccount, Currencies } from "../../Variable/Currencies";

const AddJournalEntry = () => {
  const navigate = useNavigate();
  const [status, setStatus] = useState("");
  const [journalEntry, setJournalEntry] = useState({
    journalEntryDate: "",
    chartOfAccountCode: "112",
    debit: 0.0,
    credit: 0.0,
    currency: "VND",
    createBy: "",
  });

  const chartOfAccounts = ChartOfAccount;
  const currencies = Currencies;

  const handleChange = (e) => {
    const { name, value } = e.target;
    setJournalEntry((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };
  const handleSubmit = (e) => {
    e.preventDefault();

    addJournalEntryRequest(journalEntry, localStorage.getItem("token"))
      .then((res) => {
        if (res?.data?.data === true) {
          ToastService.success("Add entry success", navigate, "/home");
        } else {
          ToastService.error("Add entry failed", navigate, "/error");
        }
      })
      .catch((err) => {
        ToastService.error("Some error happened", navigate, "/error");
      });
  };
  const formStyle = {
    maxWidth: "500px",
    margin: "0 auto",
    padding: "20px",
    backgroundColor: "#EAEAEA",
    borderRadius: "8px",
    boxShadow: "0 2px 5px rgba(0, 0, 0, 0.1)",
    fontFamily: "'Cascadia Code', sans-serif",
  };

  const inputStyle = {
    width: "100%",
    padding: "10px",
    fontSize: "16px",
    border: "1px solid #1D6F85",
    borderRadius: "5px",
    boxSizing: "border-box",
    backgroundColor: "#FFF",
    color: "#333",
    transition: "border-color 0.3s",
  };

  const labelStyle = {
    fontWeight: "bold",
    display: "block",
    marginBottom: "8px",
    color: "#1D6F85",
  };

  const buttonStyle = {
    padding: "10px 20px",
    backgroundColor: "#1D6F85",
    color: "white",
    border: "none",
    borderRadius: "5px",
    fontSize: "16px",
    cursor: "pointer",
    width: "100%",
    transition: "background-color 0.3s, color 0.3s",
  };

  const buttonHoverStyle = {
    backgroundColor: "#3A9FBF",
  };

  return (
    <form onSubmit={handleSubmit} style={formStyle}>
      <div>
        <label htmlFor="date" style={labelStyle}>
          Ngày
        </label>
        <input
          type="date"
          id="journalEntryDate"
          name="journalEntryDate"
          value={journalEntry.date}
          onChange={handleChange}
          style={inputStyle}
          required={true}
        />
      </div>
      <div>
        <label htmlFor="chartOfAccountCode" style={labelStyle}>
          Chart of Account Code
        </label>
        <select
          id="chartOfAccountCode"
          name="chartOfAccountCode"
          value={journalEntry.chartOfAccountCode}
          onChange={handleChange}
          style={inputStyle}
        >
          {chartOfAccounts.map((account) => (
            <option key={account.code} value={account.code}>
              {account.code} - {account.name} ({account.category})
            </option>
          ))}
        </select>
      </div>
      <div>
        <label htmlFor="debit" style={labelStyle}>
          Debit
        </label>
        <input
          type="number"
          id="debit"
          name="debit"
          value={journalEntry.debit}
          onChange={handleChange}
          style={inputStyle}
        />
      </div>
      <div>
        <label htmlFor="credit" style={labelStyle}>
          Credit
        </label>
        <input
          type="number"
          id="credit"
          name="credit"
          value={journalEntry.credit}
          onChange={handleChange}
          style={inputStyle}
        />
      </div>
      <div>
        <label htmlFor="currency" style={labelStyle}></label>
        <select
          id="currency"
          name="currency"
          value={journalEntry.currency}
          onChange={handleChange}
          style={inputStyle}
        >
          {currencies.map((currency) => (
            <option value={currency.code} key={currency.code}>
              {currency.name} - {currency.code}
            </option>
          ))}
        </select>
      </div>
      <div>
        <button
          type="submit"
          style={buttonStyle}
          onMouseOver={(e) =>
            (e.target.style.backgroundColor = buttonHoverStyle.backgroundColor)
          }
          onMouseOut={(e) =>
            (e.target.style.backgroundColor = buttonStyle.backgroundColor)
          }
        >
          Submit
        </button>
        {status ? status : status}
      </div>
    </form>
  );
};

export default AddJournalEntry;
