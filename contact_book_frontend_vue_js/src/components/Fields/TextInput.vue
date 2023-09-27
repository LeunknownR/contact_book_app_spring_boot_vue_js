<template>
	<input
		:data-test="dataTest"
		ref="inputRef"
		:type="type || 'text'"
		class="bg-indigo-500 border border-indigo-200 text-slate-50 placeholder:text-indigo-200 font-light rounded px-3 py-2 w-full"
		:placeholder="placeholder"
		:value="modelValue"
		:tabindex="tabindex"
		:maxlength="maxLength"
		@input="onInput"
		@blur="emits('blur')"
		@keypress="onKeyPress"
	/>
</template>
<script setup lang="ts" generic="V extends string | number">
	import { ref } from "vue";

	defineProps<{
		dataTest?: string;
		type?: "text" | "email" | "tel";
		placeholder: string;
		modelValue: V;
		tabindex?: number;
		maxLength: number;
	}>();
	const inputRef = ref<HTMLInputElement | null>(null);
	const emits = defineEmits<{
		(e: "update:modelValue", value: string): void;
		(e: "blur"): void;
		(e: "keypress", event: KeyboardEvent): void;
	}>();
	const onInput = (event: Event): void => {
		emits("update:modelValue", (event.target as HTMLInputElement).value);
	};
	const onKeyPress = (event: KeyboardEvent): void => {
		emits("keypress", event);
	};
</script>
