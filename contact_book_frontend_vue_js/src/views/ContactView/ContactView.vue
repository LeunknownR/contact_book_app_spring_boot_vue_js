<template>
	<Title title="Contactos" />
	<aside>
		<ContactGroup :contacts="state.contacts" />
	</aside>
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
	}>({
		contacts: [],
	});
	onBeforeMount(() => {
		(async () => {
			const store = useContactStore();
			await store.dispatch(ActionTypes.FetchContacts);
			state.contacts = store.getters.getContacts;
			// state.contacts = data;
		})();
	});
</script>
