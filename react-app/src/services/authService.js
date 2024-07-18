import axios from "axios";

const API_URL = 'http://localhost:8080/auth';

class AuthService {
    login(credentials) {
        return axios.post(`${API_URL}/login`, credentials);
    }
    
    registro(RegisterRequest) {
        return axios.post(`${API_URL}/register`, RegisterRequest);
    }

    getUserData() {
        const token = localStorage.getItem('token');
        return axios.get(`${API_URL}/me`, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });
    }
}

export default new AuthService();