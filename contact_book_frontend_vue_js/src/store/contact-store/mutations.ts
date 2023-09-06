import type { MutationTree } from "vuex";
import type { ContactState } from "./state";
import MutationTypes from "./mutation-types";
import type { Contact } from "@/services/types";

const mutations: MutationTree<ContactState> = {
	[MutationTypes.FillContacts](state: ContactState, newContacts: Contact[]) {
		state.contacts = newContacts;
	},
};

export default mutations;
