import React, { Component, Fragment } from "react";

const RegisterForm = ({
  onInfoHandler,
  onSubmitHandler,
  onDetailHandler,
  onMajorHandler,
  onTypeHandler,
  onLevelHandler,
  majorLabels,
  type,
}) => {
  return (
    <>
      <form onSubmit={onSubmitHandler}>
        <label>학수번호</label>
        <input
          name="id"
          type="text"
          placeholder="ex)101101"
          onChange={onInfoHandler}
        ></input>
        <label>과목명</label>
        <input
          name="name"
          type="text"
          placeholder="ex)전자기학"
          onChange={onInfoHandler}
        ></input>
        <label>학점</label>
        <input
          name="credit"
          type="text"
          placeholder="ex)333"
          onChange={onInfoHandler}
        ></input>
        <select name="type" onChange={onTypeHandler}>
          <option value="required">필수교양</option>
          <option value="msc">MSC</option>
          <option value="dragonball">드래곤볼</option>
          <option value="major">전공</option>
        </select>
        <div>
          {type === "msc" && (
            <select name="mscDetail" onChange={onDetailHandler}>
              <option value="math">수학</option>
              <option value="science">과학</option>
              <option value="computer">전산</option>
            </select>
          )}
          {type === "dragonball" && (
            <select name="generalDetail" onChange={onDetailHandler}>
              <option value="DB1">드래곤볼1</option>
              <option value="DB2">드래곤볼2</option>
              <option value="DB3">드래곤볼3</option>
              <option value="DB4">드래곤볼4</option>
              <option value="DB5">드래곤볼5</option>
              <option value="DB6">드래곤볼6</option>
              <option value="DB7">드래곤볼7</option>
            </select>
          )}
          {type === "major" && (
            <>
              <ul>
                {majorLabels.map((label, idx) => (
                  <li key={label}>
                    {label}
                    <input
                      type="checkbox"
                      name={label}
                      onClick={onMajorHandler}
                    ></input>
                  </li>
                ))}
                <li>
                  <label>Level</label>
                  <input
                    type="text"
                    name="level"
                    onChange={onLevelHandler}
                  ></input>
                </li>
              </ul>
            </>
          )}
        </div>

        <button type="submit">등록</button>
      </form>
    </>
  );
};

export default RegisterForm;
