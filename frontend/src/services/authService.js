import baseUrl from "baseUrl.js";
import authHeader from "services/authHeader.js";


export const loginService = async (username, password) => {
    const response = await baseUrl.post("/auth/login", { username, password });
    if (response.data.token) {
        localStorage.setItem("user", JSON.stringify(response.data));
    }
    return response.data;
};
export const logoutService = async () => {
    localStorage.removeItem("user");
    localStorage.removeItem("cart");
    const response = await baseUrl.get("/auth/logout");
    return response.data;
};
export const signupService = (username, email, password) => {
    return baseUrl.post("/auth/signup", {
        username, email, password
    });
};

export const changePasswordService = async (id, oldPassword, newPassword) => {
    if (oldPassword === newPassword) {
        return Promise.reject();
    }
    return baseUrl.put(`/users/${id}/password`, {id, oldPassword, newPassword}, {headers: authHeader()});
}