import React, { Component } from "react";
import CommonHeader from "../../components/common/CommonHeader";
import PageTemplate from "../../components/common/PageTemplate";
import CommonHeadContainer from "../CommonHeadContainer";
import GradContentsContainer from "./GradContentsContainer";

class GradContainer extends Component {
  render() {
    return (
      <PageTemplate>
        <GradContentsContainer />
      </PageTemplate>
    );
  }
}

export default GradContainer;
