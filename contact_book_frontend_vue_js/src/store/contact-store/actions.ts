import type { ActionTree } from "vuex";
import type { ContactState } from "./state";
import MutationTypes from "./mutation-types";
import contactService from "@/services/contactService";
import ActionTypes from "./action-types";

const actions: ActionTree<ContactState, ContactState> = {
	async [ActionTypes.FetchContacts]({ commit }) {
		const { data } = await contactService.getContacts();
		commit(MutationTypes.FillContacts, data);
	},
};

export default actions;
