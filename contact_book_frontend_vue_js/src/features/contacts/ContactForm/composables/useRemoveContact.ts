import type { Store } from "vuex";
import type { ContactState } from "@/store/contact-store/state";
import type { ContactForm } from "../utils/types";

type RemoveContactAction = () => Promise<void>;
const useRemoveContact = (
	store: Store<ContactState>,
	form: ContactForm
): RemoveContactAction => {
	const removeContact = async (): Promise<void> => {
		console.log("Removing...", form);
	};
	return removeContact;
};

export default useRemoveContact;
