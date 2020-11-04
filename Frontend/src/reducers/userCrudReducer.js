import lodash from "lodash";

import {
    CREATE_USER,
    READ_USERS,
    READ_USER,
    UPDATE_USER,
    DELETE_USER,
    SET_MESSAGE
} from "actions/type.js";

export default (state={}, action) => {
	switch (action.type)
	{
		case READ_USERS:
			return {...state, ...lodash.mapKeys(action.payload, "id")};
		case READ_USER:
			return {...state, [action.payload.id]: action.payload};
		case CREATE_USER:
			return {...state, [action.payload.id]: action.payload};
		case UPDATE_USER:
			return {...state, [action.payload.id]: action.payload};
		case DELETE_USER:
			return lodash.omit(state, action.payload);
		default:
			return state;
	}
};