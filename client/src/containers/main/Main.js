import React, { Component } from "react";
import MainContentsContainer from "./MainContentsContainer";
import PageTemplate from "../../components/common/PageTemplate";

class MainContainer extends Component {
  constructor(props) {
    super(props);
  }
  render() {
    return (
      <PageTemplate>
        <MainContentsContainer />
      </PageTemplate>
    );
  }
}

export default MainContainer;
