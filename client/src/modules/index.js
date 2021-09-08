import { combineReducers } from "redux";
import user from "./user";
import test from "./test";
import storage from "redux-persist/lib/storage/session";
import { persistReducer } from "redux-persist";

const persistConfig = {
  key: "root",
  storage,
  whitelist: ["user"],
};

//testasdf 초기화됨
const rootReducer = combineReducers({
  user,
  testasdf: test,
});

export default persistReducer(persistConfig, rootReducer);
