import axios from "axios";
import AuthHeader from "./AuthHeader.js";

const API_URL = "http://localhost:8080/auth/";

class UserService {
    getPublicContent = () => {
        return axios.get(API_URL + "all");
    };
    getUserBoard = () => {
      return axios.get(API_URL + 'user', { headers: AuthHeader() });
    };
    getModeratorBoard = () => {
      return axios.get(API_URL + 'staff', { headers: AuthHeader() });
    };
    getAdminBoard = () => {
      return axios.get(API_URL + 'admin', { headers: AuthHeader() });
    };
}

export default new UserService();