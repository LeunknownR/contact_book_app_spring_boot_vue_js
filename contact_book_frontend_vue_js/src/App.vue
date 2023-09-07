<template>
	<main class="flex flex-col justify-between min-h-screen">
		<Header />
		<section class="grid place-items-center g-4">
			<div class="flex flex-col gap-7 my-10 flex-grow">
				<Navbar />
				<RouterView />
			</div>
		</section>
		<Footer />
	</main>
</template>
<script setup lang="ts">
	import { RouterView } from "vue-router";
	import Navbar from "@/components/Navbar/Navbar.vue";
	import Header from "@/components/Header.vue";
	import Footer from "@/components/Footer.vue";
</script>
