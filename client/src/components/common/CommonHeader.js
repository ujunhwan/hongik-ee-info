import React from "react";
import { useSelector } from "react-redux";
import { Link } from "react-router-dom";

import "./CommonHeader.scss";

const CommonHeader = ({ onLoginHandler, onLogoutHandler }) => {
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
              <Link className="main" to="/">
                HOME
              </Link>
            </li>
            <li>
              <Link className="graduation" to="/graduation">
                GRADUATION
              </Link>
            </li>
            <li>
              <Link className="curriculum" to="/curriculum">
                CURRICULUM
              </Link>
            </li>
            <li>
              <Link className="subjects" to="/subjects">
                SUBJECTS
              </Link>
            </li>
            <li>
              <Link className="about" to="/about">
                ABOUT
              </Link>
            </li>

            {!isLogin && (
              <li>
                <a className="login" onClick={onLoginHandler}>
                  LOGIN
                </a>
              </li>
            )}
            {isLogin && (
              <li>
                <a className="logout" onClick={onLogoutHandler}>
                  LOGOUT
                </a>
              </li>
            )}
          </ul>
        </div>
      </header>
    </div>
  );
};

export default CommonHeader;
