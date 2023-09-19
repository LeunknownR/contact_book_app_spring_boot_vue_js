import type { ContactFiltersData } from "@/services/utils/types";
import useFormData from "@/features/contacts/ContactForm/composables/useFormData";
import useFormInitializer, {
	type FormInitializerComposable,
} from "@/features/contacts/ContactForm/composables/useFormInitializer";
import useContacts from "@/features/contacts/composables/useContacts";
import { useContactStore } from "@/store/contact-store";
import ActionTypes from "@/store/contact-store/action-types";
import type { Contact, ContactGroupItem } from "@/types/domain";
import { INIT_CONTACT_FILTERS } from "@/features/contacts/ContactFilters/utils/constants";
import { computed, onBeforeMount, reactive, type ComputedRef } from "vue";

type StateContactView = {
	contactGroup: ContactGroupItem[];
	selectedContact: Contact | null;
	isAddingContact: boolean;
	contactFilters: ContactFiltersData;
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
		contactFilters: { ...INIT_CONTACT_FILTERS },
		selectedContact: null,
		isAddingContact: false,
	});
	const fetchContacts = useContacts({
		store,
		filters: state.contactFilters,
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
