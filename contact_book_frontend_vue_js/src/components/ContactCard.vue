<template>
	<li
		class="flex justify-between items-center gap-3 w-15 px-5 py-3 cursor-pointer duration-300 rounded-lg hover:bg-indigo-800"
		:class="{ 'bg-indigo-800': isSelected }"
		@click="emit('selectContact', contact)"
	>
		<div class="flex gap-3 w-15 items-center">
			<InitialName :initial="initialOfName" />
			<div class="flex flex-col">
				<h4 class="text-slate-50 text-lg">{{ name }}</h4>
				<span class="text-slate-50 text-sm">{{ email }}</span>
				<span class="text-indigo-200 text-xs">{{ firstPhone }}</span>
			</div>
		</div>
		<FavoriteField :isFavorite="isFavorite" />
	</li>
</template>
<script setup lang="ts">
	import type { Contact } from "@/services/types";
	import { computed } from "vue";
	import InitialName from "./InitialName.vue";
	import FavoriteField from "./ContactCard/components/FavoriteField.vue";

	const props = defineProps<{
		contact: Contact;
		contactSelected: Contact | null;
	}>();
	const emit = defineEmits<{
		(e: "selectContact", contact: Contact): void;
	}>();
	const { name, email, isFavorite } = props.contact;
	const initialOfName = computed<string>(() => props.contact.name[0]);
	const firstPhone = computed<string>(() => props.contact.phones[0].number);
	const isSelected = computed<boolean>(() => {
		const { contactSelected } = props;
		return (
			contactSelected !== null && contactSelected.id === props.contact.id
		);
	});
</script>
