<template>
	<h2>{{ title }}</h2>
</template>
<script setup lang="ts">
	const { title } = defineProps<{
		title: string;
	}>();
</script>
