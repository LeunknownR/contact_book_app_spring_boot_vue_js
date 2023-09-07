<template>
	<button class="cursor-pointer">
		<Icon
			v-if="isFavorite"
			class="favorite-icon"
			icon="uis:favorite"
		/>
		<Icon
			v-else
			class="favorite-icon"
			icon="uit:favorite"
		/>
	</button>
</template>
<style scoped lang="postcss">
	.favorite-icon {
		@apply text-indigo-500 text-4xl duration-300;
	}
</style>
<script setup lang="ts">
	import { Icon } from "@iconify/vue/dist/iconify.js";

	const { isFavorite } = defineProps<{
		isFavorite: boolean;
	}>();
</script>
