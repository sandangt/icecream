import {
    SIGNUP_SUCCESS,
    SIGNUP_FAIL,
    LOGIN_SUCCESS,
    LOGIN_FAIL,
    LOGOUT,
    SET_MESSAGE
} from "./type.js";

import {loginService, signupService, logoutService, changePasswordService} from "services/authService.js";

export const signup = (username, email, password) => (dispatch) => {
    return signupService(username, email, password).then(
        (response) => {
            dispatch({
                type: SIGNUP_SUCCESS
            });
            dispatch({
                type: SET_MESSAGE,
                payload: response.data.message,
              });
            return Promise.resolve();
        })
        .catch( (error) => {
            const message =
                (error.response &&
                    error.response.data &&
                    error.response.data.message) ||
                error.message ||
                error.toString();
            dispatch({
                type: SIGNUP_FAIL
            });
            dispatch({
                type: SET_MESSAGE,
                payload: message,
              });
            return Promise.reject();
        }
    );
};

export const login = (username, password) => (dispatch) => {
    return loginService(username, password).then(
        (data) => {
            dispatch({
                type: LOGIN_SUCCESS,
                payload: {user : data}
            });
            return Promise.resolve();
        })
        .catch((error) => {
            const message =
                (error.response &&
                    error.response.data &&
                    error.response.data.message) ||
                error.message ||
                error.toString();
            dispatch({
                type: LOGIN_FAIL
            });
            dispatch({
                type: SET_MESSAGE,
                payload: message,
              });
            return Promise.reject();
        }
    );
};

export const logout = () => (dispatch) => {
    logoutService();
    dispatch({
        type: LOGOUT
    });
};

