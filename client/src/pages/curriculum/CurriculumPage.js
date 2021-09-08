import React, { Component } from "react";
import PageTemplate from "../../components/common/PageTemplate";
import CurriculumContainer from "../../containers/curriculum/CurriculumContainer";

function CurriculumPage() {
  return (
    <PageTemplate>
      <CurriculumContainer />
    </PageTemplate>
  );
}

export default CurriculumPage;
