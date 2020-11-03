import axios from "axios";

const API_URL = "http://localhost:8080/auth/";

class AuthService {
    login = (username, password) => {
        return axios
        .post(API_URL + "login", {username, password})
        .then((response) => {
            if (response.data.token) {
                console.log(response.data);
                localStorage.setItem("user", JSON.stringify(response.data));
            }
            return response.data;
        });
    };
    logout = () => {
        localStorage.removeItem("user");
    };
    signup = (username, email, password) => {
        return axios.post(API_URL + "signup", {
            username, email, password
        });
    };
}

export default new AuthService();