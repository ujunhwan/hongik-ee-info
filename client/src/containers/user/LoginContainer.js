import React, { Fragment, useState } from "react";
import LoginComponent from "../../components/login/LoginForm";
import { useDispatch } from "react-redux";
import { loginUser } from "../../modules/user";
import { withRouter } from "react-router-dom";

function LoginContainer({ history, closeModalHandler }) {
  // console.log(props);
  const dispatch = useDispatch();

  const [Id, setId] = useState("");
  const [Password, setPassword] = useState("");

  const onIdHandler = (event) => {
    setId(event.currentTarget.value);
  };

  const onPasswordHandler = (event) => {
    setPassword(event.currentTarget.value);
  };

  const onSubmitHandler = (event) => {
    event.preventDefault();

    let body = {
      Id: Id,
      Pw: Password,
    };

    dispatch(loginUser(body)).then((response) => {
      console.log(response);

      if (response.payload) {
        history.replace("/");
        closeModalHandler();
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
