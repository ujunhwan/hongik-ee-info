import axios from "axios";
import React, { Component, useState } from "react";
import { useDispatch } from "react-redux";
import RegisterForm from "../../components/register/RegisterForm";

const majorLabels = [
  "device",
  "circuit",
  "energy",
  "communication",
  "embedded",
  "antenna",
  "ai",
];

const dragonballLabels = [];
const mscLabels = ["math", "science", "computer"];

function RegisterContainer(props) {
  const dispatch = useDispatch();

  const [level, setLevel] = useState("");
  const [detail, setDetail] = useState("");
  const [classInfo, setClassInfo] = useState({
    id: "",
    name: "",
    credit: "",
  });

  const [type, setType] = useState("");

  const [majorList, setMajorList] = useState({
    device: false,
    circuit: false,
    energy: false,
    communication: false,
    embedded: false,
    antenna: false,
    ai: false,
  });

  const onTypeHandler = (event) => {
    const currType = event.currentTarget.value;
    setType(currType);

    if (currType === "msc") {
      setDetail("math");
    } else if (currType === "dragonball") {
      setDetail("DB1");
    }

    setMajorList({
      device: false,
      circuit: false,
      energy: false,
      communication: false,
      embedded: false,
      antenna: false,
      ai: false,
    });
  };

  const onInfoHandler = (e) => {
    const { value, name } = e.target;
    setClassInfo({
      ...classInfo,
      [name]: value,
    });
  };

  const onDetailHandler = (event) => {
    setDetail(event.currentTarget.value);
  };

  const onMajorHandler = (e) => {
    const { name } = e.target;
    let payload = false;
    if (majorList[name] == false) {
      payload = true;
    }

    setDetail("major");

    setMajorList({
      ...majorList,
      [name]: payload,
    });
  };

  const onSubmitHandler = (e) => {
    e.preventDefault();

    const url = "/api/class/registeration";
    let data = {
      id: classInfo.id,
      name: classInfo.name,
      credit: classInfo.credit,
      type: type,
      detail: detail,
    };

    if (data.type === "major") {
      data = {
        ...data,
        major: majorList,
        level: level,
      };
    }
    console.log(data);
    // TODO: major 아무것도 선택 안했을 경우 FAIL

    const req = axios.post(url, data).then((res) => console.log(res));
  };

  const onLevelHandler = (e) => {
    const { value } = e.target;
    setLevel(value);
  };

  return (
    <div>
      <RegisterForm
        onInfoHandler={onInfoHandler}
        onDetailHandler={onDetailHandler}
        onMajorHandler={onMajorHandler}
        onSubmitHandler={onSubmitHandler}
        onTypeHandler={onTypeHandler}
        majorLabels={majorLabels}
        onLevelHandler={onLevelHandler}
        type={type}
      />
    </div>
  );
}

export default RegisterContainer;
