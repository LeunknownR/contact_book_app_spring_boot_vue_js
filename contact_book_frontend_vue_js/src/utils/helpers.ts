export const sleep = async (timeout?: number): Promise<void> => {
	return new Promise<void>(res => {
		setTimeout(res, timeout || 1000);
	});
};
