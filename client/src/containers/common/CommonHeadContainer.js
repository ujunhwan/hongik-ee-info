import React, { Fragment, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import CommonHeader from "../../components/common/CommonHeader";
import { logoutUser } from "../../modules/user";
import LoginContainer from "../user/LoginContainer";

function CommonHeadContainer(props) {
  const dispatch = useDispatch();
  const [isModalOpen, setModal] = useState(false);

  const openModalHandler = () => {
    setModal(true);
  };

  const closeModalHandler = () => {
    setModal(false);
  };

  const onLogoutHandler = (event) => {
    event.preventDefault();
    dispatch(logoutUser());
  };

  return (
    <Fragment>
      <CommonHeader
        onLoginHandler={openModalHandler}
        onLogoutHandler={onLogoutHandler}
      />
      {isModalOpen && (
        <Fragment>
          <div className="background-blur" />
          <div id="modal-root">
            <LoginContainer closeModalHandler={closeModalHandler} />
          </div>
        </Fragment>
      )}
    </Fragment>
  );
}

export default CommonHeadContainer;
