import axios from "axios";
import type { AxiosError, AxiosInstance } from "axios";
import { addResponseInterceptor } from "./interceptors";

const api: AxiosInstance = axios.create({
    baseURL: `${import.meta.env.VITE_API_URL}/api`,
    headers: {
        "Accept": "application/json",
        "Content-Type": "application/json"
    }
});
addResponseInterceptor(api.interceptors.response);

export default api;