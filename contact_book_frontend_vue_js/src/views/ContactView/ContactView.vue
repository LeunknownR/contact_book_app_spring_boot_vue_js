<template>
	<section class="flex flex-col gap-5">
		<Title title="Contactos" />
		<aside class="flex flex-col max-h-96 overflow-y-auto">
			<ContactGroup
				:contacts="state.contacts"
				:contact-selected="state.contactSelected"
				@selectContact="selectContact"
			/>
		</aside>
	</section>
</template>
<script setup lang="ts">
	import ContactGroup from "@/components/ContactGroup.vue";
	import Title from "@/components/Title.vue";
	import type { Contact } from "@/services/types";
	import { onBeforeMount, reactive } from "vue";
	import { useContactStore } from "@/store/contact-store";
	import ActionTypes from "@/store/contact-store/action-types";

	const state = reactive<{
		contacts: Contact[];
		contactSelected: Contact | null;
	}>({
		contacts: [],
		contactSelected: null,
	});
	const selectContact = (contact: Contact): void => {
		state.contactSelected = contact;
	};
	onBeforeMount(() => {
		(async () => {
			const store = useContactStore();
			await store.dispatch(ActionTypes.FetchContacts);
			state.contacts = store.getters.getContacts;
		})();
	});
</script>
