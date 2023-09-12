<template>
	<div class="flex flex-col gap-3">
		<slot></slot>
		<ErrorMessage
			v-if="error"
			:message="error"
		/>
	</div>
</template>
<script setup lang="ts">
	import ErrorMessage from "../ErrorMessage.vue";
	defineProps<{
		error: string | null;
	}>();
</script>
