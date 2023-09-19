<template>
	<button
		class="relative flex flex-col items-center text-sm"
		type="button"
		@click="onClick"
	>
		<Icon
			data-test="non-favorite-icon"
			class="icon absolute"
			:class="{ 'hidden-icon': isFavorite }"
			icon="uit:favorite"
		/>
		<Icon
			data-test="favorite-icon"
			class="icon"
			:class="{ 'hidden-icon': !isFavorite }"
			icon="uis:favorite"
		/>
	</button>
</template>
<style scoped lang="postcss">
	.icon {
		@apply text-4xl text-indigo-500 duration-200;
	}
	.hidden-icon {
		@apply opacity-0;
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
