import { toast, type ToastType } from "vue3-toastify";

export const sleep = async (timeout?: number): Promise<void> => {
	return new Promise<void>(res => {
		setTimeout(res, timeout || 1000);
	});
};
export const notify = (message: string, type?: ToastType) => {
	toast(message, {
		autoClose: 2000,
		closeButton: true,
		theme: "dark",
		type: type || "success",
		position: "top-center",
		transition: "bounce",
	});
};
