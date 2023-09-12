/** @type {import('tailwindcss').Config} */
import colors from "tailwindcss/colors";
export default {
	content: ["./index.html", "./src/**/*.{vue,js,ts,jsx,tsx}"],
	theme: {
		colors: {
			...colors,
			overlay: "#000000bb",
			indigo: {
				200: "#B9B8E7",
				300: "#9E9CEF",
				400: "#908FE4",
				500: "#7874F2",
				600: "#6562CC",
				800: "#5250A6",
				900: "#333166",
			},
			slate: {
				50: "#ffffff",
			},
		},
		extend: {},
	},
	plugins: [],
};
