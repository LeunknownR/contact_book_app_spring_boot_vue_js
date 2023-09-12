<template>
	<button
		type="button"
		class="rounded-md py-2.5 duration-300"
		:class="{
			// Primary
			primary: variant === 'primary',
			secondary: variant === 'secondary',
		}"
		@click="emits('click')"
	>
		<Icon
			v-if="icon"
			class="text-2xl"
			:icon="icon"
		/>
		<span
			v-if="content"
			class="text-md"
			>{{ content }}</span
		>
	</button>
</template>
<style scoped lang="postcss">
	.primary {
		@apply px-5 text-indigo-200 bg-indigo-900 hover:text-slate-50;
	}
	.secondary {
		@apply px-3 bg-indigo-200 text-indigo-900 hover:bg-slate-50;
	}
</style>
<script setup lang="ts">
	import { Icon } from "@iconify/vue/dist/iconify.js";

	defineProps<{
		content?: string;
		icon?: string;
		variant: "primary" | "secondary";
	}>();
	const emits = defineEmits<{
		(e: "click"): void;
	}>();
</script>
