import axios from "axios";

const GRAD = "TEST/GRAD";
const CURR = "TEST/CURR";

const initState = {
  where: null,
};

export default function (state = initState, action) {
  const { type, payload } = action;
  switch (type) {
    case GRAD:
      return {
        ...state,
        where: payload,
      };

    case CURR:
      return {
        ...state,
        where: payload,
      };

    default:
      return state;
  }
}

export function grad(data) {
  console.log(data);
  return {
    type: GRAD,
    payload: data,
  };
}

export function curr(data) {
  console.log(data);
  return {
    type: CURR,
    payload: data,
  };
}
