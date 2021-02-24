import React, { Component, Fragment } from "react";
import CommonHeader from "../components/common/CommonHeader";
import LoginForm from "../components/login/LoginForm";
import LoginContainer from "../containers/LoginContainer";

class CommonHeadContainer extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isModalOpen: false,
    };
    this.handleModalOpen.bind(this);
    this.handleModalClose.bind(this);
  }

  handleModalOpen = () => {
    this.setState({
      isModalOpen: true,
    });
    console.log("open!!");
  };

  handleModalClose = () => {
    this.setState({
      isModalOpen: false,
    });
    console.log("close!!");
  };

  render() {
    return (
      <Fragment>
        <CommonHeader onLoginClick={this.handleModalOpen} />
        {this.state.isModalOpen === true && (
          <Fragment>
            <div className="background-blur" />
            <div id="modal-root">
              {/* <LoginForm handleModalClose={this.handleModalClose} /> */}
              <LoginContainer handleModalClose={this.handleModalClose} />
            </div>
          </Fragment>
        )}
      </Fragment>
    );
  }
}

export default CommonHeadContainer;
