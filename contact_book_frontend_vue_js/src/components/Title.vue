<template>
	<div class="flex gap-2 items-center">
		<div
			data-role="bullet"
			class="bg-indigo-500 w-6 h-6 rounded"
		></div>
		<h2 class="text-slate-50 text-xl font-bold">{{ title }}</h2>
	</div>
</template>
<script setup lang="ts">
	const { title } = defineProps<{
		title: string;
	}>();
</script>
