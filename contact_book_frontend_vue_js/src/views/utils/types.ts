import type { Contact, ContactGroupItem } from "@/types/domain";

export type StateContactView = {
	contactGroup: ContactGroupItem[];
	selectedContact: Contact | null;
	isAddingContact: boolean;
};
