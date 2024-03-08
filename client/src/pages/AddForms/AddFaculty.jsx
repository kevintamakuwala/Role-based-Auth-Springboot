import React from "react";
import "./../../styles/Components.scss";
import usePostRequest from "../../hooks/usePostRequest";
import { baseurl } from "../../config";
import { useNavigate } from "react-router-dom";

const AddFaculty = () => {
  const navigate = useNavigate();

  const {
    data: responseData,
    loading,
    error,
    postData,
  } = usePostRequest(`${baseurl}/faculty`);

  const handleSubmit = (e) => {
    e.preventDefault();

    const formData = new FormData(e.target);

    const data = {
      fullName: formData.get("fullName"),
      email: formData.get("email"),
      password: formData.get("password"),
    };

    postData(data);
    
  };

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error}</div>;
  }

  return (
    <div className="form__container">
      <div className="form__header">
        <h1>Add Faculty</h1>
      </div>

      <div className="form">
        <form onSubmit={handleSubmit}>
          <input type="text" name="fullName" placeholder="Full Name" />
          <input type="email" name="email" placeholder="Email" />
          <input type="password" name="password" placeholder="Password" />

          <input type="submit" value="Add Faculty" />
        </form>
      </div>
    </div>
  );
};

export default AddFaculty;
