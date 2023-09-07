<template>
	<li class="flex flex-col gap-3 mr-3">
		<ContactGroupHeader :letter="groupLetter" />
		<ul class="flex flex-col gap-3">
			<ContactCard
				v-for="contact of props.contacts"
				:key="contact.id"
				:contact="contact"
				:contactSelected="props.contactSelected"
				@selectContact="emit('selectContact', contact)"
			/>
		</ul>
	</li>
</template>
<script setup lang="ts">
	import { computed } from "vue";
	import type { Contact } from "@/services/types";
	import ContactCard from "./ContactCard.vue";
	import ContactGroupHeader from "./ContactGroupHeader.vue";

	const props = defineProps<{
		contacts: Contact[];
		contactSelected: Contact | null;
	}>();
	const groupLetter = computed<string>(() => {
		const { contacts } = props;
		if (contacts.length === 0) return "-";
		return props.contacts[0].name[0].toUpperCase();
	});
	const emit = defineEmits<{
		(e: "selectContact", contact: Contact): void;
	}>();
</script>
