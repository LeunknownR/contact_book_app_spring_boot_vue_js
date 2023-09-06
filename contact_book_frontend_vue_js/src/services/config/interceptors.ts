import type { AxiosError, AxiosInterceptorManager, AxiosResponse } from "axios";

export const addResponseInterceptor = (response: AxiosInterceptorManager<AxiosResponse>) => {
    response.use(
        res => res,
        error => {
            const axiosError = error as AxiosError;
            if (axiosError.response)
                return axiosError.response;
            return Promise.reject(error); 
        });
};