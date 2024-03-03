import React from "react";
import useFetch from "../hooks/useFetch";
import "../styles/Labs.scss";
import { baseurl } from "../config";

const Faculties = () => {
  var { data: faculties, loading, error } = useFetch(`${baseurl}/users`);

  faculties = faculties?.filter((faculty) => faculty.roleName === "FACULTY");

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error}</div>;
  }
  return (
    <div className="faculties__container">
      <div className="faculties__header">
        <h1>Faculties</h1>
      </div>
      <div className="faculties__content">
        {faculties?.map((faculty) => {
          return <FacultyCard faculty={faculty} />;
        })}
      </div>
    </div>
  );
};

const FacultyCard = ({ faculty }) => {
  return (
    <div className="faculty__card">
      <h2>{faculty?.fullName}</h2>
      <ul>
        <li>Email : {faculty?.email}</li>
        <li>Lab Name : {faculty?.labName}</li>
      </ul>
    </div>
  );
};
export default Faculties;
