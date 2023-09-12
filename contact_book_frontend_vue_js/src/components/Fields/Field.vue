<template>
	<div class="flex flex-col gap-1">
		<Label
			v-if="label"
			:label="label"
			:icon="icon"
		/>
		<slot></slot>
	</div>
</template>
<script setup lang="ts">
	import Label from "./Label.vue";

	defineProps<{
		label?: string;
		icon?: string;
	}>();
</script>
