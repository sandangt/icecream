import {
    CREATE_USER,
    READ_USERS,
    READ_USER,
    UPDATE_USER,
    DELETE_USER,
    SET_MESSAGE
} from "./type.js";

import {readAllUserService, readUserByIdService} from "services/userCrudService.js";

export const readAllUser = () => async dispatch => {
    return readAllUserService().then( 
    (response) => {
        dispatch({
            type: READ_USERS,
            payload: response.data
        });
        dispatch({
            type: SET_MESSAGE,
            payload: response.data
        });
        return Promise.resolve();
    }, (error) => {
        const message =
            (error.response &&
                error.response.data &&
                error.response.data.message) ||
            error.message ||
            error.toString();
        dispatch({
            type: SET_MESSAGE,
            payload: message,
            });
        return Promise.reject();
    });
};

export const readUserByIdService = (id) => async dispatch => {

};