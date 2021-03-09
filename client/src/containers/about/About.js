import React, { Component } from "react";
import PageTemplate from "../../components/common/PageTemplate";
import AboutContentsContainer from "./AboutContentsContainer";

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
