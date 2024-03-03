import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import { useState } from "react";

// Import components for each route
import Home from "./pages/Home";
import Login from "./pages/Login";

import Navbar from "./components/Navbar";
import Sidebar from "./components/Sidebar";

function App() {
  const [isSidebarActive, setIsSidebarActive] = useState(false);

  const [activeMenu, setActiveMenu] = useState(
    document.querySelector(".active")?.innerHTML
  );

  return (
    <div
      className="App"
      style={isSidebarActive ? { overflowY: "hidden" } : { overflowY: "unset" }}
    >
      <BrowserRouter>

        <Sidebar
          isSidebarActive={isSidebarActive}
          setIsSidebarActive={setIsSidebarActive}
          setActiveMenu={setActiveMenu}
        />

        <Navbar
          setIsSidebarActive={setIsSidebarActive}
          activeMenu={activeMenu}
        />

        <Routes>
          <Route path="/" element={<ProtectedRoute element={<Home />} />} />

          <Route path="/login" element={<Login />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

function ProtectedRoute({ element }) {
  const token = localStorage.getItem("token");
  const isAuthenticated = !!token;

  if (!isAuthenticated) {
    return <Navigate to="/login" />;
  }

  return element;
}

export default App;
