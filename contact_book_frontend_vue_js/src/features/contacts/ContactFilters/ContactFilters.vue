<template>
	<section class="flex flex-col gap-2">
		<h5 class="text-md font-regular text-slate-50">Filtros</h5>
		<div class="flex gap-3">
			<div class="flex gap-3 relative">
				<TextInput
					:tabindex="1"
					placeholder="Busca por nombre..."
					:max-length="50"
					:model-value="filters.contactName"
					@update:modelValue="changeContactName"
				/>
				<div class="w-1/2">
					<TextInput
						type="tel"
						:tabindex="2"
						placeholder="Celular..."
						:max-length="9"
						:model-value="filters.phoneNumber"
						@keypress="onlyNumbersForKeyPressEvent"
						@update:modelValue="changePhoneNumber"
					/>
				</div>
			</div>
			<button
				type="button"
				class="bg-indigo-400 rounded grid place-items-center px-2 duration-300 hover:bg-indigo-300"
				@click="clearFilters"
			>
				<Icon
					class="text-indigo-900 text-2xl"
					icon="ant-design:clear-outlined"
				/>
			</button>
		</div>
	</section>
</template>
<script setup lang="ts">
	import TextInput from "@/components/Fields/TextInput.vue";
	import { Icon } from "@iconify/vue/dist/iconify.js";
	import type { ContactFiltersData } from "@/services/utils/types";
	import { INIT_CONTACT_FILTERS } from "./utils/constants";
	import { onlyNumbersForKeyPressEvent } from "@/utils/helpers";
	import { ref } from "vue";

	const props = defineProps<{
		filters: ContactFiltersData;
	}>();
	const emits = defineEmits<{
		(e: "fetchContacts"): Promise<void>;
	}>();
	const timeoutIdForFetchContacts = ref<NodeJS.Timeout>();
	const changeFilter =
		(field: keyof ContactFiltersData) =>
		(value: string): void => {
			clearTimeout(timeoutIdForFetchContacts.value);
			props.filters[field] = value.trim();
			timeoutIdForFetchContacts.value = setTimeout(
				() => emits("fetchContacts"),
				300
			);
		};
	const changeContactName = (value: string) => {
		changeFilter("contactName")(value);
	};
	const changePhoneNumber = (value: string) => {
		changeFilter("phoneNumber")(value);
	};
	const clearFilters = (): void => {
		Object.assign(props.filters, INIT_CONTACT_FILTERS);
		emits("fetchContacts");
	};
</script>
