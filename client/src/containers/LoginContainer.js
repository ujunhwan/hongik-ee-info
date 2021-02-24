import React, { Component } from "react";
import LoginComponent from "../components/login/LoginForm";
import Api from "./Api";

class LoginContainer extends Component {
  constructor(props) {
    super(props);
    this.state = {
      Id: "",
      Pw: "",
    };
    this.handleLoginInfo.bind(this);
    this.onChangeId.bind(this);
    this.onChangePw.bind(this);
  }

  handleLoginInfo = () => {
    console.log(`${this.state.Id} /// ${this.state.Pw}`);
    const loginInfo = {
      Id: this.state.Id,
      Pw: this.state.Pw,
    }
    Api.post("/api/login", loginInfo
    ,  {
      headers: { "Content-Type": `application/json`},
      responseType: 'json',
    })
    .then((response) => {for(let key in response) {
      console.log(response[key]);
    }});
  };

  onChangeId = (e) => {
    const { value } = e.target;
    this.setState({ Id: value });
  };

  onChangePw = (e) => {
    const { value } = e.target;
    this.setState({ Pw: value });
  };

  render() {
    const { handleModalClose } = this.props;
    return (
      <>
        <LoginComponent
          handleModalClose={handleModalClose}
          onClickLogin={this.handleLoginInfo}
          onChangeId={this.onChangeId}
          onChangePw={this.onChangePw}
        />
      </>
    );
  }
}

export default LoginContainer;
