import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import App from "./App";
import AccountingPage from "./pages/AccountingPage"; // Trang có Sidebar
import Error404Page from "./pages/Error404Page"; // Trang 404
import LoginPage from "./pages/LoginPage"; // Trang đăng nhập
import RegisterPage from "./pages/RegisterPage"; // Trang đăng ký

const RoutesComponent = () => (
  <Router>
    <Routes>
      <Route path="/" element={<App />}>
        {/* Các route có Sidebar */}
        <Route path="account-management" element={<AccountingPage />} />
        <Route path="financial-report" element={<AccountingPage />} />
        {/* Các route khác có Sidebar */}
        <Route path="login" element={<LoginPage />} />
        <Route path="register" element={<RegisterPage />} />
        <Route path="error404" element={<Error404Page />} />
      </Route>
    </Routes>
  </Router>
);

export default RoutesComponent;
