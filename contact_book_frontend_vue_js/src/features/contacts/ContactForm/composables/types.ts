import type { ContactState } from "@/store/contact-store/state";
import type { ContactStateForm } from "../utils/types";
import type { Store } from "vuex";
import type { ComputedRef } from "vue";
import type { Contact } from "@/types/domain";
import type { FormInitializerComposable } from "./useFormInitializer";

export type ContactCrudAction = () => Promise<void>;
export type SaveFormComposableParams = {
	store: Store<ContactState>;
	state: ContactStateForm;
	selectedContact: ComputedRef<Contact | null>;
	fetchContacts: () => Promise<void>;
	formInitializer: FormInitializerComposable;
};
export type RemoveContactComposableParams = {
	store: Store<ContactState>;
	selectedContact: ComputedRef<Contact | null>;
	closeForm: () => void;
	fetchContacts: () => Promise<void>;
};
