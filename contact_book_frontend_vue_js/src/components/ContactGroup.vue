<template>
	<ul>
		<ContactCard
			v-for="contact of contacts"
			:key="contact.id"
			:contact="contact"
		/>
	</ul>
</template>
<script setup lang="ts">
	import type { Contact } from "@/services/types";
	import ContactCard from "./ContactCard.vue";
	const { contacts } = defineProps<{
		contacts: Contact[];
	}>();
</script>
