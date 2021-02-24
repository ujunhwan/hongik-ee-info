import React, { Component, Fragment } from "react";
import CommonHeader from "../../components/common/CommonHeader";
import PageTemplate from "../../components/common/PageTemplate";
import CommonHeadContainer from "../CommonHeadContainer";
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
