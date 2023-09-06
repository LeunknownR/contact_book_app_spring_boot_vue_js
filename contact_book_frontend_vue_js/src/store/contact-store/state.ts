import type { Contact } from "@/services/types";

export type ContactState = {
	contacts: Contact[];
};

const state = (): ContactState => {
	return {
		contacts: [],
	};
};

export default state;
