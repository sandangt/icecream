import baseUrl from "baseUrl.js";


export const loginService = async (username, password) => {
    const response = await baseUrl.post("/auth/login", { username, password });
    if (response.data.token) {
        console.log(response.data);
        localStorage.setItem("user", JSON.stringify(response.data));
    }
    return response.data;
};
export const logoutService = async () => {
    localStorage.removeItem("user");
    const response = await baseUrl.get("/auth/logout");
    return response.data;
};
export const signupService = (username, email, password) => {
    return baseUrl.post("/auth/signup", {
        username, email, password
    });
};
