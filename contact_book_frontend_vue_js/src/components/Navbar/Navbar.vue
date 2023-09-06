<template>
	<nav>
		<RouterLink
			v-for="item in NAVBAR_ITEMS"
			:key="item.id"
			:to="item.to"
		>
			<Icon :icon="item.icon" />
			<span>{{ item.text }}</span>
		</RouterLink>
	</nav>
</template>
<script setup lang="ts">
	import { Icon } from "@iconify/vue/dist/iconify.js";
	import { NAVBAR_ITEMS } from "./utils/constants";
	import { RouterLink } from "vue-router";
</script>
