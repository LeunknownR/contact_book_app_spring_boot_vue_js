import ActionTypes from "@/store/contact-store/action-types";
import type { ContactState } from "@/store/contact-store/state";
import type { Contact } from "@/types/domain";
import type { ComputedRef } from "vue";
import type { Store } from "vuex";
import type { ContactStateForm } from "../utils/types";
import useEditContact from "./useEditContact";
import ApiResponseMessages from "@/services/utils/constants";
import useValidationForm from "./useValidationForm";
import type { FormInitializerComposable } from "./useFormInitializer";

type SaveFormAction = () => Promise<void>;
const useSaveForm = (
	store: Store<ContactState>,
	{ form, errors }: ContactStateForm,
	selectedContact: ComputedRef<Contact | null>,
	fetchContacts: () => Promise<void>,
	formInitializer: FormInitializerComposable
): SaveFormAction => {
	const addContact = async (): Promise<ApiResponseMessages> => {
		const message: ApiResponseMessages = await store.dispatch(
			ActionTypes.AddContact,
			form
		);
		if (message === ApiResponseMessages.Success) {
			formInitializer.close();
			fetchContacts();
		}
		return message;
	};
	const editContact = useEditContact(
		store,
		form,
		selectedContact,
		fetchContacts,
		formInitializer.selectContact
	);
	const { checkErrors, checkPersistenceErrors } = useValidationForm(
		form,
		errors
	);
	const saveForm = async (): Promise<void> => {
		const isEdit: boolean = selectedContact.value !== null;
		if (!checkErrors()) return;
		const message: ApiResponseMessages = isEdit
			? await editContact()
			: await addContact();
		checkPersistenceErrors(message);
	};
	return saveForm;
};

export default useSaveForm;
