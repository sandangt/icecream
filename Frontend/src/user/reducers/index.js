import { combineReducers } from "redux";
import authReducer from "./authReducer.js";
import messageReducer from "./messageReducer.js";

export default combineReducers({
    auth : authReducer,
    message : messageReducer
});