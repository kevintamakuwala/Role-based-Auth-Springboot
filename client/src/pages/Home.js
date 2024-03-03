

import { baseurl } from "../config";
import "../styles/Home.scss";

import useFetch from "./../hooks/useFetch";


const Home = () => {
  const { data, loading, error } = useFetch(`${baseurl}/users/me`);

  if (loading) return <div>Loading...</div>;

  if (error) return <div>error occurded: {error}</div>;

  return (
    <div className="home__container">
        Home page
        {console.log(data)}
    </div>
  );
};



export default Home;