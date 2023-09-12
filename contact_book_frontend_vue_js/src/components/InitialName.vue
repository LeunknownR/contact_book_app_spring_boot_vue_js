<template>
	<div
		class="bg-indigo-200 text-slate-50 grid place-items-center font-medium w-14 h-14 rounded-full"
		:class="{
			'w-20': isBig,
			'h-20': isBig,
			'text-lg': !isBig,
			'text-2xl': isBig,
		}"
	>
		<span>{{ initialUpperCased }}</span>
	</div>
</template>
<script setup lang="ts">
	import { computed } from "vue";

	const props = defineProps<{
		initial: string;
		isBig: boolean;
	}>();
	const initialUpperCased = computed(() => props.initial.toUpperCase());
</script>
