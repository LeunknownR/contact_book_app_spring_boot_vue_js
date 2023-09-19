<template>
	<Teleport to="#modals">
		<Transition name="wrapper">
			<div
				v-if="isOpen"
				class="bg-overlay fixed w-full h-full top-0 left-0 grid place-items-center"
			>
				<section
					class="relative flex bg-indigo-800 rounded-md p-6 w-max"
					ref="content"
				>
					<button
						class="absolute top-3 right-3 text-indigo-400 text-3xl hover:text-indigo-300"
						type="button"
						@click="closeModal"
					>
						<Icon icon="entypo:cross" />
					</button>
					<slot></slot>
				</section>
			</div>
		</Transition>
	</Teleport>
</template>
<style scoped>
	.wrapper-enter-active,
	.wrapper-leave-active {
		transition: all 0.3s ease;
	}

	.wrapper-enter-from,
	.wrapper-leave-to {
		opacity: 0;
		scale: 1.3;
	}
</style>
<script setup lang="ts">
	import { Icon } from "@iconify/vue/dist/iconify.js";
	import { onClickOutside } from "@vueuse/core";
	import { ref } from "vue";

	defineProps<{
		isOpen: boolean;
	}>();
	const emits = defineEmits<{
		(e: "update:isOpen", value: boolean): void;
	}>();
	const closeModal = (): void => emits("update:isOpen", false);
	const content = ref(null);
	onClickOutside(content, closeModal);
</script>
