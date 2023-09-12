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
				<span class="text-indigo-200 text-xs">{{ firstPhone }}</span>
			</div>
		</div>
		<FavoriteSwitch
			v-show="!hiddenFavorite"
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
	import { useContactStore } from "@/store/contact-store";
	import ActionTypes from "@/store/contact-store/action-types";

	const props = defineProps<{
		contact: Contact;
		contactSelected: Contact | null;
		hiddenFavorite: boolean;
	}>();
	const emits = defineEmits<{
		(e: "selectContact", contact: Contact): void;
	}>();
	const initialOfName = computed<string>(() => props.contact.name[0]);
	const firstPhone = computed<string>(() => props.contact.phones[0].number);
	const isSelected = computed<boolean>(() => {
		const { contactSelected } = props;
		return (
			contactSelected !== null && contactSelected.id === props.contact.id
		);
	});
	const store = useContactStore();
	const toggleFavorite = () => {
		store.dispatch(ActionTypes.ToggleFavoriteContact, props.contact.id);
	};
</script>
