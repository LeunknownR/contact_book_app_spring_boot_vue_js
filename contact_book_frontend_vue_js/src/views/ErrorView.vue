<template>
	<main class="flex justify-center items-center flex-grow">
		<div class="w-1/2 text-slate-50">
			<h2 class="text-8xl font-bold">404</h2>
			<p class="text-lg font-thin">
				Creo que por aquí no es, sigue navegando.
			</p>
		</div>
		<img
			class="w-50"
			:src="noContentImg"
			alt="404 - Página no encontrada"
		/>
	</main>
</template>
<script setup lang="ts">
	import noContentImg from "@/assets/images/no_content.svg";
</script>
