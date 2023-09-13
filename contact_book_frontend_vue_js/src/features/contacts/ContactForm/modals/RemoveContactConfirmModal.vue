<template>
	<ConfirmationModal
		title="Eliminar contacto"
		content="¿Estás seguro de eliminar el contacto?"
		:is-open="isOpen"
		@update:is-open="updateIsOpen"
	>
		<CustomButton
			variant="secondary"
			content="Cancelar"
			@click="updateIsOpen(false)"
		/>
		<CustomButton
			variant="primary"
			content="Aceptar"
			@click="removeContact"
		/>
	</ConfirmationModal>
</template>
<script setup lang="ts">
	import ConfirmationModal from "@/components/ConfirmationModal.vue";
	import CustomButton from "@/components/CustomButton.vue";

	defineProps<{
		isOpen: boolean;
	}>();
	const emits = defineEmits<{
		(e: "update:isOpen", value: boolean): void;
		(e: "removeContact"): void;
	}>();
	const updateIsOpen = (value: boolean): void =>
		emits("update:isOpen", value);
	const removeContact = () => {
		updateIsOpen(false);
		emits("removeContact");
	};
</script>
