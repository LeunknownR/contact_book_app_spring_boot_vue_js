import type { Contact } from "@/types/domain";
import type { StateContactView } from "@/views/utils/types";
import { computed, type ComputedRef } from "vue";

export type FormInitializerComposable = {
	startAddingForm(): void;
	selectContact(contact: Contact): void;
	close(): void;
	show: ComputedRef<boolean>;
};
const useFormInitializer = (
	state: StateContactView
): FormInitializerComposable => {
	const startAddingForm = (): void => {
		state.selectedContact = null;
		state.isAddingContact = true;
	};
	const selectContact = (contact: Contact): void => {
		state.selectedContact = contact;
		state.isAddingContact = false;
	};
	const closeForm = (): void => {
		state.selectedContact = null;
		state.isAddingContact = false;
	};
	const showForm = computed<boolean>(
		() => state.selectedContact !== null || state.isAddingContact
	);
	return {
		startAddingForm,
		selectContact,
		close: closeForm,
		show: showForm,
	};
};

export default useFormInitializer;
