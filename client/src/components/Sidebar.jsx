import React from "react";
import closeIcon from "../assets/close_icon.png";
import { NavLink } from "react-router-dom";
import DDULogo from "../assets/logo.png";
import { useUser } from "../context/UserContext";

const Sidebar = ({ isSidebarActive, setIsSidebarActive, setActiveMenu }) => {
  const { userType } = useUser();

  const logout = () => {
    localStorage.removeItem("token");
    window.location.reload();
  };

  return (
    <>
      {isSidebarActive && (
        <div className="sidebar">
          <div className="sidebar__container">
            <div className="icon">
              <img
                src={closeIcon}
                onClick={() => setIsSidebarActive(false)}
                style={{ cursor: "pointer" }}
                alt="close"
              />
            </div>

            <div className="menu_list">
              <div className="logo">
                <img src={DDULogo} alt="ddu" />
              </div>
              <ul>
                <li>
                  <NavLink
                    to={"/"}
                    activeClassName="active_menu"
                    onClick={() => {
                      setActiveMenu("Home");
                      setIsSidebarActive(false);
                    }}
                  >
                    Home
                  </NavLink>
                </li>
                {userType !== "FACULTY" && (
                  <li>
                    <NavLink
                      to={"/faculties"}
                      activeClassName="active_menu"
                      onClick={() => {
                        setActiveMenu("Home");
                        setIsSidebarActive(false);
                      }}
                    >
                      Faculties
                    </NavLink>
                  </li>
                )}
                <li>
                  <NavLink
                    to={"/labs"}
                    activeClassName="active_menu"
                    onClick={() => {
                      setActiveMenu("Home");
                      setIsSidebarActive(false);
                    }}
                  >
                    Labs
                  </NavLink>
                </li>
                <li>
                  <NavLink
                    to={"/labs-resources"}
                    activeClassName="active_menu"
                    onClick={() => {
                      setActiveMenu("Home");
                      setIsSidebarActive(false);
                    }}
                  >
                    Labs Resources
                  </NavLink>
                </li>
              </ul>
            </div>

            <div className="logout_button">
              <button type="button" onClick={logout}>
                Logout
              </button>
            </div>
          </div>

          <div className="blur"></div>
        </div>
      )}
    </>
  );
};

export default Sidebar;
