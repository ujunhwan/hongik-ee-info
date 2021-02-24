import React, { Component } from "react";
import CommonHeader from "../../components/common/CommonHeader";
import PageTemplate from "../../components/common/PageTemplate";
import CommonHeadContainer from "../CommonHeadContainer";
import SubjectsContentsContainer from "./SubjectsContentsContainer";

class SubjectsContainer extends Component {
  render() {
    return (
      <PageTemplate>
        <SubjectsContentsContainer />
      </PageTemplate>
    );
  }
}

export default SubjectsContainer;
