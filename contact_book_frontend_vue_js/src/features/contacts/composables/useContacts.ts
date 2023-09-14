import type { ContactFiltersData } from "@/services/utils/types";
import ActionTypes from "@/store/contact-store/action-types";
import type { ContactState } from "@/store/contact-store/state";
import type { Contact, ContactGroupItem } from "@/types/domain";
import type { Store } from "vuex";

type ContactsComposable = {
	store: Store<ContactState>;
	filters: ContactFiltersData;
	fillContactGroup: (contactGroup: ContactGroupItem[]) => void;
	fetchContactsActionType: ActionTypes;
};
const useContacts = ({
	store,
	filters,
	fillContactGroup,
	fetchContactsActionType,
}: ContactsComposable): (() => Promise<void>) => {
	const isNewContactGroup = (
		currentLetter: string | null,
		firstLetter: string
	): boolean => {
		return !currentLetter || currentLetter !== firstLetter;
	};
	const sorterContacts = (
		firstContact: Contact,
		secondContact: Contact
	): number => {
		if (firstContact.name < secondContact.name) return -1;
		if (firstContact.name > secondContact.name) return 1;
		return 0;
	};
	const convertToContactGroup = (contacts: Contact[]): ContactGroupItem[] => {
		const contactGroup: ContactGroupItem[] = [];
		let currentLetter: string | null = null;
		[...contacts].sort(sorterContacts).forEach(contact => {
			const firstLetter: string = contact.name[0];
			if (isNewContactGroup(currentLetter, firstLetter)) {
				currentLetter = firstLetter;
				contactGroup.push({
					letter: currentLetter,
					contacts: [contact],
				});
				return;
			}
			if (currentLetter !== firstLetter) return;
			const contactGroupItem: ContactGroupItem =
				contactGroup[contactGroup.length - 1];
			contactGroupItem.contacts.push(contact);
		});
		return contactGroup;
	};
	const fetchContacts = async (): Promise<void> => {
		await store.dispatch(fetchContactsActionType, filters);
		const contacts: Contact[] = store.getters.getContacts;
		const contactGroup: ContactGroupItem[] =
			convertToContactGroup(contacts);
		fillContactGroup(contactGroup);
	};
	return fetchContacts;
};

export default useContacts;
