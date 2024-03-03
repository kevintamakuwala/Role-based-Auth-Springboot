import "../styles/Components.scss";
import menuIcon from "../assets/menu_icon.png";
import { useUser } from "../context/UserContext";

const Navbar = ({ setIsSidebarActive }) => {
  const { userDetails } = useUser();
  return (
    <nav>
      <div className="icon">
        <img
          src={menuIcon}
          onClick={() => setIsSidebarActive(true)}
          style={{ cursor: "pointer" }}
          alt="menu"
        />
      </div>

      {userDetails?.fullName && (
        <span id="active_menu" style={{ color: "white" }}>
          Welcome, {userDetails?.fullName}
        </span>
      )}
    </nav>
  );
};

export default Navbar;
