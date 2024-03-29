import type { ActionTree } from "vuex";
import type { ContactState } from "./state";
import MutationTypes from "./mutation-types";
import contactService from "@/services/contactService";
import ActionTypes from "./action-types";
import contactCategoryService from "@/services/contactCategoryService";
import type {
	EditContactPayload,
	ContactFiltersData,
} from "@/services/utils/types";
import type { ContactFormType } from "@/features/contacts/ContactForm/utils/types";
import type { Contact } from "@/types/domain";
import type ApiResponseMessages from "@/services/utils/constants";

const actions: ActionTree<ContactState, ContactState> = {
	async [ActionTypes.FetchContacts]({ commit }, filters: ContactFiltersData) {
		commit(MutationTypes.SetMessageLoading, null);
		commit(MutationTypes.ToggleLoading, true);
		commit(MutationTypes.SetIsFetchingContacts, true);
		const { data } = await contactService.getContacts(filters);
		commit(MutationTypes.ToggleLoading, false);
		commit(MutationTypes.SetIsFetchingContacts, false);
		commit(MutationTypes.FillContacts, data);
	},
	async [ActionTypes.FetchFavoriteContacts]({ commit }) {
		commit(MutationTypes.SetMessageLoading, null);
		commit(MutationTypes.ToggleLoading, true);
		commit(MutationTypes.SetIsFetchingContacts, true);
		const { data } = await contactService.getFavoriteContacts();
		commit(MutationTypes.ToggleLoading, false);
		commit(MutationTypes.SetIsFetchingContacts, false);
		commit(MutationTypes.FillFavoriteContacts, data);
	},
	async [ActionTypes.FetchContactCategories]({ commit }) {
		commit(MutationTypes.SetMessageLoading, null);
		commit(MutationTypes.ToggleLoading, true);
		const { data } = await contactCategoryService.getContactCategories();
		commit(MutationTypes.ToggleLoading, false);
		commit(MutationTypes.FillContactCategories, data);
	},
	async [ActionTypes.AddContact](
		{ commit },
		{
			name,
			category,
			email,
			isFavorite,
			phones: phoneNumbers,
		}: ContactFormType
	): Promise<string> {
		commit(MutationTypes.SetMessageLoading, "Agregando contacto...");
		commit(MutationTypes.ToggleLoading, true);
		const { status: message } = await contactService.addContact({
			name,
			email,
			categoryId: category!.id,
			isFavorite,
			phonesToAdd: phoneNumbers.map(({ number }) => number),
		});
		commit(MutationTypes.ToggleLoading, false);
		return message;
	},
	async [ActionTypes.EditContact](
		{ commit },
		payload: EditContactPayload
	): Promise<[ApiResponseMessages, Contact | null]> {
		commit(MutationTypes.SetMessageLoading, "Editando contacto...");
		commit(MutationTypes.ToggleLoading, true);
		const { data: contact, status: message } =
			await contactService.editContact(payload);
		commit(MutationTypes.ToggleLoading, false);
		return [message as ApiResponseMessages, contact];
	},
	async [ActionTypes.RemoveContact](
		{ commit },
		contactId: number
	): Promise<void> {
		commit(MutationTypes.SetMessageLoading, "Eliminando contacto...");
		commit(MutationTypes.ToggleLoading, true);
		await contactService.removeContact(contactId);
		commit(MutationTypes.ToggleLoading, false);
	},
	async [ActionTypes.ToggleFavoriteContact](
		{ commit },
		contactId: number
	): Promise<void> {
		await contactService.toggleFavoriteContact(contactId);
		commit(MutationTypes.ToggleFavoriteContact, contactId);
	},
};

export default actions;
