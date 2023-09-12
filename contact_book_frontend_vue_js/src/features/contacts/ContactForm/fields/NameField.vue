<template>
	<ErrorMessage :message="error">
		<Field
			label="Nombre"
			icon="fluent:contact-card-48-filled"
		>
			<TextInput
				v-if="editStatus || !isEdit"
				:tabindex="1"
				placeholder="Kurt Cobain"
				:max-length="50"
				:model-value="value"
				@update:model-value="onInput"
				@blur="toggleEditStatus(false)"
			/>
			<ValueField
				v-else
				:value="value"
				@start-edition="toggleEditStatus(true)"
			/>
		</Field>
	</ErrorMessage>
</template>
<script setup lang="ts">
	import Field from "@/components/Fields/Field.vue";
	import TextInput from "@/components/Fields/TextInput.vue";
	import ValueField from "@/components/Fields/ValueField.vue";
	import ErrorMessage from "@/components/ErrorMessage.vue";

	const props = defineProps<{
		value: string;
		editStatus: boolean;
		isEdit: boolean;
		error: string | null;
	}>();
	const emits = defineEmits<{
		(e: "update:value", value: string): void;
		(e: "update:editStatus", value: boolean): void;
		(e: "clearError"): void;
	}>();
	const onInput = (value: string) => {
		emits("update:value", value);
		emits("clearError");
	};
	const toggleEditStatus = (value: boolean): void => {
		emits("update:editStatus", value);
	};
</script>
