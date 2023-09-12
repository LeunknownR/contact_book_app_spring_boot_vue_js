import type { GetterTree } from "vuex";
import type { ContactState, LoadingState } from "./state";
import type { Contact, ContactCategory } from "@/types/domain";

const getters: GetterTree<ContactState, ContactState> = {
	getContacts(state: ContactState): Contact[] {
		return state.contacts;
	},
	getAllContactCategories(state: ContactState): ContactCategory[] {
		return state.allContactCategories;
	},
	getLoading(state: ContactState): LoadingState {
		return state.loading;
	},
	isFetchingContacts(state: ContactState): boolean {
		return state.isFetchingContacts;
	},
};

export default getters;
