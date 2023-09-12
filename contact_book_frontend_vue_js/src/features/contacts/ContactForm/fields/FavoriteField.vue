<template>
	<button
		type="button"
		class="flex flex-col items-center text-sm"
		@click="onClick"
	>
		<Icon
			class="icon"
			:class="{ 'opacity-0': modelValue }"
			icon="uit:favorite"
		/>
		<Icon
			class="icon absolute"
			:class="{ 'opacity-0': !modelValue }"
			icon="uis:favorite"
		/>
		<span class="text-indigo-200">Favorito</span>
	</button>
</template>
<style scoped lang="postcss">
	.icon {
		@apply text-3xl text-indigo-400 duration-200;
	}
</style>
<script setup lang="ts">
	import { Icon } from "@iconify/vue/dist/iconify.js";
	const props = defineProps<{
		modelValue: boolean;
	}>();
	const emits = defineEmits<{
		(e: "update:modelValue", value: boolean): void;
	}>();
	const onClick = (): void => {
		emits("update:modelValue", !props.modelValue);
	};
</script>
