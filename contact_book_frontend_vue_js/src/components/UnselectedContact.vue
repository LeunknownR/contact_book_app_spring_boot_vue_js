<template>
	<section class="flex flex-col items-center gap-5 flex-grow">
		<img
			class="w-36"
			:src="unselectedContactImg"
			alt="Imagen de contacto no seleccionado"
		/>
		<div class="text-slate-50 text-center">
			<h4 class="font-bold md:text-lg mb-0.5">
				No has seleccionado ningún contacto
			</h4>
			<span class="font-light text-sm md:text-sm"
				>Agrega un nuevo contacto o selecciona uno para revisar sus
				datos o editarlo.</span
			>
		</div>
	</section>
</template>
<script setup lang="ts">
	import unselectedContactImg from "@/assets/images/unselected_contact.svg";
</script>
