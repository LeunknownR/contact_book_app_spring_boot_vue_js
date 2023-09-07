<template>
	<nav class="flex flex-wrap justify-center items-center gap-3">
		<RouterLink
			class="flex flex-col justify-center items-center gap-1 p-3 rounded w-20 hover:bg-indigo-800 duration-300 text-slate-50"
			active-class="bg-indigo-500"
			v-for="item in NAVBAR_ITEMS"
			:key="item.id"
			:to="item.to"
		>
			<Icon :icon="item.icon" />
			<span class="text-xs">{{ item.text }}</span>
		</RouterLink>
	</nav>
</template>
<script setup lang="ts">
	import { Icon } from "@iconify/vue/dist/iconify.js";
	import { NAVBAR_ITEMS } from "./utils/constants";
	import { RouterLink } from "vue-router";
</script>
