<template>
	<ul class="flex flex-col gap-2.5">
		<ContactCardSkelleton
			v-for="index in 4"
			:key="index"
		/>
	</ul>
</template>
<script setup lang="ts">
	import ContactCardSkelleton from "./ContactCardSkelleton.vue";
</script>
