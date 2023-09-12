<template>
	<header class="flex justify-between items-center">
		<Title :title="title" />
		<button
			class="text-indigo-200 text-3xl duration-300 hover:text-indigo-300"
			type="button"
			@click="emits('closeForm')"
		>
			<Icon icon="entypo:cross" />
		</button>
	</header>
</template>
<script setup lang="ts">
	import { Icon } from "@iconify/vue/dist/iconify.js";
	import { computed } from "vue";
	import Title from "@/components/Title.vue";

	const props = defineProps<{
		isEdition: boolean;
	}>();
	const emits = defineEmits<{
		(e: "closeForm"): void;
	}>();
	const title = computed<string>(() => {
		return props.isEdition ? "Datos de contacto" : "Nuevo contacto";
	});
</script>
