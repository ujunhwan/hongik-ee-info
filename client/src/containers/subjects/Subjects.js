import React, { Component } from "react";
import PageTemplate from "../../components/common/PageTemplate";
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
