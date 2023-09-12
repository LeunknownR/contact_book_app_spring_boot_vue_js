<template>
	<ErrorMessage
		class="ml-7"
		:message="error"
	>
		<div class="flex items-center gap-2">
			<span class="grid place-items-center">
				<Icon
					class="text-indigo-400 text-lg"
					icon="bi:phone-fill"
				/>
			</span>
			<TextInput
				v-if="editStatus || !isEdit"
				type="tel"
				placeholder="666666666"
				:max-length="9"
				:model-value="value.number"
				@update:model-value="onInput"
				@blur="onBlur"
			/>
			<ValueField
				v-else
				:value="readableNumber"
				@start-edition="toggleEditStatus(true)"
			/>
			<button
				type="button"
				@click="onRemove"
			>
				<Icon
					class="text-indigo-200 text-xl duration-300 hover:text-indigo-300"
					icon="mdi:trash"
				/>
			</button>
		</div>
	</ErrorMessage>
</template>
<script setup lang="ts">
	import { Icon } from "@iconify/vue/dist/iconify.js";
	import TextInput from "@/components/Fields/TextInput.vue";
	import ValueField from "@/components/Fields/ValueField.vue";
	import type { ContactPhone } from "@/types/domain";
	import ErrorMessage from "@/components/ErrorMessage.vue";
	import { computed } from "vue";

	const props = defineProps<{
		value: ContactPhone;
		editStatus: boolean;
		isEdit: boolean;
		error: string | null;
	}>();
	const emits = defineEmits<{
		(e: "update:value", value: string): void;
		(e: "update:editStatus", value: boolean): void;
		(e: "clearError"): void;
		(e: "remove", phone: ContactPhone): void;
	}>();
	const toggleEditStatus = (value: boolean): void => {
		emits("update:editStatus", value);
	};
	const onBlur = (): void => {
		toggleEditStatus(false);
		const { value } = props;
		if (!value.number) onRemove();
	};
	const onInput = (value: string): void => {
		emits("update:value", value);
		emits("clearError");
	};
	const onRemove = () => emits("remove", props.value);
	const readableNumber = computed<string>(() => {
		const { number } = props.value;
		let result = "";
		for (let i = 0; i < number.trim().length; i++) {
			const space = i % 3 === 0 ? " " : "";
			result += `${space}${number[i]}`;
		}
		return result;
	});
</script>
