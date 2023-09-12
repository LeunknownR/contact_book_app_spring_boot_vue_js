<template>
	<input
		ref="inputRef"
		:type="type || 'text'"
		class="bg-indigo-500 border border-indigo-200 text-slate-50 placeholder:text-indigo-200 font-light rounded px-3 py-2 w-full"
		:placeholder="placeholder"
		:value="modelValue"
		:tabindex="tabindex"
		:maxlength="maxLength"
		@input="onInput"
		@blur="emits('blur')"
	/>
</template>
<script setup lang="ts">
	import { onMounted, ref } from "vue";

	defineProps<{
		type?: "text" | "email" | "tel";
		placeholder: string;
		modelValue: string;
		tabindex?: number;
		maxLength: number;
	}>();
	const inputRef = ref<HTMLInputElement | null>(null);
	const emits = defineEmits<{
		(e: "update:modelValue", value: string): void;
		(e: "blur"): void;
	}>();
	const onInput = (event: Event): void => {
		emits("update:modelValue", (event.target as HTMLInputElement).value);
	};
	onMounted(() => {
		inputRef.value?.focus();
	});
</script>
