<template>
	<label class="flex gap-2 items-center text-sm font-bold text-indigo-200">
		<span class="grid place-items-center">
			<Icon
				v-if="props.icon"
				:icon="props.icon"
			/>
		</span>
		<span>{{ label }}</span>
	</label>
</template>
<script setup lang="ts">
	import { Icon } from "@iconify/vue/dist/iconify.js";
	const props = defineProps<{
		label: string;
		icon?: string;
	}>();
</script>
