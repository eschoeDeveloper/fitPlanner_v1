import axios from 'axios';

const axiosInstance = axios.create({
    // baseURL: "http://localhost:7070",
    withCredentials: true,
    headers: {
        "Content-Type" : "application/json"
    }
});

axiosInstance.interceptors.request.use(
    (config) => {
        config.headers['Access-Control-Allow-Origin'] = '*';  // CORS 설정(모든 리소스 허용)
        config.headers['Access-Control-Allow-Methods'] = "GET, POST, PUT, DELETE, PATCH, OPTIONS";
        config.headers['Access-Control-Allow-Headers'] = "X-Requested-With, content-type, Authorization";
        return config;
    }
)

export default axiosInstance;