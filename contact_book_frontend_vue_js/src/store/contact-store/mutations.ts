import type { MutationTree } from "vuex";
import type { ContactState } from "./state";
import MutationTypes from "./mutation-types";
import type { Contact, ContactCategory } from "@/types/domain";

const mutations: MutationTree<ContactState> = {
	[MutationTypes.FillContacts](
		state: ContactState,
		newContacts: Contact[]
	): void {
		state.contacts = newContacts;
	},
	[MutationTypes.FillFavoriteContacts](
		state: ContactState,
		newContacts: Contact[]
	): void {
		state.contacts = newContacts;
	},
	[MutationTypes.ToggleLoading](state: ContactState, value: boolean) {
		state.loading.isLoading = value;
	},
	[MutationTypes.SetMessageLoading](
		state: ContactState,
		message: string | null
	) {
		state.loading.message = message;
	},
	[MutationTypes.SetIsFetchingContacts](state: ContactState, value: boolean) {
		state.isFetchingContacts = value;
	},
	[MutationTypes.FillContactCategories](
		state: ContactState,
		newContactCategories: ContactCategory[]
	): void {
		state.allContactCategories = newContactCategories;
	},
	[MutationTypes.ToggleFavoriteContact](
		state: ContactState,
		contactId: number
	): void {
		state.contacts.forEach(contact => {
			if (contact.id === contactId)
				contact.isFavorite = !contact.isFavorite;
		});
	},
};

export default mutations;
