import ApiResponseMessages from "@/services/utils/constants";
import type { Contact, ContactPhone } from "@/types/domain";
import type { ContactForm } from "../utils/types";
import type { ComputedRef } from "vue";
import type { EditContactPayload } from "@/services/utils/types";
import ActionTypes from "@/store/contact-store/action-types";
import type { ContactState } from "@/store/contact-store/state";
import type { Store } from "vuex";

type EditContactAction = () => Promise<ApiResponseMessages>;
const useEditContact = (
	store: Store<ContactState>,
	form: ContactForm,
	selectedContact: ComputedRef<Contact | null>,
	fetchContacts: () => Promise<void>,
	setContact: (contact: Contact) => void
): EditContactAction => {
	const completePhoneDataIfExistsInSelectedContact = (): ContactPhone[] => {
		return form.phones.map(phone => {
			const phoneFound = selectedContact.value!.phones.find(
				({ id, number }) => id === phone.id || number === phone.number
			);
			return {
				id: phoneFound?.id,
				number: phone.number,
			};
		});
	};
	const successEdition = async (contact: Contact): Promise<void> => {
		await fetchContacts();
		setContact(contact);
	};
	const editContact = async (): Promise<ApiResponseMessages> => {
		const phonesToEdit = completePhoneDataIfExistsInSelectedContact();
		const { name, email, category, isFavorite } = form;
		const payload: EditContactPayload = {
			id: selectedContact.value!.id,
			name,
			email,
			categoryId: category!.id,
			isFavorite,
			phonesToEdit,
		};
		const [message, contact]: [ApiResponseMessages, Contact | null] =
			await store.dispatch(ActionTypes.EditContact, payload);
		if (message === ApiResponseMessages.Success && contact)
			successEdition(contact);
		return message;
	};
	return editContact;
};

export default useEditContact;
