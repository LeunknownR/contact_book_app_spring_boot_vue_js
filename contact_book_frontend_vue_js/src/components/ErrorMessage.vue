<template>
	<div class="flex flex-col gap-2">
		<slot></slot>
		<div
			v-if="message"
			data-test="message"
			class="flex items-center w-max gap-1 bg-red-400 text-slate-50 px-2.5 py-1 rounded-md"
			:class="class"
		>
			<span class="text-sm grid place-items-center"
				><Icon icon="material-symbols:error-outline"
			/></span>
			<span class="text-xs mt-0.5">{{ message }}</span>
		</div>
	</div>
</template>
<script setup lang="ts">
	import { Icon } from "@iconify/vue/dist/iconify.js";

	defineProps<{
		message: string | null;
		class?: string;
	}>();
</script>
