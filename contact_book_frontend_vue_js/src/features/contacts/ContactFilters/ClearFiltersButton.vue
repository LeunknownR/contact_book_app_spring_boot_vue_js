<template>
	<button
		type="button"
		class="bg-indigo-400 rounded grid place-items-center px-2 duration-300 hover:bg-indigo-300"
		@click="emits('clearFilters')"
	>
		<Icon
			class="text-indigo-900 text-2xl"
			icon="ant-design:clear-outlined"
		/>
	</button>
</template>
<script setup lang="ts">
	import { Icon } from "@iconify/vue/dist/iconify.js";

	const emits = defineEmits<{
		(e: "clearFilters"): void;
	}>();
</script>
