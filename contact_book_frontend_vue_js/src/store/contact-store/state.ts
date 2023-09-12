import type { Contact, ContactCategory } from "@/types/domain";

export type LoadingState = {
	isLoading: boolean;
	message: string | null;
};
export type ContactState = {
	loading: LoadingState;
	isFetchingContacts: boolean;
	contacts: Contact[];
	allContactCategories: ContactCategory[];
};

const state = (): ContactState => {
	return {
		loading: {
			isLoading: false,
			message: null,
		},
		isFetchingContacts: true,
		contacts: [],
		allContactCategories: [],
	};
};

export default state;
