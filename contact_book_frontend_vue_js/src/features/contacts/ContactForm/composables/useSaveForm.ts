import useEditContact from "./useEditContact";
import ApiResponseMessages from "@/services/utils/constants";
import useValidationForm from "./useValidationForm";
import useAddContact from "./useAddContact";
import type { ContactCrudAction, SaveFormComposableParams } from "./types";

const useSaveForm = ({
	store,
	state: { form, errors },
	selectedContact,
	fetchContacts,
	formInitializer,
}: SaveFormComposableParams): ContactCrudAction => {
	const addContact = useAddContact(
		store,
		form,
		fetchContacts,
		formInitializer.close
	);
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
