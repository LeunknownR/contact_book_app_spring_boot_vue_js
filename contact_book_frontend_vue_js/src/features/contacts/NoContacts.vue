<template>
	<section
		class="flex flex-col justify-center items-center gap-2 text-slate-50 flex-grow"
	>
		<span class="grid place-items-center text-6xl">
			<Icon icon="icon-park-solid:book-one" />
		</span>
		<div class="text-center">
			<h4 class="font-bold md:text-lg mb-0.5">
				No se encontraron o existen contactos
			</h4>
			<span class="font-light text-sm md:text-sm">
				Intenta buscando otros o agregando nuevos..
			</span>
		</div>
	</section>
</template>
<script setup lang="ts">
	import { Icon } from "@iconify/vue/dist/iconify.js";
</script>
