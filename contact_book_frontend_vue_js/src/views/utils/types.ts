import type { Contact, ContactGroupItem } from "@/types/domain";

export type StateContactView = {
	contactGroup: ContactGroupItem[];
	contactSelected: Contact | null;
	isAddingContact: boolean;
};
