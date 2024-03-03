import React from "react";
import useFetch from "../hooks/useFetch";
import "../styles/Labs.scss";
import { baseurl } from "../config";

const LabsResources = () => {
  const { data: labs, loading, error } = useFetch(`${baseurl}/labs`);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error}</div>;
  }
  return (
    <div className="labs_resources__container">
      <div className="labs__header">
        <h1>Labs Resources</h1>
      </div>
      <div className="labs__content">
        {labs?.map((lab) => {
          return <LabResourceCard lab={lab} />;
        })}
      </div>
    </div>
  );
};

const LabResourceCard = ({ lab }) => {
  return (
    <div className="lab__card">
      <h2>{lab?.name}</h2>
      <ul>
        {lab?.resources?.map((resource) => {
          return (
            <li key={resource.resourceId}>
              {resource.resourceName} : {resource.quantity}
            </li>
          );
        })}
      </ul>
    </div>
  );
};

export default LabsResources;
