import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import App from "./App.jsx";
import AccountingPage from "./Component/Accounting/AccountingPage.jsx";
import AddJournalEntry from "./Component/Accounting/AddJournalEntryDetail.jsx";
import Chart from "./Component/Accounting/ChartJournalEntry.jsx";
import Dashboard from "./Component/Accounting/Dashboard.js";
import ActivateAccount from "./Component/Authenticating/ActivateAccount";
import Login from "./Component/Authenticating/Login.jsx";
import NavigateToForgot from "./Component/Authenticating/NavigateToForgot";
import SignUp from "./Component/Authenticating/SignUp.jsx";
import HomePage from "./Component/HeaderFooter/HomePage";
import Customer from "./Component/Profile/Customer.jsx";
import UpdateInfo from "./Component/Profile/UpdateInfo.jsx";
import AuthProvider from "./Context/Context.jsx";
import Nothing from "./Error/Nothing/Nothing.js";
import Error404 from "./Error/Page/Error404.jsx";
import JournalEntrySearch from "./Not To Use/JournalEntrySearch.jsx";
import TreeDataDisableChildrenFiltering from "./Not To Use/Testing.js";
import reportWebVitals from "./reportWebVitals";
const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <AuthProvider>
        <Routes>
          <Route path="/" element={<App />}>
            <Route index element={<HomePage />} />
            <Route path="login" element={<Login />} />
            <Route path="signup" element={<SignUp />} />
            <Route path="/account-management" element={<Dashboard />} />
            <Route path="forgot" element={<NavigateToForgot />} />
            <Route path="activate" element={<ActivateAccount />} />
            <Route path="user/update" element={<UpdateInfo />} />
            <Route path="/user" element={<Customer />} />
            <Route path="/home" element={<HomePage />} />
            <Route path="error404" element={<Error404 />} />
            <Route path="nothing" element={<Nothing />} />
            <Route path="dashboard" element={<Dashboard />} />
            <Route path="/accounting/search" element={<JournalEntrySearch />} />
            <Route path="/accounting" element={<AccountingPage />} />
            <Route path="/budget-check" element={<Chart />} />

            <Route
              path="/accounting/add-transaction"
              element={<AddJournalEntry />}
            />
            <Route path="tree" element={<TreeDataDisableChildrenFiltering />} />

            <Route path="*" element={<Error404 />} />
          </Route>
        </Routes>
      </AuthProvider>
    </BrowserRouter>
  </React.StrictMode>
);

reportWebVitals();
