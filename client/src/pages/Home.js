import { baseurl } from "../config";
import { useUser } from "../context/UserContext";
import useFetch from "../hooks/useFetch";
import "../styles/Home.scss";

const Home = () => {
  const { setUserDetails } = useUser();

  const { data, loading, error } = useFetch(`${baseurl}/users/me`);

  if (data) {
    setUserDetails(data);
  }

  if (loading) {
    return <div>Loading...</div>;
  }
  if (error) {
    return <div>Error: {error}</div>;
  }

  return <div className="home__container">Home page</div>;
};

export default Home;
