<template>
	<li class="flex flex-col gap-3 mr-3">
		<ContactGroupHeader :letter="props.contactGroupItem.letter" />
		<ul class="flex flex-col gap-3">
			<ContactCard
				v-for="contact of props.contactGroupItem.contacts"
				:key="contact.id"
				:contact="contact"
				:contactSelected="props.contactSelected"
				:hidden-favorite="hiddenFavorite"
				@selectContact="emits('selectContact', contact)"
			/>
		</ul>
	</li>
</template>
<script setup lang="ts">
	import type { Contact } from "@/types/domain";
	import ContactCard from "../ContactCard/ContactCard.vue";
	import ContactGroupHeader from "../ContactGroup/ContactGroupHeader.vue";
	import type { ContactGroupItem } from "@/types/domain";

	const props = defineProps<{
		contactGroupItem: ContactGroupItem;
		contactSelected: Contact | null;
		hiddenFavorite: boolean;
	}>();
	const emits = defineEmits<{
		(e: "selectContact", contact: Contact): void;
	}>();
</script>
