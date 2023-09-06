<template>
	<Header />
	<Navbar />
	<RouterView />
	<Footer />
</template>
<script setup lang="ts">
	import { RouterView } from "vue-router";
	import Navbar from "@/components/Navbar/Navbar.vue";
	import Header from "@/components/Header.vue";
	import Footer from "@/components/Footer.vue";
</script>
