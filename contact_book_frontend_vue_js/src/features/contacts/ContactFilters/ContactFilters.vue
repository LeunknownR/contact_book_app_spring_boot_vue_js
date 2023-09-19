<template>
	<section class="flex flex-col gap-2">
		<h5 class="text-md font-regular text-slate-50">Filtros</h5>
		<div class="flex gap-3">
			<div class="flex gap-3 relative">
				<TextInput
					data-test="contact-name-filter"
					:tabindex="1"
					placeholder="Busca por nombre..."
					:max-length="50"
					v-model="contactName"
				/>
				<div class="w-1/2">
					<TextInput
						data-test="phone-number-filter"
						type="tel"
						:tabindex="2"
						placeholder="Celular..."
						:max-length="9"
						@keypress="onlyNumbersForKeyPressEvent"
						v-model="phoneNumber"
					/>
				</div>
			</div>
			<ClearFiltersButton @clear-filters="clearFilters" />
		</div>
	</section>
</template>
<script setup lang="ts">
	import TextInput from "@/components/Fields/TextInput.vue";
	import type { ContactFiltersData } from "@/services/utils/types";
	import { INIT_CONTACT_FILTERS } from "./utils/constants";
	import { onlyNumbersForKeyPressEvent } from "@/utils/helpers";
	import { ref, computed } from "vue";
	import ClearFiltersButton from "./ClearFiltersButton.vue";

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
	const contactName = computed<string>({
		get(): string {
			return props.filters.contactName;
		},
		set(value: string) {
			changeFilter("contactName")(value);
		},
	});
	const phoneNumber = computed<string>({
		get(): string {
			return props.filters.phoneNumber;
		},
		set(value: string) {
			changeFilter("phoneNumber")(value);
		},
	});
	const filtersAreEmpty = (): boolean => {
		return !contactName.value && !phoneNumber.value;
	};
	const clearFilters = (): void => {
		const { filters } = props;
		if (filtersAreEmpty()) return;
		Object.assign(filters, INIT_CONTACT_FILTERS);
		emits("fetchContacts");
	};
</script>
