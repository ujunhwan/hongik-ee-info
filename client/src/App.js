import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import MainContainer from "./containers/main/Main";
import SubjectsContainer from "./containers/subjects/Subjects";
import GradContainer from "./containers/graduation/Grad";
import CurrContainer from "./containers/curriculum/Curr";
import AboutContainer from "./containers/about/About";

import "./App.scss";
import CommonHeadContainer from "./containers/CommonHeadContainer";

function App() {
  return (
    <>
      <Router>
        <CommonHeadContainer />
        <Switch>
          <Route path="/about" component={AboutContainer} />
          <Route path="/subjects" component={SubjectsContainer} />
          <Route path="/curriculum" component={CurrContainer} />
          <Route path="/graduation" component={GradContainer} />
          <Route path="/" component={MainContainer} />
        </Switch>
      </Router>
    </>
  );
}

export default App;
