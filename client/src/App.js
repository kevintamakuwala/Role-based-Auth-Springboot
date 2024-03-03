import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import { useEffect, useState } from "react";

// Import components for each route
import Home from "./pages/Home";
import Login from "./pages/Login";

import Navbar from "./components/Navbar";
import Sidebar from "./components/Sidebar";
import { useUser } from "./context/UserContext";
import Labs from "./pages/Labs";
import LabsResources from "./pages/LabsResources";
import Faculties from "./pages/Faculties";
import Error404 from "./pages/Error404";

function App() {
  var { userType, setUserType } = useUser();

  const [isSidebarActive, setIsSidebarActive] = useState(false);

  useEffect(() => {
    const storedUserType = localStorage.getItem("userType");
    setUserType(storedUserType || "");
  }, [setUserType]);

  useEffect(() => {
    userType = localStorage.getItem("userType");
  }, [userType]);

  return (
    <div
      className="App"
      style={isSidebarActive ? { overflowY: "hidden" } : { overflowY: "unset" }}
    >
      <BrowserRouter>
        {userType && (
          <>
            <Sidebar
              isSidebarActive={isSidebarActive}
              setIsSidebarActive={setIsSidebarActive}
            />

            <Navbar
              setIsSidebarActive={setIsSidebarActive}
            />
          </>
        )}

        <Routes>
          <Route
            path="/"
            element={
              <ProtectedRoute roles={["HOD", "FACULTY"]} element={<Home />} />
            }
          />
          <Route
            path="/labs"
            element={
              <ProtectedRoute roles={["HOD", "FACULTY"]} element={<Labs />} />
            }
          />
          <Route
            path="/labs-resources"
            element={
              <ProtectedRoute
                roles={["HOD", "FACULTY"]}
                element={<LabsResources />}
              />
            }
          />
          <Route
            path="/faculties"
            element={<ProtectedRoute roles={["HOD"]} element={<Faculties />} />}
          />
          <Route path="/login" element={<Login />} />
          <Route path="/*" element={<Error404 />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

const ProtectedRoute = ({ element, roles }) => {
  const { userType } = useUser();
  const token = localStorage.getItem("token");
  const isAuthenticated = !!token;

  if (!isAuthenticated) {
    return <Navigate to="/login" />;
  }

  if (roles && roles.length > 0 && !roles.includes(userType)) {
    return <Navigate to="/404" />;
  }

  return element;
};

export default App;
