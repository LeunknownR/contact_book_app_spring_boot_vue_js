import type { Contact } from "@/types/domain";
import type { StateContactView } from "@/views/utils/types";
import { computed, type ComputedRef } from "vue";

export type FormInitializerComposable = {
	startAddingForm(): void;
	selectContact(contact: Contact): void;
	close(): void;
	show: ComputedRef<boolean>;
};
const useInitForm = (state: StateContactView): FormInitializerComposable => {
	const startAddingForm = (): void => {
		state.contactSelected = null;
		state.isAddingContact = true;
	};
	const selectContact = (contact: Contact): void => {
		state.contactSelected = contact;
		state.isAddingContact = false;
	};
	const closeForm = (): void => {
		state.contactSelected = null;
		state.isAddingContact = false;
	};
	const showForm = computed<boolean>(
		() => state.contactSelected !== null || state.isAddingContact
	);
	return {
		startAddingForm,
		selectContact,
		close: closeForm,
		show: showForm,
	};
};

export default useInitForm;
