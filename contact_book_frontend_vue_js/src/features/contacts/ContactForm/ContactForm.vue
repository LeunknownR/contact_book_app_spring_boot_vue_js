<template>
	<div class="flex justify-center flex-grow">
		<form
			class="flex flex-col gap-3 bg-indigo-800 p-7 rounded-lg"
			@submit.prevent="saveForm"
		>
			<Header
				@close-form="formInitializer.close"
				:is-edition="isEdition"
			/>
			<ErrorMessage
				class="self-end"
				:message="state.errors.persistence"
			>
				<Fields
					:form="state.form"
					:errors="state.errors"
					:formEditStatus="state.formEditStatus"
					:selected-contact="selectedContact"
				/>
			</ErrorMessage>
			<Footer
				:is-edition="isEdition"
				@save-form="saveForm"
				@open-remove-contact-confirm-modal="
					openRemoveContactConfirmModal
				"
			/>
		</form>
	</div>
	<RemoveContactConfirmModal
		v-model:is-open="isOpenRemoveContactConfirmModal"
		@remove-contact="removeContact"
	/>
</template>
<script setup lang="ts">
	import Header from "./layout/Header.vue";
	import Fields from "./layout/Fields.vue";
	import Footer from "./layout/Footer.vue";
	import { computed, reactive, ref } from "vue";
	import type { Contact } from "@/types/domain";
	import {
		buildContactForm,
		buildContactFormEditStatus,
		buildContactFormErrors,
	} from "./utils/constants";
	import useSetupForm from "./composables/useSetupForm";
	import { useContactStore } from "@/store/contact-store";
	import useSaveForm from "./composables/useSaveForm";
	import useRemoveContact from "./composables/useRemoveContact";
	import type { ContactStateForm } from "./utils/types";
	import ErrorMessage from "@/components/ErrorMessage.vue";
	import type ActionTypes from "@/store/contact-store/action-types";
	import type { FormInitializerComposable } from "./composables/useFormInitializer";
	import RemoveContactConfirmModal from "@/features/contacts/ContactForm/modals/RemoveContactConfirmModal.vue";

	const props = defineProps<{
		selectedContact: Contact | null;
		fetchContactsActionType: ActionTypes;
		formInitializer: FormInitializerComposable;
	}>();
	const store = useContactStore();
	const state = reactive<ContactStateForm>({
		form: buildContactForm(),
		formEditStatus: buildContactFormEditStatus(),
		errors: buildContactFormErrors(),
	});
	const isOpenRemoveContactConfirmModal = ref<boolean>(false);
	const emits = defineEmits<{
		(e: "fetchContacts"): Promise<void>;
	}>();
	const selectedContact = computed(() => props.selectedContact);
	const isEdition = computed<boolean>(() => props.selectedContact !== null);
	useSetupForm(state, selectedContact);
	const fetchContacts = async (): Promise<void> => {
		emits("fetchContacts");
	};
	const saveForm = useSaveForm({
		store,
		state,
		selectedContact,
		fetchContacts,
		formInitializer: props.formInitializer,
	});
	const removeContact = useRemoveContact({
		store,
		selectedContact,
		closeForm: props.formInitializer.close,
		fetchContacts,
	});
	const openRemoveContactConfirmModal = (): void => {
		isOpenRemoveContactConfirmModal.value = true;
	};
	defineExpose({
		state,
	});
</script>
