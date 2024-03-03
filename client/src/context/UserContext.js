import React, { createContext, useState, useContext } from "react";

const UserContext = createContext();

export const UserProvider = ({ children }) => {
  const [userType, setUserType] = useState(() => {
    const storedUserType = localStorage.getItem("userType");
    return storedUserType ? storedUserType : "";
  });

  const [userDetails, setUserDetails] = useState({})

  return (
    <UserContext.Provider value={{ userType, setUserType, userDetails, setUserDetails }}>
      {children}
    </UserContext.Provider>
  );
};

export const useUser = () => useContext(UserContext);
