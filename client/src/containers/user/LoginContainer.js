import React, { Fragment, useEffect, useState } from "react";
import LoginComponent from "../../components/login/LoginForm";
import { useDispatch } from "react-redux";
import { loginUser } from "../../modules/user";
import { withRouter } from "react-router-dom";

/* not using */
function LoginContainer({ history, closeModalHandler }) {
  const dispatch = useDispatch();

  const [id, setId] = useState("");
  const [password, setPassword] = useState("");

  const onIdHandler = (event) => {
    setId(event.currentTarget.value);
  };

  const onPasswordHandler = (event) => {
    setPassword(event.currentTarget.value);
  };

  const onSubmitHandler = (event) => {
    event.preventDefault();

    let body = {
      ID: id,
      PASSWD: password,
    };

    dispatch(loginUser(body)).then((response) => {
      if (response.payload.isLogin == "true") {
        history.replace("/");
        closeModalHandler();
        localStorage.setItem("auth", response.payload.token);
      } else {
        alert("Login Error");
      }
    });
  };

  return (
    <Fragment>
      <LoginComponent
        handleModalClose={closeModalHandler}
        onSubmitHandler={onSubmitHandler}
        onIdHandler={onIdHandler}
        onPasswordHandler={onPasswordHandler}
      />
    </Fragment>
  );
}

export default withRouter(LoginContainer);
