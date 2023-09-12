import ActionTypes from "@/store/contact-store/action-types";
import type { ContactState } from "@/store/contact-store/state";
import type { Store } from "vuex";

const useFormData = (store: Store<ContactState>): (() => Promise<void>) => {
	const fetchContactFormData = async (): Promise<void> => {
		await store.dispatch(ActionTypes.FetchContactCategories);
	};
	return fetchContactFormData;
};

export default useFormData;
