import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import MainContainer from "./containers/main/Main";
import SubjectsContainer from "./containers/subjects/Subjects";
import GradContainer from "./containers/graduation/Grad";
import AboutContainer from "./containers/about/About";

import RegisterPage from "./pages/register/RegisterPage";

import "./App.scss";
import CommonHeadContainer from "./containers/common/CommonHeadContainer";
import ClassesPage from "./pages/classes/ClassesPage";
import CurriculumPage from "./pages/curriculum/CurriculumPage";
import ClassesContainer from "./containers/classes/ClassesContainer";
import LoginPage from "./pages/login/LoginPage";
import GraduationPage from "./pages/graduation/GraduationPage";

function App() {
  return (
    <>
      <Router>
        <CommonHeadContainer />
        <Switch>
          <Route path="/login" component={LoginPage} />
          <Route path="/classes" component={ClassesPage} />
          <Route path="/register" component={RegisterPage} />
          <Route path="/courses" component={ClassesContainer} />
          <Route path="/subjects" component={SubjectsContainer} />
          <Route path="/curriculum" component={CurriculumPage} />
          <Route path="/graduation" component={GraduationPage} />
          <Route path="/" component={MainContainer} />
        </Switch>
      </Router>
    </>
  );
}

export default App;
