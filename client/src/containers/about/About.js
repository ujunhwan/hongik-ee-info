import React, { Component } from "react";
import PageTemplate from "../../components/common/PageTemplate";
import CommonHeadContainer from "../CommonHeadContainer";
import AboutContentsContainer from "./AboutContentsContainer";
import ReactDOM from "react-dom";

class AboutContainer extends Component {
  render() {
    return (
      <PageTemplate>
        <AboutContentsContainer />
      </PageTemplate>
    );
  }
}

export default AboutContainer;
