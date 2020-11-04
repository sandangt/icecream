import { combineReducers } from "redux";
import authReducer from "./authReducer.js";
import messageReducer from "./messageReducer.js";
import users from "./userCrudReducer.js";

export default combineReducers({
    auth : authReducer,
    userList : users,
    message : messageReducer
});
