import axios from "axios";

const API_URL = "http://localhost:8080/auth/";

export const loginService = async (username, password) => {
    const response = await axios.post(API_URL + "login", { username, password });
    if (response.data.token) {
        console.log(response.data);
        localStorage.setItem("user", JSON.stringify(response.data));
    }
    return response.data;
};
export const logoutService = async () => {
    localStorage.removeItem("user");
    const response = await axios.get(API_URL + "logout");
    return response.data;
};
export const signupService = (username, email, password) => {
    return axios.post(API_URL + "signup", {
        username, email, password
    });
};
