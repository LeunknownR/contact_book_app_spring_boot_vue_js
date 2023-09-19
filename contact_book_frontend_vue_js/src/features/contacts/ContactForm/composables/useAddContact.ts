import ApiResponseMessages from "@/services/utils/constants";
import ActionTypes from "@/store/contact-store/action-types";
import type { ContactFormType } from "../utils/types";
import type { Store } from "vuex";
import type { ContactState } from "@/store/contact-store/state";
import { notify } from "@/utils/helpers";

type AddContactAction = () => Promise<ApiResponseMessages>;
const useAddContact = (
	store: Store<ContactState>,
	form: ContactFormType,
	fetchContacts: () => Promise<void>,
	closeForm: () => void
): AddContactAction => {
	const successAdding = async (): Promise<void> => {
		closeForm();
		await fetchContacts();
		notify("¡Contacto agregado con éxito!");
	};
	const addContact = async (): Promise<ApiResponseMessages> => {
		const message: ApiResponseMessages = await store.dispatch(
			ActionTypes.AddContact,
			form
		);
		if (message === ApiResponseMessages.Success) successAdding();
		return message;
	};
	return addContact;
};

export default useAddContact;
