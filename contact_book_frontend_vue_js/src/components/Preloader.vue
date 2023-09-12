<template>
	<Teleport to="#preloaders">
		<Transition>
			<div
				v-if="state.isLoading"
				class="fixed top-0 left-0 w-full h-full flex flex-col justify-center items-center gap-6 bg-overlay duration-300"
			>
				<div
					class="w-10 h-10 rounded-full bg-indigo-600 animate-ping"
				></div>
				<span class="text-slate-50 text-md">{{
					state.message || "Cargando..."
				}}</span>
			</div>
		</Transition>
	</Teleport>
</template>
<style scoped>
	.v-enter-active,
	.v-leave-active {
		transition: opacity 0.25s ease;
	}

	.v-enter-from,
	.v-leave-to {
		opacity: 0;
	}
</style>
<script setup lang="ts">
	import type { LoadingState } from "@/store/contact-store/state";

	defineProps<{
		state: LoadingState;
	}>();
</script>
