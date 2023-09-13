<template>
	<Modal
		:is-open="isOpen"
		@update:is-open="updateIsOpen"
	>
		<div class="flex flex-col gap-2 w-[70vw] md:w-[35vw] max-w-[600px]">
			<Title :title="title" />
			<p class="paragraph">{{ content }}</p>
			<footer class="flex gap-3 justify-end">
				<slot></slot>
			</footer>
		</div>
	</Modal>
</template>
<script setup lang="ts">
	import Modal from "./Modal.vue";
	import Title from "./Title.vue";

	defineProps<{
		title: string;
		content: string;
		isOpen: boolean;
	}>();
	const emits = defineEmits<{
		(e: "update:isOpen", value: boolean): void;
	}>();
	const updateIsOpen = (value: boolean): void =>
		emits("update:isOpen", value);
</script>
