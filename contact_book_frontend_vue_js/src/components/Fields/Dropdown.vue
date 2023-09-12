<template>
	<button
		type="button"
		class="relative"
		:tabindex="tabindex"
		@blur="onBlur"
		@keyup.enter="open = true"
	>
		<div
			class="flex justify-between items-center bg-indigo-600 border-2 border-indigo-500 font-light rounded px-3 py-2 w-full"
			:class="{
				'text-slate-50': isSelected,
				'text-indigo-200': !isSelected,
			}"
			@click="open = true"
		>
			{{ text }}
			<Icon
				class="text-xl text-indigo-200"
				icon="raphael:arrowdown"
			/>
		</div>
		<div
			v-if="open"
			class="flex flex-col absolute top-full translate-y-2 w-full border-2 border-indigo-500 rounded-md"
		>
			<DropdownOption
				v-for="(option, i) in options"
				:key="i"
				:text="getOptionText(option)"
				@click="onChange(option)"
			/>
		</div>
	</button>
</template>
<script setup lang="ts" generic="O">
	import { computed, ref } from "vue";
	import DropdownOption from "./DropdownOption.vue";
	import { Icon } from "@iconify/vue/dist/iconify.js";
	import ErrorMessage from "../ErrorMessage.vue";

	const props = defineProps<{
		placeholder: string;
		keyText: keyof O;
		modelValue: O | null;
		options: O[];
		tabindex: number;
	}>();
	const open = ref<boolean>(false);
	const emits = defineEmits<{
		(e: "update:modelValue", value: O): void;
		(e: "blur"): void;
	}>();
	const onChange = (value: O): void => {
		open.value = false;
		emits("update:modelValue", value);
	};
	const onBlur = () => {
		open.value = false;
		emits("blur");
	};
	const getOptionText = (option: O): string =>
		option[props.keyText] as string;
	const text = computed<string>(() => {
		const { modelValue, placeholder } = props;
		const result: string = modelValue
			? getOptionText(modelValue)
			: placeholder;
		return result;
	});
	const isSelected = computed<boolean>(() => {
		return props.modelValue !== null;
	});
</script>
