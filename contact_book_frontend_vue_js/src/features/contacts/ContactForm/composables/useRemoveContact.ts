import ActionTypes from "@/store/contact-store/action-types";
import { notify } from "@/utils/helpers";
import type { ContactCrudAction, RemoveContactComposableParams } from "./types";

const useRemoveContact = ({
	store,
	selectedContact,
	closeForm,
	fetchContacts,
}: RemoveContactComposableParams): ContactCrudAction => {
	const removeContact = async (): Promise<void> => {
		await store.dispatch(
			ActionTypes.RemoveContact,
			selectedContact.value?.id
		);
		closeForm();
		await fetchContacts();
		notify("¡Contacto eliminado con éxito!", "error");
	};
	return removeContact;
};

export default useRemoveContact;
