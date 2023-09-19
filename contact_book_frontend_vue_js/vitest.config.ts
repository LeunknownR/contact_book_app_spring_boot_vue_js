/// <reference types="vitest" />
import vue from "@vitejs/plugin-vue";
import { fileURLToPath } from "url";
import { defineConfig } from "vite";

export default defineConfig({
	plugins: [vue()],
	resolve: {
		alias: {
			"@": fileURLToPath(new URL("./src", import.meta.url)),
		},
	},
	test: {
		include: ["src/**/*.{test,spec}.{js,ts,jsx,tsx}"],
		environment: "jsdom",
		globals: true,
	},
});
