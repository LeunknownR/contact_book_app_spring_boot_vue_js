<template>
	<div
		class="bg-indigo-200 text-slate-50 grid place-items-center text-lg font-medium w-14 h-14 rounded-full"
	>
		<span>{{ initial }}</span>
	</div>
</template>
<script setup lang="ts">
	const { initial } = defineProps<{
		initial: string;
	}>();
</script>
