<template>
	<li
		class="animate-pulse animate-duration-100 flex justify-between items-center gap-3 px-5 py-3"
	>
		<div class="flex gap-3 w-full items-center">
			<InitialName
				initial=" "
				:is-big="false"
			/>
			<div class="flex flex-col relative flex-grow gap-1">
				<h4 class="bg-slate-50 w-4/6 py-3 rounded-sm"></h4>
				<span class="bg-slate-50 w-5/12 py-2 rounded-sm"></span>
				<span class="bg-indigo-200 w-1/4 py-1 rounded-sm"></span>
			</div>
		</div>
		<div class="bg-indigo-500 w-12 h-10 rounded"></div>
	</li>
</template>
<style scoped></style>
<script setup lang="ts">
	import InitialName from "@/components/InitialName.vue";
</script>
