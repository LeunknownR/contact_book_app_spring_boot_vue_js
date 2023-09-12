<template>
	<button
		class="relative flex flex-col items-center text-sm"
		type="button"
		@click="onClick"
	>
		<Icon
			class="icon absolute"
			:class="{ 'opacity-0': isFavorite }"
			icon="uit:favorite"
		/>
		<Icon
			class="icon"
			:class="{ 'opacity-0': !isFavorite }"
			icon="uis:favorite"
		/>
	</button>
</template>
<style scoped lang="postcss">
	.icon {
		@apply text-4xl text-indigo-500 duration-200;
	}
</style>
<script setup lang="ts">
	import { Icon } from "@iconify/vue/dist/iconify.js";

	const { isFavorite } = defineProps<{
		isFavorite: boolean;
	}>();
	const emits = defineEmits<{
		(e: "toggleFavorite"): void;
	}>();
	const onClick = (e: MouseEvent): void => {
		e.stopPropagation();
		emits("toggleFavorite");
	};
</script>
