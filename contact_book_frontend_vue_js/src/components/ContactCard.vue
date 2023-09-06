<template>
	<li>
		<div>
			<span>{{ initialOfContactName }}</span>
		</div>
		<div>
			<h4>{{ name }}</h4>
			<span>{{ email }}</span>
			<span>{{ firstPhone }}</span>
		</div>
	</li>
</template>
<script setup lang="ts">
	import type { Contact } from "@/services/types";
	import { computed } from "vue";
	const { contact } = defineProps<{
		contact: Contact;
	}>();
	const { name, email } = contact;
	const initialOfContactName = computed<string>(() => contact.name[0]);
	const firstPhone = computed<string>(() => contact.phones[0].number);
</script>
