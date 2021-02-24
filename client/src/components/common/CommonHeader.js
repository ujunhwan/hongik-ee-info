import React from "react";
import { Link } from "react-router-dom";

import "./CommonHeader.scss";

const CommonHeader = ({ onLoginClick }) => (
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
          <li>
            <a className="login" onClick={onLoginClick}>
              LOGIN
            </a>
          </li>
        </ul>
      </div>
    </header>
  </div>
);

export default CommonHeader;
