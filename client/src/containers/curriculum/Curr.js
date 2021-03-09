import React, { Component, Fragment } from "react";
import PageTemplate from "../../components/common/PageTemplate";
import CurrContentsContainer from "./CurrContentsContainer";

class CurrContainer extends Component {
  render() {
    return (
      <PageTemplate>
        <CurrContentsContainer />
      </PageTemplate>
    );
  }
}

export default CurrContainer;
