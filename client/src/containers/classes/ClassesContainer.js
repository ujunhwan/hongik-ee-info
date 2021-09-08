import axios from "axios";
import React, { Component, Fragment, useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import ClassesForm from "../../components/classes/ClassesForm";

function ClassesContainer() {
  // api 로 가져오기

  const [classes, setClasses] = useState([]);
  useEffect(() => {
    axios.get(url).then((res) => {
      console.log(res.data);
      setClasses(res.data);
    });
  }, []);

  const url = "/api/class";
  return (
    <Fragment>
      <ClassesForm classes={classes} />
    </Fragment>
  );
}

export default ClassesContainer;
