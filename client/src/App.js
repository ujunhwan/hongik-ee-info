import React, { useState, useEffect } from 'react';
import './style.css';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from 'react-router-dom';

function App() {
  return (
    <Router>
      <div className="wrapper main">
        <header className="header transparent">
          <div className="logo">
            <Link to="./home">HONGIK EE</Link>
          </div>
          <nav id="nav">
            <ul>
              <li>
                <Link to="/home">HOME</Link>
              </li>
              <li>
                <Link to="/grad">GRADUATION</Link>
              </li>
              <li>
                <Link to="/curriculum">CURRICULUM</Link>
              </li>
              <li>
                <Link to="/subjects">SUBJECTS</Link>
              </li>
              <li>
                <Link to="/about">ABOUT</Link>
              </li>
            </ul>
          </nav>
        </header>

        <Route path="/home" component={ShowBanner}></Route>

        <section id="contents">
          <Switch>
            <Route path="/about">
              <About />
            </Route>
            <Route path="/subjects">
              <Subjects />
            </Route>
            <Route path="/curriculum">
              <Curriculum />
            </Route>
            <Route path="/grad">
              <Graduation />
            </Route>
          </Switch>
          </section>
      </div>
    </Router>
  );
}

function ShowBanner() {
  return (
      <section id="banner">
        <div className="inner">
          <p>HONGIK UNIV.</p>
          <p>School of Electronic and Electrical Engineering</p>
        </div>
      </section>
  );
}

function Home() {
  return (
      <h1 className="hidden">home page</h1>
  );
}

function Graduation() {
    return (
        <h1>graduation page</h1>
    );
}

function Curriculum() {
  return (
      <h1>curriculum page</h1>
  )
}

function Subjects() {
  return (
      <h1>subjects page</h1>
  )
}

function About() {
  return (
      <h1>about page</h1>
  );
}

export default App;