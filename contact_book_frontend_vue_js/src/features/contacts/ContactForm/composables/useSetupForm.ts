import type { Contact } from "@/types/domain";
import { type ComputedRef, watch, onMounted } from "vue";
import {
	INIT_CONTACT_FORM,
	INIT_FORM_ERRORS,
	INIT_FORM_FIELD_EDIT_STATUS,
} from "../utils/constants";
import type { ContactStateForm } from "../utils/types";

const useSetupForm = (
	{ form, errors, formEditStatus }: ContactStateForm,
	selectedContact: ComputedRef<Contact | null>
) => {
	const resetForm = (): void => {
		Object.assign(form, structuredClone(INIT_CONTACT_FORM));
	};
	const resetEditStatus = (contactSelected: Contact): void => {
		Object.assign(
			formEditStatus,
			structuredClone(INIT_FORM_FIELD_EDIT_STATUS)
		);
		formEditStatus.phones = contactSelected.phones.map(() => false);
	};
	const activeEditionStatusForm = () => {
		Object.assign(
			formEditStatus,
			structuredClone({
				name: true,
				email: true,
				category: true,
				phones: [],
			})
		);
	};
	const resetFormErrors = (): void => {
		Object.assign(errors, structuredClone(INIT_FORM_ERRORS));
	};
	const resetPhoneErrors = (selectedContact: Contact): void => {
		errors.phones = selectedContact.phones.map(() => null);
	};
	const preloadDataToForm = (contactSelected: Contact): void => {
		form.name = contactSelected.name;
		form.email = contactSelected.email;
		form.category = { ...contactSelected.category };
		form.isFavorite = contactSelected.isFavorite;
		form.phones = contactSelected.phones.map(phone => ({ ...phone }));
	};
	const initAddingForm = (): void => {
		resetForm();
		activeEditionStatusForm();
	};
	const initEditingForm = (contactSelected: Contact): void => {
		resetEditStatus(contactSelected);
		preloadDataToForm(contactSelected);
		resetPhoneErrors(contactSelected);
	};
	const setupForm = (): void => {
		resetFormErrors();
		if (!selectedContact.value) initAddingForm();
		else initEditingForm(selectedContact.value);
	};
	onMounted(setupForm);
	watch(selectedContact, setupForm, { deep: true });
};

export default useSetupForm;
