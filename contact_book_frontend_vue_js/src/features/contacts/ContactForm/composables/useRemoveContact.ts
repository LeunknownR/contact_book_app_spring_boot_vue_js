import type { Store } from "vuex";
import type { ContactState } from "@/store/contact-store/state";
import ActionTypes from "@/store/contact-store/action-types";
import type { Contact } from "@/types/domain";
import type { ComputedRef } from "vue";
import { notify } from "@/utils/helpers";

type RemoveContactAction = () => Promise<void>;
const useRemoveContact = (
	store: Store<ContactState>,
	selectedContact: ComputedRef<Contact | null>,
	closeForm: () => void,
	fetchContacts: () => Promise<void>
): RemoveContactAction => {
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
