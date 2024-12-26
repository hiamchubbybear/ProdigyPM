import { createContext, useEffect, useState } from "react";
const AuthContext = createContext({});

export const AuthProvider = ({ children }) => {
  const [auth, setAuth] = useState(null);

  useEffect(() => {
    let token = localStorage.getItem("token");
    if (!token) {
      //   token = "dummy-token";
      //   localStorage.setItem("token", token);
    }
    setAuth({ token });
  }, []);

  return (
    <AuthContext.Provider value={{ auth, setAuth }}>
      {children}
    </AuthContext.Provider>
  );
};
export default AuthProvider;
