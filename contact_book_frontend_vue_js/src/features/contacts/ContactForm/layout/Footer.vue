<template>
	<footer class="flex items-center justify-end gap-3">
		<CustomButton
			v-if="isEdition"
			variant="secondary"
			icon="mdi:trash"
			@click="emits('openRemoveContactConfirmModal')"
		/>
		<CustomButton
			variant="primary"
			:content="contentMainButton"
			@click="emits('saveForm')"
		/>
	</footer>
</template>
<script setup lang="ts">
	import CustomButton from "@/components/CustomButton.vue";
	import { computed } from "vue";

	const props = defineProps<{
		isEdition: boolean;
	}>();
	const emits = defineEmits<{
		(e: "saveForm"): void;
		(e: "openRemoveContactConfirmModal"): void;
	}>();
	const contentMainButton = computed<string>(() =>
		props.isEdition ? "Editar" : "Agregar"
	);
</script>
