<template>
	<ErrorMessage :message="error">
		<Field
			label="Categoría"
			icon="icomoon-free:book"
		>
			<Dropdown
				v-if="editStatus || !value || !isEdit"
				:tabindex="3"
				placeholder="Elige una categoría"
				:max-length="50"
				:model-value="value"
				:options="categories"
				:error="error"
				key-text="name"
				@getOptionText="getCategoryText"
				@update:model-value="onChange"
				@blur="toggleEditStatus(false)"
			/>
			<ValueField
				v-else
				:value="value?.name"
				@start-edition="toggleEditStatus(true)"
			/>
		</Field>
	</ErrorMessage>
</template>
<script setup lang="ts">
	import Field from "@/components/Fields/Field.vue";
	import ValueField from "@/components/Fields/ValueField.vue";
	import Dropdown from "@/components/Fields/Dropdown.vue";
	import type { ContactCategory } from "@/types/domain";
	import { computed } from "vue";
	import { useContactStore } from "@/store/contact-store";
	import ErrorMessage from "@/components/ErrorMessage.vue";

	defineProps<{
		value: ContactCategory | null;
		editStatus: boolean;
		isEdit: boolean;
		error: string | null;
	}>();
	const store = useContactStore();
	const categories = computed<ContactCategory[]>(
		() => store.getters.getAllContactCategories
	);
	const emits = defineEmits<{
		(e: "update:value", value: ContactCategory | null): void;
		(e: "update:editStatus", value: boolean): void;
		(e: "clearError"): void;
	}>();
	const getCategoryText = (category: ContactCategory): string =>
		category.name;
	const onChange = (value: ContactCategory) => {
		emits("update:value", value);
		emits("clearError");
	};
	const toggleEditStatus = (value: boolean): void => {
		emits("update:editStatus", value);
	};
</script>
