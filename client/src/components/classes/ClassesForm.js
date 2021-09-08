import axios from "axios";
import React, { Component, Fragment } from "react";

const onClickUpdate = () => {
  axios.put("/api/user")
  .then(response => {
    console.log(response);
    alert("update complete");
  })
}
const ClassesForm = ({ classes }) => {
  return (
    <Fragment>
      <button onClick = {onClickUpdate}>UPDATE</button>
      <h2>전공 목록</h2>
      <ul>
        <table>
          <thead>
            <tr>
              <th>학수번호</th>
              <th>과목명</th>
              <th>분야</th>
              <th>학점</th>
              <th>레벨</th>
            </tr>
          </thead>
          <tbody>
            {classes.map((c, idx) => (
              <tr key={idx}>
                <td>{c.classNumber}</td>

                <td>{c.className}</td>
                <td>{c.detail}</td>
                <td>{c.credit}</td>
                <td>{c.level}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </ul>
      <h2>교양 목록</h2>
      <ul></ul>
    </Fragment>
  );
};

export default ClassesForm;
