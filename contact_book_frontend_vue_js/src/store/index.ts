import { createStore } from "vuex";

import type { ContactState } from "./contact-store/state";
import state from "./contact-store/state";
import actions from "./contact-store/actions";
import getters from "./contact-store/getters";
import mutations from "./contact-store/mutations";

const store = createStore<ContactState>({
	actions,
	getters,
	mutations,
	state,
	// modules: {
	// 	contactModule,
	// },
});

export default store;
