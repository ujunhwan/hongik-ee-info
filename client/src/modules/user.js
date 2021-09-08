import axios from "axios";
import qs from 'qs';

const LOGIN_USER = "USER/login_user";
const LOGOUT_USER = "USER/logout_user";
const AUTH_USER = "USER/auth_user";

const initState = {
  token: "",
  isLogin: false,
};

export default function (state = initState, action) {
  const { type, payload } = action;
  switch (type) {
    case LOGIN_USER:
      return {
        ...state,
        token: payload.token,
        isLogin: payload.isLogin,
      };

    case LOGOUT_USER:
      return {
        ...state,
        isLogin: payload.isLogin,
        token: payload.token,
      };

    case AUTH_USER:
      return {
        state,
      };

    default:
      return state;
  }
}

export function loginUser(data) {
  const request = axios
    .post("/api/user/login/", data, {
      withCredentials: true,
    })
    .then((response) => {
      console.log(response);
      return response.data;
    });

  return {
    type: LOGIN_USER,
    payload: request,
  };
}

export function logoutUser() {
  axios.get("/api/user/logout");

  return {
    type: LOGOUT_USER,
    payload: {
      token: "",
      isLogin: false,
    },
  };
}
