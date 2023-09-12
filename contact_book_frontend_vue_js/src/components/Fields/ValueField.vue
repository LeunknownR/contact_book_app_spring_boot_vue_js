<template>
	<div class="flex gap-3">
		<span class="text-slate-50 font-thin">{{ value }}</span>
		<button
			type="button"
			@click="emits('startEdition')"
		>
			<Icon
				class="text-indigo-200"
				icon="ooui:edit"
			/>
		</button>
	</div>
</template>
<script setup lang="ts">
	import { Icon } from "@iconify/vue/dist/iconify.js";

	defineProps<{
		value: string;
	}>();
	const emits = defineEmits<{
		(e: "startEdition"): void;
	}>();
</script>
