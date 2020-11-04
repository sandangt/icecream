import baseUrl from "baseUrl.js";
import authHeader from "./authHeader.js";


export const readAllUserService = async () => {
    const response = await baseUrl.get("/users", {headers: authHeader()});
    return response;
};
export const readUserByIdService = async (id) => {
    const response = await baseUrl.get(`/users/${id}`, {headers: authHeader()});
    return response;
};
export const updateUserById = async (id, requestBody) => {
    const response = await baseUrl.put("/users", {headers: authHeader()}, {...requestBody});
    return response;
};