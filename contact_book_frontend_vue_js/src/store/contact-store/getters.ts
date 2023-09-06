import type { GetterTree } from "vuex";
import type { ContactState } from "./state";

const getters: GetterTree<ContactState, ContactState> = {
	getContacts(state: ContactState) {
		return state.contacts;
	},
};

export default getters;
