import type { Contact } from "@/types/domain";
import { type ComputedRef, watch, onMounted } from "vue";
import {
	buildActiveFormEditStatus,
	buildContactForm,
	buildContactFormEditStatus,
	buildContactFormErrors,
} from "../utils/constants";
import type { ContactStateForm } from "../utils/types";

const useSetupForm = (
	{ form, errors, formEditStatus }: ContactStateForm,
	selectedContact: ComputedRef<Contact | null>
) => {
	const resetForm = (): void => {
		Object.assign(form, buildContactForm());
	};
	const resetEditStatus = (selectedContact: Contact): void => {
		Object.assign(formEditStatus, buildContactFormEditStatus());
		formEditStatus.phones = selectedContact.phones.map(() => false);
	};
	const activeEditionStatusForm = () => {
		Object.assign(formEditStatus, buildActiveFormEditStatus());
	};
	const resetFormErrors = (): void => {
		Object.assign(errors, buildContactFormErrors());
	};
	const resetPhoneErrors = (selectedContact: Contact): void => {
		errors.phones = selectedContact.phones.map(() => null);
	};
	const preloadDataToForm = (selectedContact: Contact): void => {
		form.name = selectedContact.name;
		form.email = selectedContact.email;
		form.category = { ...selectedContact.category };
		form.isFavorite = selectedContact.isFavorite;
		form.phones = selectedContact.phones.map(phone => ({ ...phone }));
	};
	const initAddingForm = (): void => {
		resetForm();
		activeEditionStatusForm();
	};
	const initEditingForm = (selectedContact: Contact): void => {
		resetEditStatus(selectedContact);
		preloadDataToForm(selectedContact);
		resetPhoneErrors(selectedContact);
	};
	const setupForm = (): void => {
		resetFormErrors();
		if (!selectedContact.value) initAddingForm();
		else initEditingForm(selectedContact.value);
	};
	onMounted(setupForm);
	watch(selectedContact, setupForm);
};

export default useSetupForm;
