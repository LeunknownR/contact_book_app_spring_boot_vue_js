import { Store, createStore } from "vuex";

import type { ContactState } from "./contact-store/state";
import state from "./contact-store/state";
import actions from "./contact-store/actions";
import getters from "./contact-store/getters";
import mutations from "./contact-store/mutations";

export const buildContactStore = (): Store<ContactState> => {
	return createStore<ContactState>({
		actions,
		getters,
		mutations,
		state,
	});
};
const store: Store<ContactState> = buildContactStore();

export default store;
