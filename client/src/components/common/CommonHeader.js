import React from "react";
import { useSelector } from "react-redux";
import { Link } from "react-router-dom";

import "./CommonHeader.scss";

const CommonHeader = ({
  onLoginHandler,
  onLogoutHandler,
  gradHandler,
  currHandler,
}) => {
  const isLogin = useSelector((state) => state.user.isLogin);
  return (
    <div className="header-wrapper">
      <header className="CommonHeader">
        <div className="left-area">
          <Link to="/" className="logo-button">
            HONGIK EE
          </Link>
        </div>
        <div className="right-area">
          <ul>

            <li>
              <Link className="register" to="/register">
                REGISTER
              </Link>
            </li>
            <li>
              <Link className="graduation" to="/graduation">
                GRADUATION
              </Link>
            </li>
            
            <li>
              <Link className="course" to="/course">
                COURSE 
              </Link>
            </li>

            {!isLogin && (
              <li>
                {/* <a className="login" onClick={onLoginHandler}>
                  LOGIN
                </a> */}
                <Link className="login" to="/login">
                  LOGIN
                </Link>
              </li>
            )}
            {isLogin && (
              <li>
                {/* <a className="logout" onClick={onLogoutHandler}>
                  LOGOUT
                </a> */}
                <Link className="logout" to="/logout">
                  LOGOUT 
                </Link>

              </li>
            )}
          </ul>
        </div>
      </header>
    </div>
  );
};

export default CommonHeader;
