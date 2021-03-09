import React, { Fragment, Component } from "react";

import "./LoginForm.scss";

const LoginForm = ({
  handleModalClose,
  onSubmitHandler,
  onIdHandler,
  onPasswordHandler,
}) => {
  return (
    <Fragment>
      <div className="login-wrapper">
        <div className="login-block">
          <div className="exit-wrapper">
            <button className="exit-button" onClick={handleModalClose}>
              x
            </button>
          </div>
          <div className="contents-block">
            <h2 className="login-name">LOGIN</h2>
            <section className="input-wrapper">
              <div className="input-form">
                <div className="input-id">
                  <input
                    type="text"
                    size="13"
                    maxLength="7"
                    placeholder="학번"
                    onChange={onIdHandler}
                  ></input>
                </div>
                <div className="input-pw">
                  <input
                    type="password"
                    size="13"
                    placeholder="비밀번호"
                    onChange={onPasswordHandler}
                  ></input>
                </div>
              </div>
              <button className="login-button" onClick={onSubmitHandler}>
                LOGIN
              </button>
            </section>

            <div className="input-notice">
              클래스넷 학번/비밀번호를 입력해주세요.
            </div>
          </div>
        </div>
      </div>
    </Fragment>
  );
};
export default LoginForm;
