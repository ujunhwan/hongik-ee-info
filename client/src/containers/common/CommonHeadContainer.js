import React, { Fragment, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import CommonHeader from "../../components/common/CommonHeader";
import { curr, grad } from "../../modules/test";
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

  const isLogin = useSelector((state) => state.user.isLogin);

  useEffect(() => {
    console.log(isLogin);
  }, []);

  const currHandler = () => {
    dispatch(curr("222"));
  };

  const gradHandler = () => {
    dispatch(grad("111"));
  };


  return (
    <Fragment>
      <CommonHeader
        gradHandler={gradHandler}
        currHandler={currHandler}
        // onLoginHandler={openModalHandler}
        // onLogoutHandler={onLogoutHandler}
        // loginHandler={loginHandler}
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
