import React from "react";
import "./../styles/Labs.scss";
import useFetch from "../hooks/useFetch";
import { baseurl } from "../config";

const Labs = () => {
  const {
    data: myLabs,
    loading: myLabsLoading,
    error: myLabsError,
  } = useFetch(`${baseurl}/labs/mylab`);

  const {
    data: otherLabs,
    loading: otherLabsLoading,
    error: otherLabsError,
  } = useFetch(`${baseurl}/labs/other-labs`);

  if (myLabsLoading || otherLabsLoading) {
    return <div>Loading...</div>;
  }

  if (myLabsError || otherLabsError) {
    return <div>Error: {myLabsError || otherLabsError}</div>;
  }

  return (
    <div className="labs__container">
      <div className="labs__header">
        <h1>My Labs</h1>
      </div>
      <div className="labs__content">
        <LabCard lab={myLabs} />
      </div>

      <div className="labs__header">
        <h1>Other Labs</h1>
      </div>
      <div className="labs__content">
        {otherLabs?.map((lab) => {
          return <LabCard lab={lab} />;
        })}
      </div>
    </div>
  );
};

const LabCard = ({ lab }) => {
  return (
    <div className="lab__card">
      <h2>{lab?.name}</h2>
      <ul>
        {lab?.faculties?.map((faculty) => {
          return <li key={faculty.id}>{faculty.fullName}</li>;
        })}
      </ul>
    </div>
  );
};

export default Labs;
