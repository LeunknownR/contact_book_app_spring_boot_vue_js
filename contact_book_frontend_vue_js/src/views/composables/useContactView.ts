import useFormData from "@/features/contacts/ContactForm/composables/useFormData";
import useFormInitializer, {
	type FormInitializerComposable,
} from "@/features/contacts/ContactForm/composables/useFormInitializer";
import useContacts from "@/features/contacts/composables/useContacts";
import { useContactStore } from "@/store/contact-store";
import ActionTypes from "@/store/contact-store/action-types";
import type { Contact, ContactGroupItem } from "@/types/domain";
import { computed, onBeforeMount, reactive, type ComputedRef } from "vue";

type StateContactView = {
	contactGroup: ContactGroupItem[];
	contactSelected: Contact | null;
	isAddingContact: boolean;
};
type ContactViewComposable = {
	formInitializer: FormInitializerComposable;
	state: StateContactView;
	isFetchingContacts: ComputedRef<boolean>;
	fetchContacts: () => Promise<void>;
};
const useContactView = (
	fetchContactsActionType: ActionTypes
): ContactViewComposable => {
	const store = useContactStore();
	const state = reactive<StateContactView>({
		contactGroup: [],
		contactSelected: null,
		isAddingContact: false,
	});
	const fetchContacts = useContacts({
		store,
		fillContactGroup: contactGroup => (state.contactGroup = contactGroup),
		fetchContactsActionType,
	});
	const isFetchingContacts = computed(() => store.getters.isFetchingContacts);
	const formInitializer = useFormInitializer(state);
	const fetchContactFormData = useFormData(store);
	const fetchAllData = async () => {
		await fetchContactFormData();
		await fetchContacts();
	};
	onBeforeMount(() => fetchAllData());
	return {
		state,
		formInitializer,
		isFetchingContacts,
		fetchContacts,
	};
};

export default useContactView;
