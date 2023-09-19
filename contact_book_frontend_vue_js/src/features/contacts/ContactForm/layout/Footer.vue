<template>
	<footer class="flex items-center justify-end gap-3">
		<CustomButton
			v-if="isEdition"
			data-test="remove-contact-btn"
			variant="secondary"
			icon="mdi:trash"
			@click="emits('openRemoveContactConfirmModal')"
		/>
		<CustomButton
			type="submit"
			data-test="save-form-btn"
			variant="primary"
			:content="contentMainButton"
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
		(e: "openRemoveContactConfirmModal"): void;
	}>();
	const contentMainButton = computed<string>(() =>
		props.isEdition ? "Editar" : "Agregar"
	);
</script>
