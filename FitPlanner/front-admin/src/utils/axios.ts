import axios from 'axios';

const instance = axios.create({
    baseURL: "http://localhost:7070",
    timeout: 3000
})

instance.interceptors.request.use(
    (request) => request,
    (error) => Promise.reject(error)
);

instance.interceptors.response.use(
    (response) => response,
    (error) => Promise.reject(error)
);

instance.defaults.headers.common["Content-Type"] = "application/json";

export default instance;
