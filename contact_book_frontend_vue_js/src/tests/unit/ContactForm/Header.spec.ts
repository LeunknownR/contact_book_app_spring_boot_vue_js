import Title from "@/components/Title.vue";
import Header from "@/features/contacts/ContactForm/layout/Header.vue";
import { mount, shallowMount } from "@vue/test-utils";
import { describe, expect, it } from "vitest";
import type { VueTestComponent } from "../utils/types";

type HeaderComponent = VueTestComponent<typeof Header>;
describe("Header.vue (ContactForm)", () => {
	describe("for title", () => {
		const getTitle = (wrapper: HeaderComponent): string => {
			return wrapper.findComponent(Title).find("h2").text();
		};
		it("should be 'Nuevo contacto' when is not edition form", () => {
			const wrapper = mount(Header, {
				props: {
					isEdition: false,
				},
			});
			const title: string = getTitle(wrapper);
			expect(title).toBe("Nuevo contacto");
		});
		it("should be 'Datos de contacto' when is edition form", () => {
			const wrapper = mount(Header, {
				props: {
					isEdition: true,
				},
			});
			const title: string = getTitle(wrapper);
			expect(title).toBe("Datos de contacto");
		});
	});
	it("should be emitted 'closeForm' event when close form button is clicked", () => {
		const wrapper = shallowMount(Header, {
			props: {
				isEdition: true,
			},
		});
		const button = wrapper.find("button");
		button.trigger("click");
		expect(wrapper.emitted("closeForm")).toBeTruthy();
	});
});
