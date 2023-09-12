<template>
	<main class="flex flex-col justify-between min-h-screen">
		<Header />
		<section class="flex flex-col items-center gap-5 mt-6 mb-14 flex-grow">
			<Navbar />
			<RouterView />
		</section>
		<Footer />
	</main>
	<Preloader :state="loading" />
</template>
<script setup lang="ts">
	import { RouterView } from "vue-router";
	import Navbar from "@/components/Navbar/Navbar.vue";
	import Header from "@/components/Header.vue";
	import Footer from "@/components/Footer.vue";
	import { useContactStore } from "./store/contact-store";
	import { computed } from "vue";
	import Preloader from "./components/Preloader.vue";
	import type { LoadingState } from "./store/contact-store/state";

	const store = useContactStore();
	const loading = computed<LoadingState>(() => store.getters.getLoading);
</script>
