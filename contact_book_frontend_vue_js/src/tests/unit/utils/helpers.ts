import { afterEach, beforeEach } from "vitest";

export const setupTeleport = (wrapperId: string): void => {
	beforeEach(() => {
		const el = document.createElement("div");
		el.id = wrapperId;
		document.body.appendChild(el);
	});
	afterEach(() => {
		document.body.outerHTML = "";
	});
};
