<template>
	<header class="grid place-items-center relative">
		<h3
			class="text-slate-50 bg-indigo-200 px-3.5 py-1 text-sm rounded before:w-full before:h-0.5 before:bg-indigo-200 before:absolute before:top-1/2 before:left-0 before:-translate-y-1/2 before:-z-10"
		>
			{{ props.letter }}
		</h3>
	</header>
</template>
<script setup lang="ts">
	const props = defineProps<{
		letter: String;
	}>();
</script>
