import { useStore, type Module } from "vuex";

import type { ContactState } from "./state";
import state from "./state";
import actions from "./actions";
import getters from "./getters";
import mutations from "./mutations";
import ActionTypes from "./action-types";

const contactModule: Module<ContactState, ContactState> = {
	namespaced: true,
	actions,
	getters,
	mutations,
	state,
};

export const useContactStore = () => {
	const store = useStore<ContactState>();
	return store;
};

export default contactModule;
