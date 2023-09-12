<template>
	<div
		class="px-3 py-2 odd:bg-indigo-400 even:bg-indigo-300 text-slate-50 first:rounded-t-md last:rounded-b-md"
		@click="onClick"
	>
		<slot>{{ text }}</slot>
	</div>
</template>
<script setup lang="ts">
	defineProps<{
		text: string;
	}>();
	const emits = defineEmits<{
		(e: "click"): void;
	}>();
	const onClick = (): void => {
		emits("click");
	};
</script>
