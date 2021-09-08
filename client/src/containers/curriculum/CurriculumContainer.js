import axios from "axios";
import React, { Component, Fragment, useEffect } from "react";
import { useSelector } from "react-redux";
import PageTemplate from "../../components/common/PageTemplate";
import CurriculumForm from "../../components/curriculum/CurriculumForm";

function CurriculumContainer(props) {
  const isLogin = useSelector((state) => state.user.isLogin);
  const token = useSelector((state) => state.user.token);
  useEffect(() => {
    console.log("Curriculum!");
    const url = "/api/user/class";
    console.log(token);
    axios
      .get(url, {
        params: {
          token: token,
        },
      })
      .then((res) => console.log(res));
  }, []);

  return (
    <PageTemplate>
      <CurriculumForm isLogin={isLogin} />
    </PageTemplate>
  );
}

export default CurriculumContainer;
