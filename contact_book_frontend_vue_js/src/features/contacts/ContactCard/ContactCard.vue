<template>
	<li
		class="flex justify-between items-center gap-3 px-5 py-3 cursor-pointer duration-300 rounded-lg hover:bg-indigo-800"
		:class="{ 'bg-indigo-800': isSelected }"
		@click="emits('selectContact', contact)"
	>
		<div class="flex gap-3 items-center">
			<InitialName
				:initial="initialOfName"
				:is-big="false"
			/>
			<div class="flex flex-col">
				<h4 class="text-slate-50 text-lg">{{ contact.name }}</h4>
				<span class="text-slate-50 text-sm">{{ contact.email }}</span>
				<span
					data-test="first-phone"
					class="text-indigo-200 text-xs"
					>{{ firstPhone }}</span
				>
			</div>
		</div>
		<FavoriteSwitch
			v-if="!hiddenFavoriteSwitch"
			:is-favorite="contact.isFavorite"
			@toggle-favorite="toggleFavorite"
		/>
	</li>
</template>
<script setup lang="ts">
	import { computed } from "vue";
	import InitialName from "@/components/InitialName.vue";
	import FavoriteSwitch from "./FavoriteSwitch.vue";
	import type { Contact } from "@/types/domain";

	const props = defineProps<{
		contact: Contact;
		selectedContact: Contact | null;
		hiddenFavoriteSwitch: boolean;
	}>();
	const emits = defineEmits<{
		(e: "selectContact", contact: Contact): void;
		(e: "toggleFavorite", contactId: number): Promise<void>;
	}>();
	const initialOfName = computed<string>(() => props.contact.name[0]);
	const firstPhone = computed<string>(() => props.contact.phones[0].number);
	const isSelected = computed<boolean>(() => {
		const { selectedContact } = props;
		return (
			selectedContact !== null && selectedContact.id === props.contact.id
		);
	});
	const toggleFavorite = async () =>
		await emits("toggleFavorite", props.contact.id);
</script>
